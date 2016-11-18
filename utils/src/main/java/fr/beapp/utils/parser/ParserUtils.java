package fr.beapp.utils.parser;

import android.support.annotation.Nullable;

import fr.beapp.logger.Logger;

public class ParserUtils {

	@Nullable
	public static Integer parseInteger(@Nullable String value) {
		return parseInteger(value, null);
	}

	/**
	 * Safely parses the specified string as a signed decimal integer value. The ASCII characters \u002d ('-') and \u002b ('+') are recognized as the minus and plus signs.
	 *
	 * @param value        the string representation of an integer value.
	 * @param defaultValue the value to return in case of error.
	 * @return the primitive integer value represented by {@code string} or {@code defaultValue} in case of error.
	 */
	@Nullable
	public static Integer parseInteger(@Nullable String value, @Nullable Integer defaultValue) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			Logger.warn("Can't parse value %s to Integer", e, value);
		}
		return defaultValue;
	}

	@Nullable
	public static Long parseLong(@Nullable String value) {
		return parseLong(value, null);
	}

	/**
	 * Safely parses the specified string as a signed decimal long value. The ASCII characters \u002d ('-') and \u002b ('+') are recognized as the minus and plus signs.
	 *
	 * @param value        the string representation of a long value.
	 * @param defaultValue the value to return in case of error.
	 * @return the primitive long value represented by {@code string} or {@code defaultValue} in case of error.
	 */
	@Nullable
	public static Long parseLong(@Nullable String value, @Nullable Long defaultValue) {
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			Logger.warn("Can't parse value %s to Long", e, value);
		}
		return defaultValue;
	}

	@Nullable
	public static Float parseFloat(@Nullable String value) {
		return parseFloat(value, null);
	}

	/**
	 * Safely parses the specified string as a float value.
	 *
	 * @param value        the string representation of a float value.
	 * @param defaultValue the value to return in case of error.
	 * @return the primitive float value represented by {@code string} or {@code defaultValue} in case of error.
	 */
	@Nullable
	public static Float parseFloat(@Nullable String value, @Nullable Float defaultValue) {
		try {
			return Float.parseFloat(value);
		} catch (Exception e) {
			Logger.warn("Can't parse value %s to Float", e, value);
		}
		return defaultValue;
	}

	@Nullable
	public static Double parseDouble(@Nullable String value) {
		return parseDouble(value, null);
	}

	/**
	 * Safely parses the specified string as a double value.
	 *
	 * @param value        the string representation of a double value.
	 * @param defaultValue the value to return in case of error.
	 * @return the primitive double value represented by {@code string} or {@code defaultValue} in case of error.
	 */
	@Nullable
	public static Double parseDouble(@Nullable String value, @Nullable Double defaultValue) {
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
