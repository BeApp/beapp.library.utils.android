package fr.beapp.utils.android;

import android.location.Location;

public class LocationUtils {

	private LocationUtils() {
	}

	/**
	 * Computes the approximate distance in meters between two locations defined using the WGS84 ellipsoid.
	 *
	 * @param startLatitude  the starting latitude
	 * @param startLongitude the starting longitude
	 * @param endLatitude    the ending latitude
	 * @param endLongitude   the ending longitude
	 * @return the distance in meters
	 */
	public static float distance(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
		float[] results = new float[1];
		Location.distanceBetween(startLatitude, startLongitude, endLatitude, endLongitude, results);
		return results[0];
	}

}
