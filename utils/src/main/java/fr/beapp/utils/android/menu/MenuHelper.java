package fr.beapp.utils.android.menu;


import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;
import java.util.Queue;

public class MenuHelper {

	private final SparseArray<Queue<MenuExecutor>> deferredMenuExecutors = new SparseArray<>();

	/**
	 * Clear all deferred executors which was registered.
	 */
	public void clearDeferredExecutors() {
		deferredMenuExecutors.clear();
	}

	/**
	 * Execute all deferred executors registered for the given {@link Menu}.
	 *
	 * @param menu the menu to scan
	 */
	public void triggerDeferredExecutors(@NonNull Menu menu) {
		for (int i = 0, size = deferredMenuExecutors.size(); i < size; i++) {
			int menuId = deferredMenuExecutors.keyAt(i);

			MenuItem menuItem = menu.findItem(menuId);
			if (menuItem != null) {
				Queue<MenuExecutor> menuExecutors = deferredMenuExecutors.valueAt(i);
				while (!menuExecutors.isEmpty()) {
					menuExecutors.poll().executeForMenu(menuItem);
				}
			}
		}
	}

	/**
	 * Execute an executor on the given {@link MenuItem} if it's non-<code>null</code>.
	 * If the {@link MenuItem} is <code>null</code>, the executor will be postponed and executor once menu is ready, based on the given menu id.
	 *
	 * @param menuItem     the menu to target for executor
	 * @param menuId       the menu id for register executor
	 * @param menuExecutor the executor to call
	 */
	public void executeOrDefer(@Nullable MenuItem menuItem, @IdRes int menuId, @NonNull MenuExecutor menuExecutor) {
		if (menuItem != null) {
			menuExecutor.executeForMenu(menuItem);
		} else {
			executeOrDefer(menuId, menuExecutor);
		}
	}

	/**
	 * Postpone an executor to trigger once the targetted menu is ready.
	 *
	 * @param menuId       the menu id for register executor
	 * @param menuExecutor the executor to call
	 */
	public void executeOrDefer(@IdRes int menuId, @NonNull MenuExecutor menuExecutor) {
		Queue<MenuExecutor> menuExecutors = deferredMenuExecutors.get(menuId);
		if (menuExecutors == null) {
			menuExecutors = new LinkedList<>();
			deferredMenuExecutors.put(menuId, menuExecutors);
		}
		menuExecutors.add(menuExecutor);
	}

	public interface MenuExecutor {
		/**
		 * Action to execute on the menu item. At this point, it's ready.
		 *
		 * @param menuItem the menu item
		 */
		void executeForMenu(@NonNull MenuItem menuItem);
	}

}
