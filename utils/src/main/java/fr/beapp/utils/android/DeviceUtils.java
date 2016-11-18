package fr.beapp.utils.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.UUID;

import fr.beapp.logger.Logger;

public class DeviceUtils {

	private static String userAgent;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public static int getScreenWidth(@NonNull Context context) {
		WindowManager systemService = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		if (systemService == null)
			return 0;

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
	 * Retrieve the device's UID.
	 *
	 * @param context the calling context.
	 * @return the device UID
	 */
	@Nullable
	public static UUID getDeviceUID(@NonNull Context context) {
		// Use the Android ID unless it's broken, in which case fallback on deviceId,
		// unless it's not available, then fallback on a random number which we store
		// to a prefs file
		String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
		if (androidId != null && !"9774d56d682e549c".equals(androidId)) {
			return toUUID(androidId);
		}

		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		final String deviceId = telephonyManager.getDeviceId();
		if (deviceId != null) {
			return toUUID(deviceId);
		}

		return UUID.randomUUID();
	}

	@Nullable
	private static UUID toUUID(@Nullable String value) {
		if (value == null)
			return null;

		try {
			return UUID.nameUUIDFromBytes(value.getBytes("utf8"));
		} catch (UnsupportedEncodingException e) {
			Logger.warn("Couldn't convert value %s to UUID", value);
		}
		return null;
	}

	/**
	 * Inspired from http://stackoverflow.com/a/5261472/815737
	 */
	@NonNull
	public synchronized static String getUserAgent(@NonNull Context context) {
		if (userAgent == null) {
			userAgent = String.format("Android (Linux; U; Android %s; %s; %s/%s) %s/%s", Build.VERSION.SDK, Locale.getDefault().getLanguage(), Build.BRAND, Build.MODEL, context.getPackageName(), ApplicationUtils.getVersionName(context));
		}
		return userAgent;
	}

}
