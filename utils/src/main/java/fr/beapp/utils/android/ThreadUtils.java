package fr.beapp.utils.android;


import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;

public class ThreadUtils {

	private static final Handler HANDLER = new Handler(Looper.getMainLooper());

	private ThreadUtils() {
	}

	/**
	 * Execute the given {@link Runnable} on main thread
	 *
	 * @param runnable the runnable to execute
	 */
	public static void runOnUiThread(@NonNull Runnable runnable) {
		if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
			runnable.run();
		} else {
			HANDLER.post(runnable);
		}
	}

	/**
	 * Execute the given {@link Runnable} on main thread after a delay
	 *
	 * @param runnable    the runnable to execute
	 * @param delayMillis the delay before executing runnable
	 */
	public static void runOnUiThread(@NonNull Runnable runnable, long delayMillis) {
		if (delayMillis == 0) {
			runOnUiThread(runnable);
		} else {
			HANDLER.postDelayed(runnable, delayMillis);
		}
	}

}
