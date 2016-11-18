package fr.beapp.utils.parser;

import org.junit.Test;

import fr.beapp.utils.BaseRobolectric;

import static org.junit.Assert.assertEquals;

public class ParserUtilsTest extends BaseRobolectric {

	@Test
	public void testParseInteger() throws Exception {
		assertEquals(null, ParserUtils.parseInteger(null));
		assertEquals(null, ParserUtils.parseInteger(""));
		assertEquals(null, ParserUtils.parseInteger("abc"));
		assertEquals(null, ParserUtils.parseInteger("10aa"));

		assertEquals(Integer.valueOf(25), ParserUtils.parseInteger("10aa", 25));

		assertEquals(Integer.valueOf(10), ParserUtils.parseInteger("10"));
		assertEquals(Integer.valueOf(10), ParserUtils.parseInteger("+10"));
		assertEquals(Integer.valueOf(-10), ParserUtils.parseInteger("-10"));
	}

	@Test
	public void testParseLong() throws Exception {
		assertEquals(null, ParserUtils.parseLong(null));
		assertEquals(null, ParserUtils.parseLong(""));
		assertEquals(null, ParserUtils.parseLong("abc"));
		assertEquals(null, ParserUtils.parseLong("10aa"));

		assertEquals(Long.valueOf(25), ParserUtils.parseLong("10aa", 25L));

		assertEquals(Long.valueOf(10), ParserUtils.parseLong("10"));
		assertEquals(Long.valueOf(10), ParserUtils.parseLong("+10"));
		assertEquals(Long.valueOf(-10), ParserUtils.parseLong("-10"));
	}

	@Test
	public void testParseFloat() throws Exception {
		assertEquals(null, ParserUtils.parseFloat(null));
		assertEquals(null, ParserUtils.parseFloat(""));
		assertEquals(null, ParserUtils.parseFloat("abc"));
		assertEquals(null, ParserUtils.parseFloat("10aa"));

		assertEquals(Float.valueOf(25), ParserUtils.parseFloat("10aa", 25.0f));

		assertEquals(Float.valueOf(10), ParserUtils.parseFloat("10"));
		assertEquals(Float.valueOf(10), ParserUtils.parseFloat("10.0"));
		assertEquals(Float.valueOf(10), ParserUtils.parseFloat("+10"));
		assertEquals(Float.valueOf(10), ParserUtils.parseFloat("+10.0"));
		assertEquals(Float.valueOf(-10), ParserUtils.parseFloat("-10"));
		assertEquals(Float.valueOf(-10), ParserUtils.parseFloat("-10.0"));
	}

	@Test
	public void testParseDouble() throws Exception {
		assertEquals(null, ParserUtils.parseDouble(null));
		assertEquals(null, ParserUtils.parseDouble(""));
		assertEquals(null, ParserUtils.parseDouble("abc"));
		assertEquals(null, ParserUtils.parseDouble("10aa"));

		assertEquals(Double.valueOf(25), ParserUtils.parseDouble("10aa", 25.0));

		assertEquals(Double.valueOf(10), ParserUtils.parseDouble("10"));
		assertEquals(Double.valueOf(10), ParserUtils.parseDouble("10.0"));
		assertEquals(Double.valueOf(10), ParserUtils.parseDouble("+10"));
		assertEquals(Double.valueOf(10), ParserUtils.parseDouble("+10.0"));
		assertEquals(Double.valueOf(-10), ParserUtils.parseDouble("-10"));
		assertEquals(Double.valueOf(-10), ParserUtils.parseDouble("-10.0"));
	}

	@Test
	public void testParseBoolean() throws Exception {
		assertEquals(Boolean.FALSE, ParserUtils.parseBoolean(null));
		assertEquals(Boolean.FALSE, ParserUtils.parseBoolean(""));
		assertEquals(Boolean.FALSE, ParserUtils.parseBoolean("abc"));
		assertEquals(Boolean.FALSE, ParserUtils.parseBoolean("10aa"));
		assertEquals(Boolean.FALSE, ParserUtils.parseBoolean("false"));

		assertEquals(Boolean.TRUE, ParserUtils.parseBoolean("true"));
	}

}