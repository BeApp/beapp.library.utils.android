package fr.beapp.utils.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class MapBuilderTest {

	@Test
	public void add() throws Exception {
		HashMap<Object, Object> expectedMap = new HashMap<>();
		expectedMap.put("a", 1);
		expectedMap.put("b", 2);

		Assert.assertEquals(expectedMap, MapBuilder.builder()
				.add("a", 1)
				.add("b", 123)
				.add("b", 2)
				.add("c", null)
				.add("b", null));
	}

}