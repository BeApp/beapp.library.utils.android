package fr.beapp.utils.android;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewParent;
import android.widget.ProgressBar;

public class LoadingViewUtils {

	private LoadingViewUtils() {
	}

	/**
	 * Safely change loading state of the given {@link ProgressBar} on the main thread.
	 *
	 * @param progressBar the progress bar on which to change status
	 * @param loading     the status
	 */
	protected void setLoadingState(@Nullable final ProgressBar progressBar, final boolean loading) {
		if (progressBar == null)
			return;

		progressBar.post(new Runnable() {
			@Override
			public void run() {
				progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
			}
		});
	}

	/**
	 * Safely change loading state of the given {@link SwipeRefreshLayout} on the main thread.
	 *
	 * @param swipeRefreshLayout the swipe refresh layout on which to change status
	 * @param loading            the status
	 */
	public static void setLoadingState(@Nullable final SwipeRefreshLayout swipeRefreshLayout, final boolean loading) {
		if (swipeRefreshLayout == null)
			return;

		swipeRefreshLayout.post(new Runnable() {
			@Override
			public void run() {
				// Fix refresh state not showing when used with a RecyclerView (See http://stackoverflow.com/a/26692841/815737)
				ViewParent parent = swipeRefreshLayout.getParent();
				if (parent instanceof View) {
					View parentView = (View) parent;
					parentView.getMeasuredWidth();
					swipeRefreshLayout.measure(parentView.getMeasuredWidth(), parentView.getMeasuredHeight());
				}

				swipeRefreshLayout.setRefreshing(loading);
			}
		});
	}

}
