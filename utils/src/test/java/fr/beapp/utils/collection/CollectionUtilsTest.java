package fr.beapp.utils.collection;

import org.junit.Test;

import fr.beapp.utils.BaseRobolectric;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectionUtilsTest extends BaseRobolectric {

	@Test
	public void testIsEmpty() throws Exception {
		assertTrue(CollectionUtils.isEmpty(null));
		assertTrue(CollectionUtils.isEmpty(emptyList()));

		assertFalse(CollectionUtils.isEmpty(singletonList("a")));
		assertFalse(CollectionUtils.isEmpty(asList("a", "b")));
	}

	@Test
	public void testIsNotEmpty() throws Exception {
		assertFalse(CollectionUtils.isNotEmpty(null));
		assertFalse(CollectionUtils.isNotEmpty(emptyList()));

		assertTrue(CollectionUtils.isNotEmpty(singletonList("a")));
		assertTrue(CollectionUtils.isNotEmpty(asList("a", "b")));
	}

	@Test
	public void testHasOneItem() throws Exception {
		assertFalse(CollectionUtils.hasOneItem(null));
		assertFalse(CollectionUtils.hasOneItem(emptyList()));
		assertFalse(CollectionUtils.hasOneItem(asList("a", "b")));

		assertTrue(CollectionUtils.hasOneItem(singletonList("a")));
	}

	@Test
	public void testHasItemsCount() throws Exception {
		assertFalse(CollectionUtils.hasItemsCount(null, 1));
		assertFalse(CollectionUtils.hasItemsCount(emptyList(), 1));
		assertFalse(CollectionUtils.hasItemsCount(asList("a", "b"), 1));
		assertFalse(CollectionUtils.hasItemsCount(asList("a", "b"), -1));

		assertTrue(CollectionUtils.hasItemsCount(emptyList(), 0));
		assertTrue(CollectionUtils.hasItemsCount(singletonList("a"), 1));
		assertTrue(CollectionUtils.hasItemsCount(asList("a", "b"), 2));
	}

	@Test
	public void testHasItemAt() throws Exception {
		assertFalse(CollectionUtils.hasItemAt(null, 0));
		assertFalse(CollectionUtils.hasItemAt(null, -1));
		assertFalse(CollectionUtils.hasItemAt(null, 10));
		assertFalse(CollectionUtils.hasItemAt(emptyList(), 0));
		assertFalse(CollectionUtils.hasItemAt(emptyList(), -1));
		assertFalse(CollectionUtils.hasItemAt(emptyList(), 10));
		assertFalse(CollectionUtils.hasItemAt(asList("a", "b"), -1));
		assertFalse(CollectionUtils.hasItemAt(asList("a", "b"), 10));

		assertTrue(CollectionUtils.hasItemAt(singletonList("a"), 0));
		assertTrue(CollectionUtils.hasItemAt(asList("a", "b"), 1));
	}

	@Test
	public void testGetAt() throws Exception {
		String defaultValue = "default";

		assertEquals(defaultValue, CollectionUtils.getItemAt(null, 0, defaultValue));
		assertEquals(defaultValue, CollectionUtils.getItemAt(null, -1, defaultValue));
		assertEquals(defaultValue, CollectionUtils.getItemAt(null, 10, defaultValue));
		assertEquals(defaultValue, CollectionUtils.getItemAt(emptyList(), 0, defaultValue));
		assertEquals(defaultValue, CollectionUtils.getItemAt(emptyList(), -1, defaultValue));
		assertEquals(defaultValue, CollectionUtils.getItemAt(emptyList(), 10, defaultValue));
		assertEquals(defaultValue, CollectionUtils.getItemAt(asList("a", "b"), -1, defaultValue));
		assertEquals(defaultValue, CollectionUtils.getItemAt(asList("a", "b"), 10, defaultValue));

		assertEquals("a", CollectionUtils.getItemAt(singletonList("a"), 0, defaultValue));
		assertEquals("b", CollectionUtils.getItemAt(asList("a", "b"), 1, defaultValue));
	}

}