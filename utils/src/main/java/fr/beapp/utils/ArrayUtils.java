package fr.beapp.utils;

public class ArrayUtils {

	private ArrayUtils() {
	}

	/**
	 * Null-safe check if the specified collection is empty.
	 *
	 * @param array the array to check, may be null
	 * @return true if empty or null
	 */
	public static boolean isEmpty(Object[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Null-safe check if the specified collection is not empty.
	 *
	 * @param array the array to check, may be null
	 * @return true if non-null and non-empty
	 */
	public static boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);
	}

}
