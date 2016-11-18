package fr.beapp.utils;

import android.support.annotation.Nullable;
import android.util.Patterns;

public class StringValidationUtils {

	/**
	 * Check that the given mail is valid.
	 *
	 * @param mail the mail to validate
	 * @return true if the mail is a valid one, false otherwise
	 */
	public static boolean isValidMail(@Nullable String mail) {
		return StringUtils.isNotBlank(mail) && Patterns.EMAIL_ADDRESS.matcher(mail).matches();
	}

	/**
	 * Check that the given phone number is valid.
	 *
	 * @param mail the phone to validate
	 * @return true if the phone is a valid one, false otherwise
	 */
	public static boolean isValidPhone(@Nullable String mail) {
		// TODO Find a better regexp
		return StringUtils.isNotBlank(mail) && Patterns.PHONE.matcher(mail).matches();
	}

	/**
	 * Add <code>http://</code> protocol to the given url if needed.
	 *
	 * @param url the URL to fix
	 * @return a fully-formed URL
	 */
	@Nullable
	public static String fixURL(@Nullable String url) {
		if (StringUtils.isBlank(url))
			return null;

		url = StringUtils.trimToBlank(url);

		if (!url.startsWith("http://") && !url.startsWith("https://"))
			url = "http://" + url;

		return url;
	}

}
