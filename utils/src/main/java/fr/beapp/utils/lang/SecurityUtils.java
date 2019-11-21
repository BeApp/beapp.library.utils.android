package fr.beapp.utils.lang;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class SecurityUtils {

	private SecurityUtils() {
	}

	public static boolean isValidPassword(@Nullable String password, @IntRange(from = 6) int minLength, boolean ensureDigits, boolean ensureMixedCase) {
		if (password == null)
			return false;

		return password.matches("^" +
				(ensureDigits ? "(?=.*[0-9])" : "") +
				(ensureMixedCase ? "(?=.*[A-Z])(?=.*[a-z])" : "") +
				".{" + minLength + ",}" +
				"$");
	}

	/**
	 * Simple String transformation by XOR-ing all characters by value.
	 */
	@NonNull
	public static String xor(@NonNull String value, int key) {
		char[] chars = value.toCharArray();
		for (int j = 0; j < chars.length; j++) {
			chars[j] = (char) (chars[j] ^ key);
		}
		return String.valueOf(chars);
	}

}
