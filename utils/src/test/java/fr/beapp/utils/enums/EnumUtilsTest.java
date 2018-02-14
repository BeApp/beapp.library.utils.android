package fr.beapp.utils.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnumUtilsTest {

	@Test
	public void testFromKey() throws Exception {
		assertEquals(null, EnumUtils.fromKey(TestEnum.values(), null));
		assertEquals(null, EnumUtils.fromKey(TestEnum.values(), "unknown"));

		assertEquals(TestEnum.VALUE_2, EnumUtils.fromKey(TestEnum.values(), "val2"));
		assertEquals(TestEnum.VALUE_2, EnumUtils.fromKey(TestEnum.values(), "VAL2"));
	}

	@Test
	public void testFromKeyWithDefault() throws Exception {
		assertEquals(TestEnum.VALUE_3, EnumUtils.fromKey(TestEnum.values(), null, TestEnum.VALUE_3));
		assertEquals(TestEnum.VALUE_3, EnumUtils.fromKey(TestEnum.values(), "unknown", TestEnum.VALUE_3));

		assertEquals(TestEnum.VALUE_2, EnumUtils.fromKey(TestEnum.values(), "val2", TestEnum.VALUE_3));
		assertEquals(TestEnum.VALUE_2, EnumUtils.fromKey(TestEnum.values(), "VAL2", TestEnum.VALUE_3));
	}

	@Test
	public void testFromName() throws Exception {
		assertEquals(null, EnumUtils.fromName(TestEnum.class, null));
		assertEquals(null, EnumUtils.fromName(TestEnum.class, "unknown"));
		assertEquals(null, EnumUtils.fromName(TestEnum.class, "value_2"));

		assertEquals(TestEnum.VALUE_2, EnumUtils.fromName(TestEnum.class, "VALUE_2"));
	}

	@Test
	public void testFromNameithDefault() throws Exception {
		assertEquals(TestEnum.VALUE_3, EnumUtils.fromName(TestEnum.class, null, TestEnum.VALUE_3));
		assertEquals(TestEnum.VALUE_3, EnumUtils.fromName(TestEnum.class, "unknown", TestEnum.VALUE_3));
		assertEquals(TestEnum.VALUE_3, EnumUtils.fromName(TestEnum.class, "value_2", TestEnum.VALUE_3));

		assertEquals(TestEnum.VALUE_2, EnumUtils.fromName(TestEnum.class, "VALUE_2", TestEnum.VALUE_3));
	}

	public enum TestEnum implements EnumFromWS {
		VALUE_1("val1"),
		VALUE_2("val2"),
		VALUE_3("val3");

		private String key;

		TestEnum(String key) {
			this.key = key;
		}

		@Override
		public String getKey() {
			return key;
		}
	}
}