package fr.beapp.utils.collection;

import androidx.annotation.Nullable;
import android.util.SparseArray;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CollectionUtils {

	private CollectionUtils() {
	}

	/**
	 * Null-safe check if the specified collection is empty.
	 *
	 * @param collection the collection to check, may be null
	 * @return true if empty or null
	 */
	public static boolean isEmpty(Object[] collection) {
		return collection == null || collection.length == 0;
	}

	/**
	 * Null-safe check if the specified collection is empty.
	 *
	 * @param collection the collection to check, may be null
	 * @return true if empty or null
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * Null-safe check if the specified collection is empty.
	 *
	 * @param collection the collection to check, may be null
	 * @return true if empty or null
	 */
	public static boolean isEmpty(Map<?, ?> collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * Null-safe check if the specified collection is empty.
	 *
	 * @param collection the collection to check, may be null
	 * @return true if empty or null
	 */
	public static boolean isEmpty(SparseArray<?> collection) {
		return collection == null || collection.size() == 0;
	}

	/**
	 * Null-safe check if the specified collection is not empty.
	 *
	 * @param collection the collection to check, may be null
	 * @return true if non-null and non-empty
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}

	/**
	 * Null-safe check if the specified collection is not empty and have only one item.
	 *
	 * @param collection the collection to check, may be null
	 * @return true if non-null and have only one item
	 */
	public static boolean hasItemsCount(Collection<?> collection, int count) {
		return collection != null && collection.size() == count;
	}

	/**
	 * Null-safe check if there is an item at the given index of the specified collection.
	 *
	 * @param collection the collection to check, may be null
	 * @return true if non-null and an item is set at given index
	 */
	public static boolean hasItemAt(Collection<?> collection, int index) {
		return collection != null && index >= 0 && index < collection.size();
	}

	@Nullable
	public static <T> T getItemAt(@Nullable List<T> items, int index, T defaultValue) {
		if (hasItemAt(items, index)) {
			return items.get(index);
		}
		return defaultValue;
	}

}
