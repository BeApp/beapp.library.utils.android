package fr.beapp.utils.android;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.annotation.RequiresApi;
import fr.beapp.logger.Logger;

public class ApplicationUtils {

	private ApplicationUtils() {
	}

	/**
	 * Retrieve the application's version code
	 *
	 * @param context the calling context
	 * @return the application's version code, or null if: context is null or package name couldn't be resolved
	 *
	 * @deprecated Use {@link #getLongVersionCode(Context)} instead
	 */
	@Deprecated
	public static int getVersionCode(@NonNull Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			Logger.error("Can't retrieve application's version code", e);
		}
		return -1;
	}

	/**
	 * Retrieve the application's version code for platform SDK > 28
	 *
	 * @param context the calling context
	 * @return the application's version code, or null if: context is null or package name couldn't be resolved
	 */
	@RequiresApi(api = Build.VERSION_CODES.P)
	public static long getLongVersionCode(@NonNull Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).getLongVersionCode();
		} catch (NameNotFoundException e) {
			Logger.error("Can't retrieve application's version code", e);
		}
		return -1;
	}

	/**
	 * Retrieve the application's version name
	 *
	 * @param context the calling context
	 * @return the application's version name, or null if: context is null or package name couldn't be resolved
	 */
	@Nullable
	public static String getVersionName(@NonNull Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			Logger.error("Can't retrieve application's version name", e);
		}
		return null;
	}

}
