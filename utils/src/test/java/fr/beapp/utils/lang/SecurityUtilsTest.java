package fr.beapp.utils.lang;

import org.junit.Test;

import static fr.beapp.utils.lang.SecurityUtils.isValidPassword;
import static fr.beapp.utils.lang.SecurityUtils.xor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SecurityUtilsTest {
	@Test
	public void testIsValidPassword() throws Exception {
		assertFalse(isValidPassword(null, 8, true, true));
		assertFalse(isValidPassword("", 8, true, true));
		assertFalse(isValidPassword("Ab1", 8, true, true));
		assertFalse(isValidPassword("Ab1Cd23", 8, true, true));
		assertFalse(isValidPassword("12345678", 8, true, true));
		assertFalse(isValidPassword("abcdefgh", 8, true, true));
		assertFalse(isValidPassword("ab123fgh", 8, true, true));
		assertFalse(isValidPassword("ABCDEFGH", 8, true, true));
		assertFalse(isValidPassword("AB123FGH", 8, true, true));
		assertFalse(isValidPassword("aBcDeFgH", 8, true, true));

		assertTrue(isValidPassword("Ab1Cd23Ef", 8, true, true));
		assertTrue(isValidPassword("Ab1Cd23Efgh", 8, true, true));
	}

	@Test
	public void testXOR() throws Exception {
		assertEquals("wfpw", xor("test", 3));
		assertEquals("test", xor("wfpw", 3));

		assertEquals("ba`#êäë#{zy", xor("abc éçè xyz", 3));
		assertEquals("abc éçè xyz", xor("ba`#êäë#{zy", 3));
	}

}