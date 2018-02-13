package fr.beapp.utils.enums;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class EnumUtils {

	private EnumUtils() {
	}

	/**
	 * Retrieve an {@link Enum} value according to the given key to look for.
	 *
	 * @param items a set of {@link Enum} values to look through
	 * @param key   the key to search in items
	 * @param <E>   the type of the Enum value to search, which must implement {@link EnumFromWS}
	 * @return the item value if found, <code>null</code> otherwise
	 */
	@Nullable
	static <E extends EnumFromWS> E fromKey(@NonNull E[] items, @Nullable String key) {
		if (key == null)
			return null;

		for (E item : items) {
			if (key.equalsIgnoreCase(item.getKey()))
				return item;
		}

		return null;
	}

	/**
	 * Retrieve an {@link Enum} value according to the given key to look for.
	 *
	 * @param items        a set of {@link Enum} values to look through
	 * @param key          the key to search in items
	 * @param defaultValue the value to return if no match was found
	 * @param <E>          the type of the Enum value to search, which must implement {@link EnumFromWS}
	 * @return the item value if found, <code>defaultValue</code> otherwise
	 */
	@NonNull
	public static <E extends EnumFromWS> E fromKey(@NonNull E[] items, @Nullable String key, @NonNull E defaultValue) {
		if (key == null)
			return defaultValue;

		for (E item : items) {
			if (key.equalsIgnoreCase(item.getKey()))
				return item;
		}

		return defaultValue;
	}

	/**
	 * Retrieve an {@link Enum} value according to his name.
	 *
	 * @param clazz The class of {@link Enum} to search names into
	 * @param name  the {@link Enum} name to search in items
	 * @param <E>   the type of the Enum value to search
	 * @return the item value if found, <code>null</code> otherwise
	 */
	@Nullable
	public static <E extends Enum<E>> E fromName(@NonNull Class<E> clazz, @Nullable String name) {
		if (name == null)
			return null;

		try {
			return Enum.valueOf(clazz, name);
		} catch (Exception ignored) {
			// Nothing to do here. The enum was simply not found
		}
		return null;
	}

	/**
	 * Retrieve an {@link Enum} value according to his name.
	 *
	 * @param clazz        The class of {@link Enum} to search names into
	 * @param name         the {@link Enum} name to search in items
	 * @param defaultValue the value to return if no match was found
	 * @param <E>          the type of the Enum value to search
	 * @return the item value if found, <code>defaultValue</code> otherwise
	 */
	@NonNull
	public static <E extends Enum<E>> E fromName(@NonNull Class<E> clazz, @Nullable String name, @NonNull E defaultValue) {
		if (name == null)
			return defaultValue;

		try {
			return Enum.valueOf(clazz, name);
		} catch (Exception ignored) {
			// Nothing to do here. The enum was simply not found
		}
		return defaultValue;
	}

}
