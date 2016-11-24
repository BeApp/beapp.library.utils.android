package fr.beapp.utils.collection;

import java.util.Collection;

public class CollectionUtils {

	private CollectionUtils() {
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
	public static boolean hasOneItem(Collection<?> collection) {
		return collection != null && collection.size() == 1;
	}

	/**
	 * Null-safe check if there is an item at the given index of the specified collection.
	 *
	 * @param collection the collection to check, may be null
	 * @return true if non-null and an item is set at given index
	 */
	public static boolean hasItemAt(Collection<?> collection, int index) {
		return collection != null && index >= 0 && collection.size() > index;
	}

}
