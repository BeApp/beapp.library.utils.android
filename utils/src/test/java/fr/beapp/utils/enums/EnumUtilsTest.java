package fr.beapp.utils.enums;

import org.junit.Test;

import fr.beapp.utils.enums.EnumFromWS;
import fr.beapp.utils.enums.EnumUtils;

import static org.junit.Assert.*;

public class EnumUtilsTest {

	@Test
	public void testFromKey() throws Exception {
		assertEquals(null, EnumUtils.fromKey(TestEnum.values(), null));
		assertEquals(null, EnumUtils.fromKey(TestEnum.values(), "unknown"));

		assertEquals(TestEnum.VALUE_2, EnumUtils.fromKey(TestEnum.values(), "val2"));
		assertEquals(TestEnum.VALUE_2, EnumUtils.fromKey(TestEnum.values(), "VAL2"));
	}

	@Test
	public void testFromKey2() throws Exception {
		assertEquals(TestEnum.VALUE_3, EnumUtils.fromKey(TestEnum.values(), null, TestEnum.VALUE_3));
		assertEquals(TestEnum.VALUE_3, EnumUtils.fromKey(TestEnum.values(), "unknown", TestEnum.VALUE_3));

		assertEquals(TestEnum.VALUE_2, EnumUtils.fromKey(TestEnum.values(), "val2", TestEnum.VALUE_3));
		assertEquals(TestEnum.VALUE_2, EnumUtils.fromKey(TestEnum.values(), "VAL2", TestEnum.VALUE_3));
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