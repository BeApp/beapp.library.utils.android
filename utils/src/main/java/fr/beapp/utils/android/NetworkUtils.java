package fr.beapp.utils.android;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;

public class NetworkUtils {

	private NetworkUtils() {
	}

	/**
	 * Determine if network connection if active.
	 * <p>
	 * This method requires the caller to hold the permission
	 * {@link android.Manifest.permission#ACCESS_NETWORK_STATE}.
	 *
	 * @param context the calling context
	 * @return <code>true</code> if there is network connection
	 */
	@RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
	public static boolean isNetworkAvailable(@NonNull Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager == null) {
			return false;
		}

		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

}
