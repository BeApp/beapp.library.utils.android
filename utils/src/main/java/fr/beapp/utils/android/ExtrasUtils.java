package fr.beapp.utils.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;

import fr.beapp.logger.Logger;

public class ExtrasUtils {

	private ExtrasUtils() {
	}

	public static boolean getBoolean(@NonNull Intent intent, @NonNull String key, boolean defaultValue) {
		return getBoolean(intent.getExtras(), key, defaultValue);
	}

	public static boolean getBoolean(@Nullable Bundle extras, @NonNull String key, boolean defaultValue) {
		return extras != null ? extras.getBoolean(key, defaultValue) : defaultValue;
	}

	public static byte getByte(@NonNull Intent intent, @NonNull String key, byte defaultValue) {
		return getByte(intent.getExtras(), key, defaultValue);
	}

	public static byte getByte(@Nullable Bundle extras, @NonNull String key, byte defaultValue) {
		return extras != null ? extras.getByte(key, defaultValue) : defaultValue;
	}

	public static char getChar(@NonNull Intent intent, @NonNull String key, char defaultValue) {
		return getChar(intent.getExtras(), key, defaultValue);
	}

	public static char getChar(@Nullable Bundle extras, @NonNull String key, char defaultValue) {
		return extras != null ? extras.getChar(key, defaultValue) : defaultValue;
	}

	public static short getShort(@NonNull Intent intent, @NonNull String key, short defaultValue) {
		return getShort(intent.getExtras(), key, defaultValue);
	}

	public static short getShort(@Nullable Bundle extras, @NonNull String key, short defaultValue) {
		return extras != null ? extras.getShort(key, defaultValue) : defaultValue;
	}

	public static int getInt(@NonNull Intent intent, @NonNull String key, int defaultValue) {
		return getInt(intent.getExtras(), key, defaultValue);
	}

	public static int getInt(@Nullable Bundle extras, @NonNull String key, int defaultValue) {
		return extras != null ? extras.getInt(key, defaultValue) : defaultValue;
	}

	public static long getLong(@NonNull Intent intent, @NonNull String key, long defaultValue) {
		return getLong(intent.getExtras(), key, defaultValue);
	}

	public static long getLong(@Nullable Bundle extras, @NonNull String key, long defaultValue) {
		return extras != null ? extras.getLong(key, defaultValue) : defaultValue;
	}

	public static float getFloat(@NonNull Intent intent, @NonNull String key, float defaultValue) {
		return getFloat(intent.getExtras(), key, defaultValue);
	}

	public static float getFloat(@Nullable Bundle extras, @NonNull String key, float defaultValue) {
		return extras != null ? extras.getFloat(key, defaultValue) : defaultValue;
	}

	public static double getDouble(@NonNull Intent intent, @NonNull String key, double defaultValue) {
		return getDouble(intent.getExtras(), key, defaultValue);
	}

	public static double getDouble(@Nullable Bundle extras, @NonNull String key, double defaultValue) {
		return extras != null ? extras.getDouble(key, defaultValue) : defaultValue;
	}

	@Nullable
	public static String getString(@NonNull Intent intent, @NonNull String key, @Nullable String defaultValue) {
		return getString(intent.getExtras(), key, defaultValue);
	}

	@Nullable
	public static String getString(@Nullable Bundle extras, @NonNull String key, @Nullable String defaultValue) {
		return extras != null ? extras.getString(key, defaultValue) : defaultValue;
	}

	@Nullable
	public static <E extends Serializable> E getSerializable(@NonNull Intent intent, @NonNull String key) {
		return getSerializable(intent.getExtras(), key);
	}

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

	@Nullable
	public static <E extends Parcelable> E getParcelable(@NonNull Intent intent, @NonNull String key) {
		return getParcelable(intent.getExtras(), key);
	}

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

}
