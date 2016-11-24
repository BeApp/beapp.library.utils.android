package fr.beapp.utils.android;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.WindowManager;

import java.util.Locale;

public class DeviceUtils {

	private DeviceUtils() {
	}

	/**
	 * Convenient method to retrieve the screen's width
	 *
	 * @param context the calling context
	 * @return the screen's width, or <code>-1</code> if something went wrong
	 */
	@SuppressWarnings("deprecation")
	public static int getScreenWidth(@NonNull Context context) {
		WindowManager systemService = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		if (systemService == null)
			return -1;

		Display display = systemService.getDefaultDisplay();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			Point size = new Point();
			display.getSize(size);
			return size.x;
		} else {
			return display.getWidth();
		}
	}

	/**
	 * Determine if the device is a tablet (i.e. it has a large screen).
	 *
	 * @param context the calling context.
	 * @return true if the device is a tablet, false if the device is a phone
	 */
	public static boolean isTablet(@NonNull Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	/**
	 * Determine if current orientation is portrait
	 *
	 * @param context the calling context.
	 * @return true if the device is currently in portrait, false otherwise
	 */
	public static boolean isPortrait(@NonNull Context context) {
		return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
	}

	/**
	 * Determine if current orientation is landscape
	 *
	 * @param context the calling context.
	 * @return true if the device is currently in landscape, false otherwise
	 */
	public static boolean isLandscape(@NonNull Context context) {
		return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
	}

	/**
	 * Return a user-agent according to the device configuration
	 * <p>
	 * Inspired from <a href="http://stackoverflow.com/a/5261472/815737">http://stackoverflow.com/a/5261472/815737</a>
	 */
	@NonNull
	public static synchronized String getUserAgent(@NonNull Context context) {
		return String.format("Android (Linux; U; Android %s; %s; %s/%s) %s/%s", Build.VERSION.SDK, Locale.getDefault().getLanguage(), Build.BRAND, Build.MODEL, context.getPackageName(), ApplicationUtils.getVersionName(context));
	}

}
