package fr.beapp.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringUtilsTest {

	@Test
	public void testIsBlank() throws Exception {
		assertTrue(StringUtils.isBlank((String) null));
		assertTrue(StringUtils.isBlank(""));
		assertTrue(StringUtils.isBlank(" "));
		assertTrue(StringUtils.isBlank("   "));
		assertTrue(StringUtils.isBlank(String.valueOf('\t')));

		assertFalse(StringUtils.isBlank("t"));
		assertFalse(StringUtils.isBlank("test"));
		assertFalse(StringUtils.isBlank("  test  "));
	}

	@Test
	public void testAreAllBlank() throws Exception {
		assertTrue(StringUtils.areAllBlank());
		assertTrue(StringUtils.areAllBlank((String) null));
		assertTrue(StringUtils.areAllBlank(""));
		assertTrue(StringUtils.areAllBlank(" "));
		assertTrue(StringUtils.areAllBlank(" ", "", null, "\t"));

		assertFalse(StringUtils.areAllBlank("t", ""));
		assertFalse(StringUtils.areAllBlank("test", "test"));
		assertFalse(StringUtils.areAllBlank("  test  ", "   "));
	}

	@Test
	public void testIsNotBlank() throws Exception {
		assertFalse(StringUtils.isNotBlank(null));
		assertFalse(StringUtils.isNotBlank(""));
		assertFalse(StringUtils.isNotBlank(" "));
		assertFalse(StringUtils.isNotBlank("   "));
		assertFalse(StringUtils.isNotBlank(String.valueOf('\t')));

		assertTrue(StringUtils.isNotBlank("t"));
		assertTrue(StringUtils.isNotBlank("test"));
		assertTrue(StringUtils.isNotBlank("  test  "));
	}

	@Test
	public void testTrimToBlank() throws Exception {
		assertEquals("", StringUtils.trimToBlank(null));
		assertEquals("", StringUtils.trimToBlank(""));
		assertEquals("", StringUtils.trimToBlank(" "));
		assertEquals("", StringUtils.trimToBlank("    "));
		assertEquals("abc", StringUtils.trimToBlank("abc"));
		assertEquals("abc", StringUtils.trimToBlank("  abc  "));
	}

	@Test
	public void testTrimToNull() throws Exception {
		assertEquals(null, StringUtils.trimToNull(null));
		assertEquals(null, StringUtils.trimToNull(""));
		assertEquals(null, StringUtils.trimToNull(" "));
		assertEquals(null, StringUtils.trimToNull("    "));
		assertEquals("abc", StringUtils.trimToNull("abc"));
		assertEquals("abc", StringUtils.trimToNull("  abc  "));
	}

	@Test
	public void testStart() throws Exception {
		assertEquals(null, StringUtils.start(null, 0));
		assertEquals(null, StringUtils.start("test", -1));
		assertEquals("", StringUtils.start("test", 0));
		assertEquals("tes", StringUtils.start("test", 3));
		assertEquals("test", StringUtils.start("test", 4));
		assertEquals("test", StringUtils.start("test", 5));
	}

	@Test
	public void testEnd() throws Exception {
		assertEquals(null, StringUtils.end(null, 0));
		assertEquals(null, StringUtils.end("test", -1));
		assertEquals("", StringUtils.end("test", 0));
		assertEquals("est", StringUtils.end("test", 3));
		assertEquals("test", StringUtils.end("test", 4));
		assertEquals("test", StringUtils.end("test", 5));
	}

	@Test
	public void testToUpperCaseFirstLetter() throws Exception {
		assertEquals(null, StringUtils.toUpperCaseFirstLetter(null));
		assertEquals("", StringUtils.toUpperCaseFirstLetter(""));
		assertEquals(" ", StringUtils.toUpperCaseFirstLetter(" "));
		assertEquals("A", StringUtils.toUpperCaseFirstLetter("a"));
		assertEquals("Abc", StringUtils.toUpperCaseFirstLetter("abc"));
	}

	@Test
	public void testCollectionToCommaDelimitedString() throws Exception {
		assertEquals("", StringUtils.collectionToCommaDelimitedString(null));
		assertEquals("", StringUtils.collectionToCommaDelimitedString(Collections.emptyList()));
		assertEquals("1", StringUtils.collectionToCommaDelimitedString(Collections.singletonList("1")));
		assertEquals("1,2", StringUtils.collectionToCommaDelimitedString(Arrays.asList("1", "2")));
		assertEquals("1,2", StringUtils.collectionToCommaDelimitedString(Arrays.asList(1, 2)));
		assertEquals("1,2", StringUtils.collectionToCommaDelimitedString(Arrays.asList(new ObjectWithToString(1), new ObjectWithToString(2))));
	}

	@Test
	public void testCollectionToDelimitedString() throws Exception {
		String delimiter = "|";
		assertEquals("", StringUtils.collectionToDelimitedString(null, delimiter));
		assertEquals("", StringUtils.collectionToDelimitedString(Collections.emptyList(), delimiter));
		assertEquals("1", StringUtils.collectionToDelimitedString(Collections.singletonList("1"), delimiter));
		assertEquals("1|2", StringUtils.collectionToDelimitedString(Arrays.asList("1", "2"), delimiter));
		assertEquals("1|2", StringUtils.collectionToDelimitedString(Arrays.asList(1, 2), delimiter));
		assertEquals("1|2", StringUtils.collectionToDelimitedString(Arrays.asList(new ObjectWithToString(1), new ObjectWithToString(2)), delimiter));
	}

	@Test
	public void testCollectionToDelimitedStringLimit() throws Exception {
		String delimiter = "|";
		assertEquals("", StringUtils.collectionToDelimitedString(null, delimiter, 4));
		assertEquals("", StringUtils.collectionToDelimitedString(Collections.emptyList(), delimiter, 4));
		assertEquals("1", StringUtils.collectionToDelimitedString(Collections.singletonList("1"), delimiter, 4));
		assertEquals("1|2", StringUtils.collectionToDelimitedString(Arrays.asList("1", "2"), delimiter, 4));
		assertEquals("1|2", StringUtils.collectionToDelimitedString(Arrays.asList(1, 2), delimiter, 4));
		assertEquals("1|2", StringUtils.collectionToDelimitedString(Arrays.asList(new ObjectWithToString(1), new ObjectWithToString(2)), delimiter, 4));
		assertEquals("1|2|3|4", StringUtils.collectionToDelimitedString(Arrays.asList(1, 2, 3, 4, 5, 6), delimiter, 4));
	}

	@Test
	public void testCommaDelimitedStringToSet() throws Exception {
		assertEquals(TestUtils.newTree(), StringUtils.commaDelimitedStringToSet(null));
		assertEquals(TestUtils.newTree(), StringUtils.commaDelimitedStringToSet(""));
		assertEquals(TestUtils.newTree(), StringUtils.commaDelimitedStringToSet(" "));
		assertEquals(TestUtils.newTree("abc"), StringUtils.commaDelimitedStringToSet("abc"));
		assertEquals(TestUtils.newTree("1", "2"), StringUtils.commaDelimitedStringToSet("1,2"));
	}

	@Test
	public void testCommaDelimitedStringToList() throws Exception {
		assertEquals(Collections.emptyList(), StringUtils.commaDelimitedStringToList(null));
		assertEquals(Collections.emptyList(), StringUtils.commaDelimitedStringToList(""));
		assertEquals(Collections.emptyList(), StringUtils.commaDelimitedStringToList(" "));
		assertEquals(Collections.singletonList("abc"), StringUtils.commaDelimitedStringToList("abc"));
		assertEquals(Arrays.asList("1", "2"), StringUtils.commaDelimitedStringToList("1,2"));
	}

	private class ObjectWithToString {
		private int nb;

		public ObjectWithToString(int nb) {
			this.nb = nb;
		}

		@Override
		public String toString() {
			return String.valueOf(nb);
		}
	}
}