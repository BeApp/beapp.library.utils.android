package fr.beapp.utils.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import fr.beapp.utils.lang.ComparatorUtils;

public class MultiValueMapTest {

	@Test
	public void add() throws Exception {
		MultiValueMap<String, String> map = MultiValueMap.build();

		map.add("k1", "v1.1");
		Assert.assertEquals(Collections.singletonList("v1.1"), map.get("k1"));

		map.add("k2", "v2.1");
		Assert.assertEquals(Collections.singletonList("v1.1"), map.get("k1"));
		Assert.assertEquals(Collections.singletonList("v2.1"), map.get("k2"));

		map.add("k1", "v1.2");
		Assert.assertEquals(Arrays.asList("v1.1", "v1.2"), map.get("k1"));

		map.add("k1", "v1.3");
		map.add("k1", "v1.3");
		Assert.assertEquals(Arrays.asList("v1.1", "v1.2", "v1.3", "v1.3"), map.get("k1"));

		Assert.assertEquals(null, map.get("unknown"));
	}

	@Test
	public void add_null() throws Exception {
		MultiValueMap<String, String> map = MultiValueMap.build();

		map.add("k1", null);
		Assert.assertEquals(Collections.singletonList(null), map.get("k1"));
	}

	@Test
	public void addWithIndex() throws Exception {
		MultiValueMap<String, String> map = MultiValueMap.<String, String>build()
				.add("k1", "v1.1")
				.add("k2", "v2.1")
				.add("k1", "v1.3")
				.add("k1", 1, "v1.2");
		Assert.assertEquals(Arrays.asList("v1.1", "v1.2", "v1.3"), map.get("k1"));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addWithIndex_outOfBounds() throws Exception {
		MultiValueMap.<String, String>build()
				.add("k1", "v1.1")
				.add("k2", "v2.1")
				.add("k1", "v1.3")
				.add("k1", 1, "v1.2")
				.add("k1", 10, "v1.2");
	}

	@Test
	public void removeValue() throws Exception {
		MultiValueMap<String, String> map = MultiValueMap.<String, String>build()
				.add("k1", "v1.1")
				.add("k2", "v2.1")
				.add("k1", "v1.3")
				.add("k1", 1, "v1.2");

		map.removeValue("k1", null);
		Assert.assertEquals(Arrays.asList("v1.1", "v1.2", "v1.3"), map.get("k1"));

		map.removeValue("k1", "unknown");
		Assert.assertEquals(Arrays.asList("v1.1", "v1.2", "v1.3"), map.get("k1"));

		map.removeValue("k1", "v1.2");
		Assert.assertEquals(Arrays.asList("v1.1", "v1.3"), map.get("k1"));

		map.removeValue("k1", "v1.1");
		map.removeValue("k1", "v1.3");
		Assert.assertEquals(Collections.emptyList(), map.get("k1"));
	}

	@Test
	public void get() throws Exception {
		MultiValueMap<String, String> map = MultiValueMap.<String, String>build()
				.add("k1", "v1.1")
				.add("k2", "v2.1")
				.add("k1", "v1.3")
				.add("k1", 1, "v1.2");

		Assert.assertEquals(null, map.get("unknown"));
		Assert.assertEquals(null, map.get(null));
	}

	@Test
	public void get_withDefault() throws Exception {
		MultiValueMap<String, String> map = MultiValueMap.<String, String>build()
				.add("k1", "v1.1")
				.add("k2", "v2.1")
				.add("k1", "v1.3")
				.add("k1", 1, "v1.2");

		Assert.assertEquals(Arrays.asList("v1.1", "v1.2", "v1.3"), map.get("k1", Collections.singletonList("fallback")));
		Assert.assertEquals(Collections.singletonList("fallback"), map.get("unknown", Collections.singletonList("fallback")));
	}

	@Test
	public void getAtIndex() throws Exception {
		MultiValueMap<String, String> map = MultiValueMap.<String, String>build()
				.add("k1", "v1.1")
				.add("k2", "v2.1")
				.add("k1", "v1.3")
				.add("k1", 1, "v1.2");

		Assert.assertEquals("v1.2", map.get("k1", 1));
		Assert.assertEquals("v2.1", map.get("k2", 0));
		Assert.assertEquals(null, map.get("k1", -1));
		Assert.assertEquals(null, map.get("k1", 100));
		Assert.assertEquals(null, map.get("unknown", 0));
		Assert.assertEquals(null, map.get(null, 0));
	}

	@Test
	public void getAtIndex_withDefault() throws Exception {
		MultiValueMap<String, String> map = MultiValueMap.<String, String>build()
				.add("k1", "v1.1")
				.add("k2", "v2.1")
				.add("k1", "v1.3")
				.add("k1", 1, "v1.2");

		Assert.assertEquals("v1.2", map.get("k1", 1, "default"));
		Assert.assertEquals("v2.1", map.get("k2", 0, "default"));
		Assert.assertEquals("default", map.get("k1", -1, "default"));
		Assert.assertEquals("default", map.get("k1", 100, "default"));
		Assert.assertEquals("default", map.get("unknown", 0, "default"));
		Assert.assertEquals("default", map.get(null, 0, "default"));
	}

	@Test
	public void getAllValues() throws Exception {
		MultiValueMap<String, String> map = MultiValueMap.<String, String>build()
				.add("k1", "v1.1")
				.add("k2", "v2.1")
				.add("k1", "v1.3")
				.add("k1", 1, "v1.2")
				.add("k0", "v0");

		Assert.assertEquals(Collections.emptyList(), MultiValueMap.build().getAllValues());
		Assert.assertEquals(Arrays.asList("v0", "v1.1", "v1.2", "v1.3", "v2.1"), map.getAllValues());
	}

	@Test
	public void getAllValuesWithKeyComparator() throws Exception {
		MultiValueMap<String, String> map = MultiValueMap.<String, String>build()
				.add("k-second", "v1")
				.add("k-one", "v2")
				.add("k-second", "v3")
				.add("k-lastone", "v4")
				.add("k-second", 1, "v5");

		Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return ComparatorUtils.compare(o1.length(), o2.length());
			}
		};

		Assert.assertEquals(Collections.emptyList(), MultiValueMap.<String, String>build().getAllValues(comparator));
		Assert.assertEquals(Arrays.asList("v2", "v1", "v5", "v3", "v4"), map.getAllValues(comparator));
		Assert.assertEquals(Arrays.asList("v4", "v2", "v1", "v5", "v3"), map.getAllValues(null));
	}

	@Test
	public void size() throws Exception {
		MultiValueMap<String, String> map = MultiValueMap.<String, String>build()
				.add("k1", "v1.1")
				.add("k2", "v2.1")
				.add("k1", "v1.3")
				.add("k1", 1, "v1.2")
				.add("k0", "v0");

		Assert.assertEquals(0, map.size(null));
		Assert.assertEquals(0, map.size("unknown"));
		Assert.assertEquals(3, map.size("k1"));
		Assert.assertEquals(1, map.size("k2"));
	}

	@Test
	public void fullSize() throws Exception {
		MultiValueMap<String, String> map = MultiValueMap.<String, String>build()
				.add("k1", "v1.1")
				.add("k2", "v2.1")
				.add("k1", "v1.3")
				.add("k1", 1, "v1.2")
				.add("k0", "v0");

		Assert.assertEquals(0, MultiValueMap.<String, String>build().fullSize());
		Assert.assertEquals(5, map.fullSize());
	}
}