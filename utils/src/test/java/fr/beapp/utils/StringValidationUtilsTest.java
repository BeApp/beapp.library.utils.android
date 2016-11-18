package fr.beapp.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringValidationUtilsTest extends BaseRobolectric {

	@Test
	public void testIsValidMail() throws Exception {
		assertFalse(StringValidationUtils.isValidMail(null));
		assertFalse(StringValidationUtils.isValidMail(""));
		assertFalse(StringValidationUtils.isValidMail("  "));
		assertFalse(StringValidationUtils.isValidMail("abc"));
		assertFalse(StringValidationUtils.isValidMail("contact@beapp"));

		assertTrue(StringValidationUtils.isValidMail("a@b.fr"));
		assertTrue(StringValidationUtils.isValidMail("contact@beapp.fr"));
	}

	@Test
	public void testIsValidPhone() throws Exception {
		assertFalse(StringValidationUtils.isValidPhone(null));
		assertFalse(StringValidationUtils.isValidPhone(""));
		assertFalse(StringValidationUtils.isValidPhone("  "));
		assertFalse(StringValidationUtils.isValidPhone("abc"));

		assertTrue(StringValidationUtils.isValidPhone("0123456789"));
		assertTrue(StringValidationUtils.isValidPhone("01 23 45 67 89"));
		assertTrue(StringValidationUtils.isValidPhone("01-23-45-67-89"));
		assertTrue(StringValidationUtils.isValidPhone("+33123456789"));
	}

	@Test
	public void testFixURL() throws Exception {
		assertEquals(null, StringValidationUtils.fixURL(null));
		assertEquals(null, StringValidationUtils.fixURL(""));
		assertEquals(null, StringValidationUtils.fixURL("   "));
		assertEquals("http://google.com", StringValidationUtils.fixURL("http://google.com"));
		assertEquals("https://google.com", StringValidationUtils.fixURL("https://google.com"));
		assertEquals("http://google.com", StringValidationUtils.fixURL("  http://google.com  "));
		assertEquals("https://google.com", StringValidationUtils.fixURL("  https://google.com  "));
		assertEquals("http://google.com", StringValidationUtils.fixURL("google.com"));
		assertEquals("http://google.com", StringValidationUtils.fixURL("  google.com  "));
	}
}