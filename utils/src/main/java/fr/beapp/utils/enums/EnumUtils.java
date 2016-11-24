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
	public static <E extends EnumFromWS> E fromKey(@NonNull E[] items, @Nullable String key) {
		return fromKey(items, key, null);
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
	@Nullable
	public static <E extends EnumFromWS> E fromKey(@NonNull E[] items, @Nullable String key, @Nullable E defaultValue) {
		if (key == null)
			return defaultValue;

		for (E item : items) {
			if (key.equalsIgnoreCase(item.getKey()))
				return item;
		}

		return defaultValue;
	}

}
