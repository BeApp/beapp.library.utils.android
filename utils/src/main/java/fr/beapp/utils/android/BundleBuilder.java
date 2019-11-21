package fr.beapp.utils.android;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

/**
 * Provides a pattern-builder to populate a {@link Bundle}
 */
public class BundleBuilder {

	private final Bundle bundle = new Bundle();

	public static BundleBuilder prepare() {
		return new BundleBuilder();
	}

	@NonNull
	public Bundle get() {
		return bundle;
	}

	public BundleBuilder put(@NonNull String key, boolean value) {
		bundle.putBoolean(key, value);
		return this;
	}

	public BundleBuilder put(@NonNull String key, char value) {
		bundle.putInt(key, value);
		return this;
	}

	public BundleBuilder put(@NonNull String key, short value) {
		bundle.putInt(key, value);
		return this;
	}

	public BundleBuilder put(@NonNull String key, byte value) {
		bundle.putInt(key, value);
		return this;
	}

	public BundleBuilder put(@NonNull String key, int value) {
		bundle.putInt(key, value);
		return this;
	}

	public BundleBuilder put(@NonNull String key, long value) {
		bundle.putLong(key, value);
		return this;
	}

	public BundleBuilder put(@NonNull String key, float value) {
		bundle.putFloat(key, value);
		return this;
	}

	public BundleBuilder put(@NonNull String key, double value) {
		bundle.putDouble(key, value);
		return this;
	}

	public BundleBuilder put(@NonNull String key, @Nullable String value) {
		bundle.putString(key, value);
		return this;
	}

	public BundleBuilder put(@NonNull String key, @Nullable Serializable value) {
		bundle.putSerializable(key, value);
		return this;
	}

	public BundleBuilder put(@NonNull String key, @Nullable Parcelable value) {
		bundle.putParcelable(key, value);
		return this;
	}

}
