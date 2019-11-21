package fr.beapp.utils.android;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.Serializable;

/**
 * Provides a pattern-builder to instantiate {@link Fragment}s with arguments
 *
 * @param <T> actual type of Fragment
 */
public class FragmentBuilder<T extends Fragment> {

	private final T fragment;
	private final Bundle bundle = new Bundle();

	public FragmentBuilder(@NonNull T fragment) {
		this.fragment = fragment;
	}

	public static <T extends Fragment> FragmentBuilder<T> prepare(@NonNull T fragment) {
		return new FragmentBuilder<>(fragment);
	}

	@NonNull
	public T build() {
		fragment.setArguments(bundle);
		return fragment;
	}

	public FragmentBuilder<T> put(@NonNull String key, boolean value) {
		bundle.putBoolean(key, value);
		return this;
	}

	public FragmentBuilder<T> put(@NonNull String key, char value) {
		bundle.putInt(key, value);
		return this;
	}

	public FragmentBuilder<T> put(@NonNull String key, short value) {
		bundle.putInt(key, value);
		return this;
	}

	public FragmentBuilder<T> put(@NonNull String key, byte value) {
		bundle.putInt(key, value);
		return this;
	}

	public FragmentBuilder<T> put(@NonNull String key, int value) {
		bundle.putInt(key, value);
		return this;
	}

	public FragmentBuilder<T> put(@NonNull String key, long value) {
		bundle.putLong(key, value);
		return this;
	}

	public FragmentBuilder<T> put(@NonNull String key, float value) {
		bundle.putFloat(key, value);
		return this;
	}

	public FragmentBuilder<T> put(@NonNull String key, double value) {
		bundle.putDouble(key, value);
		return this;
	}

	public FragmentBuilder<T> put(@NonNull String key, @Nullable String value) {
		bundle.putString(key, value);
		return this;
	}

	public FragmentBuilder<T> put(@NonNull String key, @Nullable Serializable value) {
		bundle.putSerializable(key, value);
		return this;
	}

	public FragmentBuilder<T> put(@NonNull String key, @Nullable Parcelable value) {
		bundle.putParcelable(key, value);
		return this;
	}

}
