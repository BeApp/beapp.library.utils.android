package fr.beapp.utils.parser;

import android.support.annotation.Nullable;

import fr.beapp.logger.Logger;

public class ParserUtils {

	private ParserUtils() {
	}

	/**
	 * Safely parses the specified string as a signed decimal integer value. The ASCII characters \u002d ('-') and \u002b ('+') are recognized as the minus and plus signs.
	 *
	 * @param value the string representation of an integer value.
	 * @return the integer object value represented by {@code string} or {@code null} in case of error.
	 */
	@Nullable
	public static Integer parseInteger(@Nullable String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			Logger.warn("Can't parse value %s to Integer", e, value);
		}
		return null;
	}

	/**
	 * Safely parses the specified string as a signed decimal integer value. The ASCII characters \u002d ('-') and \u002b ('+') are recognized as the minus and plus signs.
	 *
	 * @param value        the string representation of an integer value.
	 * @param defaultValue the value to return in case of error.
	 * @return the primitive integer value represented by {@code string} or {@code defaultValue} in case of error.
	 */
	public static int parseInteger(@Nullable String value, int defaultValue) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			Logger.warn("Can't parse value %s to Integer", e, value);
		}
		return defaultValue;
	}

	/**
	 * Safely parses the specified string as a signed decimal long value. The ASCII characters \u002d ('-') and \u002b ('+') are recognized as the minus and plus signs.
	 *
	 * @param value the string representation of a long value.
	 * @return the long object value represented by {@code string} or {@code null} in case of error.
	 */
	@Nullable
	public static Long parseLong(@Nullable String value) {
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			Logger.warn("Can't parse value %s to Long", e, value);
		}
		return null;
	}

	/**
	 * Safely parses the specified string as a signed decimal long value. The ASCII characters \u002d ('-') and \u002b ('+') are recognized as the minus and plus signs.
	 *
	 * @param value        the string representation of a long value.
	 * @param defaultValue the value to return in case of error.
	 * @return the primitive long value represented by {@code string} or {@code defaultValue} in case of error.
	 */
	public static long parseLong(@Nullable String value, long defaultValue) {
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			Logger.warn("Can't parse value %s to Long", e, value);
		}
		return defaultValue;
	}

	/**
	 * Safely parses the specified string as a float value.
	 *
	 * @param value the string representation of a float value.
	 * @return the float object value represented by {@code string} or {@code null} in case of error.
	 */
	@Nullable
	public static Float parseFloat(@Nullable String value) {
		try {
			return Float.parseFloat(value);
		} catch (Exception e) {
			Logger.warn("Can't parse value %s to Float", e, value);
		}
		return null;
	}

	/**
	 * Safely parses the specified string as a float value.
	 *
	 * @param value        the string representation of a float value.
	 * @param defaultValue the value to return in case of error.
	 * @return the primitive float value represented by {@code string} or {@code defaultValue} in case of error.
	 */
	public static float parseFloat(@Nullable String value, float defaultValue) {
		try {
			return Float.parseFloat(value);
		} catch (Exception e) {
			Logger.warn("Can't parse value %s to Float", e, value);
		}
		return defaultValue;
	}

	/**
	 * Safely parses the specified string as a double value.
	 *
	 * @param value the string representation of a double value.
	 * @return the double object value represented by {@code string} or {@code null} in case of error.
	 */
	@Nullable
	public static Double parseDouble(@Nullable String value) {
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			Logger.warn("Can't parse value %s to Double", e, value);
		}
		return null;
	}

	/**
	 * Safely parses the specified string as a double value.
	 *
	 * @param value        the string representation of a double value.
	 * @param defaultValue the value to return in case of error.
	 * @return the primitive double value represented by {@code string} or {@code defaultValue} in case of error.
	 */
	public static double parseDouble(@Nullable String value, double defaultValue) {
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			Logger.warn("Can't parse value %s to Double", e, value);
		}
		return defaultValue;
	}

	/**
	 * Safely parses the specified string as a {@code boolean}.
	 *
	 * @param value the string representation of a boolean value.
	 * @return {@code true} if {@code value} is not {@code null} and is equal to {@code "true"} using case insensitive comparison, {@code false} otherwise.
	 */
	public static boolean parseBoolean(@Nullable String value) {
		return Boolean.parseBoolean(value);
	}

}
