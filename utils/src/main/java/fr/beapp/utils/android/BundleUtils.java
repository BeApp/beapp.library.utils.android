package fr.beapp.utils.android;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;

import fr.beapp.logger.Logger;

public class BundleUtils {

	private BundleUtils() {
	}

	// BOOLEAN

	@Nullable
	public static Boolean getBoolean(@Nullable Bundle extras, @NonNull String key) {
		return extras != null && extras.containsKey(key) ? extras.getBoolean(key) : null;
	}

	public static boolean getBoolean(@Nullable Bundle extras, @NonNull String key, boolean defaultValue) {
		return extras != null ? extras.getBoolean(key, defaultValue) : defaultValue;
	}

	// BYTE

	@Nullable
	public static Byte getByte(@Nullable Bundle extras, @NonNull String key) {
		return extras != null && extras.containsKey(key) ? extras.getByte(key) : null;
	}

	public static byte getByte(@Nullable Bundle extras, @NonNull String key, byte defaultValue) {
		return extras != null ? extras.getByte(key, defaultValue) : defaultValue;
	}

	// CHAR

	public static Character getChar(@Nullable Bundle extras, @NonNull String key) {
		return extras != null && extras.containsKey(key) ? extras.getChar(key) : null;
	}

	public static char getChar(@Nullable Bundle extras, @NonNull String key, char defaultValue) {
		return extras != null ? extras.getChar(key, defaultValue) : defaultValue;
	}

	// SHORT

	@Nullable
	public static Short getShort(@Nullable Bundle extras, @NonNull String key) {
		return extras != null && extras.containsKey(key) ? extras.getShort(key) : null;
	}

	public static short getShort(@Nullable Bundle extras, @NonNull String key, short defaultValue) {
		return extras != null ? extras.getShort(key, defaultValue) : defaultValue;
	}

	// INT


	@Nullable
	public static Integer getInt(@Nullable Bundle extras, @NonNull String key) {
		return extras != null && extras.containsKey(key) ? extras.getInt(key) : null;
	}

	public static int getInt(@Nullable Bundle extras, @NonNull String key, int defaultValue) {
		return extras != null ? extras.getInt(key, defaultValue) : defaultValue;
	}

	// LONG


	@Nullable
	public static Long getLong(@Nullable Bundle extras, @NonNull String key) {
		return extras != null && extras.containsKey(key) ? extras.getLong(key) : null;
	}

	public static long getLong(@Nullable Bundle extras, @NonNull String key, long defaultValue) {
		return extras != null ? extras.getLong(key, defaultValue) : defaultValue;
	}

	// FLOAT

	@Nullable
	public static Float getFloat(@Nullable Bundle extras, @NonNull String key) {
		return extras != null && extras.containsKey(key) ? extras.getFloat(key) : null;
	}


	public static float getFloat(@Nullable Bundle extras, @NonNull String key, float defaultValue) {
		return extras != null ? extras.getFloat(key, defaultValue) : defaultValue;
	}

	// DOUBLE


	@Nullable
	public static Double getDouble(@Nullable Bundle extras, @NonNull String key) {
		return extras != null && extras.containsKey(key) ? extras.getDouble(key) : null;
	}


	public static double getDouble(@Nullable Bundle extras, @NonNull String key, double defaultValue) {
		return extras != null ? extras.getDouble(key, defaultValue) : defaultValue;
	}

	// STRING

	@Nullable
	public static String getString(@Nullable Bundle extras, @NonNull String key) {
		return extras != null && extras.containsKey(key) ? extras.getString(key) : null;
	}

	@NonNull
	public static String getString(@Nullable Bundle extras, @NonNull String key, @NonNull String defaultValue) {
		return extras != null ? extras.getString(key, defaultValue) : defaultValue;
	}

	// SERIALIZABLE

	@Nullable
	@SuppressWarnings("unchecked")
	public static <E extends Serializable> E getSerializable(@Nullable Bundle extras, @NonNull String key) {
		if (extras == null) {
			return null;
		}

		Serializable value = extras.getSerializable(key);
		try {
			return (E) value;
		} catch (ClassCastException e) {
			Logger.warn("Couldn't cast following Serializable value with key %s: %s", e, key, value);
			return null;
		}
	}

	@NonNull
	public static <E extends Serializable> E getSerializable(@Nullable Bundle extras, @NonNull String key, E defaultValue) {
		E value = getSerializable(extras, key);
		return value != null ? value : defaultValue;
	}

	// PARCELABLE

	@Nullable
	@SuppressWarnings("unchecked")
	public static <E extends Parcelable> E getParcelable(@Nullable Bundle extras, @NonNull String key) {
		if (extras == null) {
			return null;
		}

		Parcelable value = extras.getParcelable(key);
		try {
			return (E) value;
		} catch (ClassCastException e) {
			Logger.warn("Couldn't cast following Parcelable value with key %s: %s", e, key, value);
			return null;
		}
	}

	@NonNull
	public static <E extends Parcelable> E getParcelable(@Nullable Bundle extras, @NonNull String key, E defaultValue) {
		E value = getParcelable(extras, key);
		return value != null ? value : defaultValue;
	}

}
