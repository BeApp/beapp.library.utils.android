package fr.beapp.utils.android;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import fr.beapp.logger.Logger;

public class ApplicationUtils {

	/**
	 * Retrieve the application's version code
	 *
	 * @param context the calling context
	 * @return the application's version code, or null if: context is null or package name couldn't be resolved
	 */
	public static int getVersionCode(@NonNull Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
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
