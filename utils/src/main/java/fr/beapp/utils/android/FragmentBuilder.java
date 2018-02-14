package fr.beapp.utils.android;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

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

	public FragmentBuilder<T> putString(@NonNull String key, @Nullable String value) {
		bundle.putString(key, value);
		return this;
	}

	public FragmentBuilder<T> putInt(@NonNull String key, int value) {
		bundle.putInt(key, value);
		return this;
	}

	public FragmentBuilder<T> putLong(@NonNull String key, long value) {
		bundle.putLong(key, value);
		return this;
	}

	public FragmentBuilder<T> putFloat(@NonNull String key, float value) {
		bundle.putFloat(key, value);
		return this;
	}

	public FragmentBuilder<T> putDouble(@NonNull String key, double value) {
		bundle.putDouble(key, value);
		return this;
	}

	public FragmentBuilder<T> putBoolean(@NonNull String key, boolean value) {
		bundle.putBoolean(key, value);
		return this;
	}

	public FragmentBuilder<T> putSerializable(@NonNull String key, @Nullable Serializable value) {
		bundle.putSerializable(key, value);
		return this;
	}

	public FragmentBuilder<T> putParcelable(@NonNull String key, @Nullable Parcelable value) {
		bundle.putParcelable(key, value);
		return this;
	}

}
