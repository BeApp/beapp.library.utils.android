package fr.beapp.utils.lang;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Provide utility methods for String. Some of them have been forked from Apache's common-lang 2.4 library.
 */
public class StringUtils {

	private StringUtils() {
	}

	/**
	 * <p>Checks if a String is whitespace, empty ("") or null.</p>
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @param str the String to check, may be null
	 * @return <code>true</code> if the String is null, empty or whitespace
	 */
	public static boolean isBlank(@Nullable String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean areAllBlank(@NonNull String... values) {
		for (String value : values) {
			if (!StringUtils.isBlank(value)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank("")        = false
	 * StringUtils.isNotBlank(" ")       = false
	 * StringUtils.isNotBlank("bob")     = true
	 * StringUtils.isNotBlank("  bob  ") = true
	 * </pre>
	 *
	 * @param str the String to check, may be null
	 * @return <code>true</code> if the String is not empty and not null and not whitespace
	 */
	public static boolean isNotBlank(@Nullable String str) {
		return !StringUtils.isBlank(str);
	}

	public static boolean areAllNotBlank(@NonNull String... values) {
		for (String value : values) {
			if (StringUtils.isBlank(value)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>Removes control characters (char &lt;= 32) from both
	 * ends of this String, handling <code>null</code> by returning
	 * <code>null</code>.</p>
	 * <p>The String is trimmed using {@link String#trim()}.
	 * Trim removes start and end characters &lt;= 32.</p>
	 * <pre>
	 * StringUtils.trim(null)          = null
	 * StringUtils.trim("")            = ""
	 * StringUtils.trim("     ")       = ""
	 * StringUtils.trim("abc")         = "abc"
	 * StringUtils.trim("    abc    ") = "abc"
	 * </pre>
	 *
	 * @param str the String to be trimmed, may be null
	 * @return the trimmed string, <code>null</code> if null String input
	 */
	@Nullable
	public static String trimToNull(@Nullable String str) {
		return StringUtils.isBlank(str) ? null : str.trim();
	}

	@NonNull
	public static String trimToBlank(@Nullable String str) {
		return StringUtils.isBlank(str) ? "" : str.trim();
	}

	@Nullable
	public static String start(@Nullable String str, int maxLength) {
		if (str == null || maxLength < 0)
			return null;

		return str.substring(0, Math.min(maxLength, str.length()));
	}

	@Nullable
	public static String end(@Nullable String str, int maxLength) {
		if (str == null || maxLength < 0)
			return null;

		int length = Math.min(maxLength, str.length());
		return str.substring(str.length() - length, str.length());
	}

	/**
	 * <p>Return a string on which the first letter was turned to upper-case</p>
	 *
	 * @param str the string to modify
	 * @return the modified string, <code>null</code> if null String input
	 */
	@Nullable
	public static String toUpperCaseFirstLetter(@Nullable String str) {
		if (isBlank(str))
			return str;

		if (str.length() == 1) {
			return str.toUpperCase();
		}

		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * Convenience method to return a Collection as a CSV String (comma delimited).
	 * <p/>
	 * Useful for toString() implementations.
	 *
	 * @param entries the Collection to display
	 * @return the delimited String
	 */
	@NonNull
	public static String collectionToCommaDelimitedString(@Nullable Collection<?> entries) {
		return collectionToDelimitedString(entries, ",", -1);
	}

	/**
	 * Convenience method to return a Collection as a String delimited with the given delimiter.
	 * <p/>
	 * Useful for toString() implementations.
	 *
	 * @param entries   the Collection to display
	 * @param delimiter the delimiter to use (probably a ",")
	 * @return the delimited String
	 */
	@NonNull
	public static String collectionToDelimitedString(@Nullable Collection<?> entries, @NonNull String delimiter) {
		return collectionToDelimitedString(entries, delimiter, -1);
	}

	/**
	 * Convenience method to return a Collection as a String delimited with the given delimiter, with a maximum of <code>limit</code> items.
	 * <p/>
	 * Useful for toString() implementations.
	 *
	 * @param entries   the Collection to display
	 * @param delimiter the delimiter to use (probably a ",")
	 * @param limit     the max number of items to use
	 * @return the delimited String
	 */
	@NonNull
	public static String collectionToDelimitedString(@Nullable Collection<?> entries, @NonNull String delimiter, int limit) {
		StringBuilder builder = new StringBuilder();
		if (entries != null) {
			int i = 0;
			Iterator<?> iterator = entries.iterator();
			while (iterator.hasNext()) {
				builder.append(iterator.next());
				if (limit != -1 && ++i >= limit)
					break;
				if (iterator.hasNext())
					builder.append(delimiter);
			}
		}
		return builder.toString();
	}

	/**
	 * Convert a CSV list into an {@link Set} of Strings.
	 *
	 * @param str the input String
	 * @return a {@link Set} of Strings, or an empty {@link Set} in case of empty input
	 */
	@NonNull
	public static Set<String> commaDelimitedStringToSet(@Nullable String str) {
		return delimitedStringToCollection(new TreeSet<String>(), str, ",");
	}

	/**
	 * Convert a CSV list into an {@link List} of Strings.
	 *
	 * @param str the input String
	 * @return a {@link List} of Strings, or an empty {@link List} in case of empty input
	 */
	@NonNull
	public static List<String> commaDelimitedStringToList(@Nullable String str) {
		return delimitedStringToCollection(new LinkedList<String>(), str, ",");
	}

	@NonNull
	private static <E extends Collection<String>> E delimitedStringToCollection(@NonNull E collection, @Nullable String str, @NonNull String delimiter) {
		if (isNotBlank(str)) {
			Collections.addAll(collection, str.split(delimiter));
		}
		return collection;
	}

}

