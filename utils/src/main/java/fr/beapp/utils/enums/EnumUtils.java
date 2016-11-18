package fr.beapp.utils.enums;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class EnumUtils {

	@Nullable
	public static <E extends EnumFromWS> E fromKey(@NonNull E[] items, @Nullable String key) {
		return fromKey(items, key, null);
	}

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
