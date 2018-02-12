package fr.beapp.utils.android;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

public class NetwotkUtils {

	/**
	 * Determine if network connection if active
	 *
	 * @param context the calling context
	 * @return <code>true</code> if there is network connection
	 */
	public static boolean isNetworkAvailable(@NonNull Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

}
