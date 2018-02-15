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

	static boolean matchedType(@NonNull Bundle bundle, @NonNull String key, @NonNull Class<?> clazz) {
		Object value = bundle.get(key);
		return value != null && clazz.isAssignableFrom(value.getClass());
	}

	// BOOLEAN

	@Nullable
	public static Boolean getBoolean(@Nullable Bundle bundle, @NonNull String key) {
		return bundle != null && matchedType(bundle, key, Boolean.class) ? bundle.getBoolean(key) : null;
	}

	public static boolean getBoolean(@Nullable Bundle bundle, @NonNull String key, boolean defaultValue) {
		return bundle != null ? bundle.getBoolean(key, defaultValue) : defaultValue;
	}

	// BYTE

	@Nullable
	public static Byte getByte(@Nullable Bundle bundle, @NonNull String key) {
		return bundle != null && matchedType(bundle, key, Byte.class) ? bundle.getByte(key) : null;
	}

	public static byte getByte(@Nullable Bundle bundle, @NonNull String key, byte defaultValue) {
		return bundle != null ? bundle.getByte(key, defaultValue) : defaultValue;
	}

	// CHAR

	@Nullable
	public static Character getChar(@Nullable Bundle bundle, @NonNull String key) {
		return bundle != null && matchedType(bundle, key, Character.class) ? bundle.getChar(key) : null;
	}

	public static char getChar(@Nullable Bundle bundle, @NonNull String key, char defaultValue) {
		return bundle != null ? bundle.getChar(key, defaultValue) : defaultValue;
	}

	// SHORT

	@Nullable
	public static Short getShort(@Nullable Bundle bundle, @NonNull String key) {
		return bundle != null && matchedType(bundle, key, Short.class) ? bundle.getShort(key) : null;
	}

	public static short getShort(@Nullable Bundle bundle, @NonNull String key, short defaultValue) {
		return bundle != null ? bundle.getShort(key, defaultValue) : defaultValue;
	}

	// INT

	@Nullable
	public static Integer getInt(@Nullable Bundle bundle, @NonNull String key) {
		return bundle != null && matchedType(bundle, key, Integer.class) ? bundle.getInt(key) : null;
	}

	public static int getInt(@Nullable Bundle bundle, @NonNull String key, int defaultValue) {
		return bundle != null ? bundle.getInt(key, defaultValue) : defaultValue;
	}

	// LONG

	@Nullable
	public static Long getLong(@Nullable Bundle bundle, @NonNull String key) {
		return bundle != null && matchedType(bundle, key, Long.class) ? bundle.getLong(key) : null;
	}

	public static long getLong(@Nullable Bundle bundle, @NonNull String key, long defaultValue) {
		return bundle != null ? bundle.getLong(key, defaultValue) : defaultValue;
	}

	// FLOAT

	@Nullable
	public static Float getFloat(@Nullable Bundle bundle, @NonNull String key) {
		return bundle != null && matchedType(bundle, key, Float.class) ? bundle.getFloat(key) : null;
	}


	public static float getFloat(@Nullable Bundle bundle, @NonNull String key, float defaultValue) {
		return bundle != null ? bundle.getFloat(key, defaultValue) : defaultValue;
	}

	// DOUBLE


	@Nullable
	public static Double getDouble(@Nullable Bundle bundle, @NonNull String key) {
		return bundle != null && matchedType(bundle, key, Double.class) ? bundle.getDouble(key) : null;
	}


	public static double getDouble(@Nullable Bundle bundle, @NonNull String key, double defaultValue) {
		return bundle != null ? bundle.getDouble(key, defaultValue) : defaultValue;
	}

	// STRING

	@Nullable
	public static String getString(@Nullable Bundle bundle, @NonNull String key) {
		return bundle != null && matchedType(bundle, key, String.class) ? bundle.getString(key) : null;
	}

	@NonNull
	public static String getString(@Nullable Bundle bundle, @NonNull String key, @NonNull String defaultValue) {
		return bundle != null ? bundle.getString(key, defaultValue) : defaultValue;
	}

	// SERIALIZABLE

	@Nullable
	@SuppressWarnings("unchecked")
	public static <E extends Serializable> E getSerializable(@Nullable Bundle bundle, @NonNull String key) {
		if (bundle == null) {
			return null;
		}

		Serializable value = bundle.getSerializable(key);
		try {
			return (E) value;
		} catch (ClassCastException e) {
			Logger.warn("Couldn't cast following Serializable value with key %s: %s", e, key, value);
			return null;
		}
	}

	@NonNull
	public static <E extends Serializable> E getSerializable(@Nullable Bundle bundle, @NonNull String key, E defaultValue) {
		E value = getSerializable(bundle, key);
		return value != null ? value : defaultValue;
	}

	// PARCELABLE

	@Nullable
	@SuppressWarnings("unchecked")
	public static <E extends Parcelable> E getParcelable(@Nullable Bundle bundle, @NonNull String key) {
		if (bundle == null) {
			return null;
		}

		Parcelable value = bundle.getParcelable(key);
		try {
			return (E) value;
		} catch (ClassCastException e) {
			Logger.warn("Couldn't cast following Parcelable value with key %s: %s", e, key, value);
			return null;
		}
	}

	@NonNull
	public static <E extends Parcelable> E getParcelable(@Nullable Bundle bundle, @NonNull String key, E defaultValue) {
		E value = getParcelable(bundle, key);
		return value != null ? value : defaultValue;
	}

}
