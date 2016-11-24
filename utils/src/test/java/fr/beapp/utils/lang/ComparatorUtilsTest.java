package fr.beapp.utils.lang;

import org.junit.Assert;
import org.junit.Test;

import fr.beapp.utils.lang.ComparatorUtils;

public class ComparatorUtilsTest {

	@Test
	public void testCompare_int() throws Exception {
		Assert.assertEquals(-1, ComparatorUtils.compare(1, 5));
		Assert.assertEquals(0, ComparatorUtils.compare(1, 1));
		Assert.assertEquals(1, ComparatorUtils.compare(5, 1));
	}

	@Test
	public void testCompare_long() throws Exception {
		Assert.assertEquals(-1, ComparatorUtils.compare(1L, 5L));
		Assert.assertEquals(0, ComparatorUtils.compare(1L, 1L));
		Assert.assertEquals(1, ComparatorUtils.compare(5L, 1L));
	}

	@Test
	public void testCompare_short() throws Exception {
		Assert.assertEquals(-1, ComparatorUtils.compare((short) 1, (short) 5));
		Assert.assertEquals(0, ComparatorUtils.compare((short) 1, (short) 1));
		Assert.assertEquals(1, ComparatorUtils.compare((short) 5, (short) 1));
	}

	@Test
	public void testCompare_byte() throws Exception {
		Assert.assertEquals(-1, ComparatorUtils.compare((byte) 1, (byte) 5));
		Assert.assertEquals(0, ComparatorUtils.compare((byte) 1, (byte) 1));
		Assert.assertEquals(1, ComparatorUtils.compare((byte) 5, (byte) 1));
	}

	@Test
	public void testCompare_char() throws Exception {
		Assert.assertEquals(-4, ComparatorUtils.compare('a', 'e'));
		Assert.assertEquals(0, ComparatorUtils.compare('a', 'a'));
		Assert.assertEquals(4, ComparatorUtils.compare('e', 'a'));
	}

	@Test
	public void testCompare_boolean() throws Exception {
		Assert.assertEquals(-1, ComparatorUtils.compare(false, true));
		Assert.assertEquals(0, ComparatorUtils.compare(true, true));
		Assert.assertEquals(1, ComparatorUtils.compare(true, false));
	}
}