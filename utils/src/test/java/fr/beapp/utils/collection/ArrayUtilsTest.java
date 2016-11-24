package fr.beapp.utils.collection;

import org.junit.Test;

import fr.beapp.utils.BaseRobolectric;
import fr.beapp.utils.collection.ArrayUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayUtilsTest extends BaseRobolectric {

	@Test
	public void testIsEmpty() throws Exception {
		assertTrue(ArrayUtils.isEmpty(new String[0]));
		assertTrue(ArrayUtils.isEmpty(new String[]{}));

		assertFalse(ArrayUtils.isEmpty(new String[]{"a"}));
		assertFalse(ArrayUtils.isEmpty(new String[]{"a", "b"}));
	}

	@Test
	public void testIsNotEmpty() throws Exception {
		assertFalse(ArrayUtils.isNotEmpty(new String[0]));
		assertFalse(ArrayUtils.isNotEmpty(new String[]{}));

		assertTrue(ArrayUtils.isNotEmpty(new String[]{"a"}));
		assertTrue(ArrayUtils.isNotEmpty(new String[]{"a", "b"}));
	}

}