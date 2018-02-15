package fr.beapp.utils.android;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Size;

import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;

import fr.beapp.utils.BaseRobolectric;

public class BundleUtilsTest extends BaseRobolectric {

	public static final double FLOAT_PRECISION = 0.00001;

	@Test
	public void getBoolean() throws Exception {
		Bundle bundle = bundle();
		bundle.putBoolean("key", true);

		Assert.assertTrue(BundleUtils.matchedType(bundle, "key", Boolean.class));
		Assert.assertFalse(BundleUtils.matchedType(bundle, "key", Integer.class));

		Assert.assertEquals(null, BundleUtils.getBoolean(bundle, "unknown"));
		Assert.assertEquals(null, BundleUtils.getBoolean(bundle, "otherKey"));
		Assert.assertEquals(true, BundleUtils.getBoolean(bundle, "key"));
	}

	@Test
	public void getBooleanWithDefault() throws Exception {
		Bundle bundle = bundle();
		bundle.putBoolean("key", true);

		Assert.assertEquals(true, BundleUtils.getBoolean(bundle, "unknown", true));
		Assert.assertEquals(true, BundleUtils.getBoolean(bundle, "otherKey", true));
		Assert.assertEquals(true, BundleUtils.getBoolean(bundle, "key", true));
	}

	@Test
	public void getByte() throws Exception {
		Bundle bundle = bundle();
		bundle.putByte("key", (byte) 5);

		Assert.assertTrue(BundleUtils.matchedType(bundle, "key", Byte.class));
		Assert.assertFalse(BundleUtils.matchedType(bundle, "key", Integer.class));

		Assert.assertEquals(null, BundleUtils.getByte(bundle, "unknown"));
		Assert.assertEquals(null, BundleUtils.getByte(bundle, "otherKey"));
		Assert.assertEquals(Byte.valueOf((byte) 5), BundleUtils.getByte(bundle, "key"));
	}

	@Test
	public void getByteWithDefault() throws Exception {
		Bundle bundle = bundle();
		bundle.putByte("key", (byte) 5);

		Assert.assertEquals((byte) 1, BundleUtils.getByte(bundle, "unknown", (byte) 1));
		Assert.assertEquals((byte) 1, BundleUtils.getByte(bundle, "otherKey", (byte) 1));
		Assert.assertEquals((byte) 5, BundleUtils.getByte(bundle, "key", (byte) 1));
	}

	@Test
	public void getChar() throws Exception {
		Bundle bundle = bundle();
		bundle.putChar("key", 'f');

		Assert.assertTrue(BundleUtils.matchedType(bundle, "key", Character.class));
		Assert.assertFalse(BundleUtils.matchedType(bundle, "key", Integer.class));

		Assert.assertEquals(null, BundleUtils.getChar(bundle, "unknown"));
		Assert.assertEquals(null, BundleUtils.getChar(bundle, "otherKey"));
		Assert.assertEquals(Character.valueOf('f'), BundleUtils.getChar(bundle, "key"));
	}

	@Test
	public void getCharWithDefault() throws Exception {
		Bundle bundle = bundle();
		bundle.putChar("key", 'f');

		Assert.assertEquals('b', BundleUtils.getChar(bundle, "unknown", 'b'));
		Assert.assertEquals('b', BundleUtils.getChar(bundle, "otherKey", 'b'));
		Assert.assertEquals('f', BundleUtils.getChar(bundle, "key", 'b'));
	}

	@Test
	public void getShort() throws Exception {
		Bundle bundle = bundle();
		bundle.putShort("key", (short) 5);

		Assert.assertTrue(BundleUtils.matchedType(bundle, "key", Short.class));
		Assert.assertFalse(BundleUtils.matchedType(bundle, "key", Integer.class));

		Assert.assertEquals(null, BundleUtils.getShort(bundle, "unknown"));
		Assert.assertEquals(null, BundleUtils.getShort(bundle, "otherKey"));
		Assert.assertEquals(Short.valueOf((short) 5), BundleUtils.getShort(bundle, "key"));
	}

	@Test
	public void getShortWithDefault() throws Exception {
		Bundle bundle = bundle();
		bundle.putShort("key", (short) 5);

		Assert.assertEquals((short) 1, BundleUtils.getShort(bundle, "unknown", (short) 1));
		Assert.assertEquals((short) 1, BundleUtils.getShort(bundle, "otherKey", (short) 1));
		Assert.assertEquals((short) 5, BundleUtils.getShort(bundle, "key", (short) 1));
	}

	@Test
	public void getInt() throws Exception {
		Bundle bundle = bundle();
		bundle.putInt("key", 5);

		Assert.assertTrue(BundleUtils.matchedType(bundle, "key", Integer.class));
		Assert.assertFalse(BundleUtils.matchedType(bundle, "key", Short.class));

		Assert.assertEquals(null, BundleUtils.getInt(bundle, "unknown"));
		Assert.assertEquals(null, BundleUtils.getInt(bundle, "otherKey"));
		Assert.assertEquals(Integer.valueOf(5), BundleUtils.getInt(bundle, "key"));
	}

	@Test
	public void getIntWithDefault() throws Exception {
		Bundle bundle = bundle();
		bundle.putInt("key", 5);

		Assert.assertEquals(1, BundleUtils.getInt(bundle, "unknown", 1));
		Assert.assertEquals(1, BundleUtils.getInt(bundle, "otherKey", 1));
		Assert.assertEquals(5, BundleUtils.getInt(bundle, "key", 1));
	}

	@Test
	public void getLong() throws Exception {
		Bundle bundle = bundle();
		bundle.putLong("key", 5L);

		Assert.assertTrue(BundleUtils.matchedType(bundle, "key", Long.class));
		Assert.assertFalse(BundleUtils.matchedType(bundle, "key", Short.class));

		Assert.assertEquals(null, BundleUtils.getLong(bundle, "unknown"));
		Assert.assertEquals(null, BundleUtils.getLong(bundle, "otherKey"));
		Assert.assertEquals(Long.valueOf(5L), BundleUtils.getLong(bundle, "key"));
	}

	@Test
	public void getLongWithDefault() throws Exception {
		Bundle bundle = bundle();
		bundle.putLong("key", 5L);

		Assert.assertEquals(1L, BundleUtils.getLong(bundle, "unknown", 1L));
		Assert.assertEquals(1L, BundleUtils.getLong(bundle, "otherKey", 1L));
		Assert.assertEquals(5L, BundleUtils.getLong(bundle, "key", 1L));
	}

	@Test
	public void getFloat() throws Exception {
		Bundle bundle = bundle();
		bundle.putFloat("key", 5f);

		Assert.assertTrue(BundleUtils.matchedType(bundle, "key", Float.class));
		Assert.assertFalse(BundleUtils.matchedType(bundle, "key", Short.class));

		Assert.assertEquals(null, BundleUtils.getFloat(bundle, "unknown"));
		Assert.assertEquals(null, BundleUtils.getFloat(bundle, "otherKey"));
		Assert.assertEquals(Float.valueOf(5f), BundleUtils.getFloat(bundle, "key"));
	}

	@Test
	public void getFloatWithDefault() throws Exception {
		Bundle bundle = bundle();
		bundle.putFloat("key", 5f);

		Assert.assertEquals(1f, BundleUtils.getFloat(bundle, "unknown", 1f), FLOAT_PRECISION);
		Assert.assertEquals(1f, BundleUtils.getFloat(bundle, "otherKey", 1f), FLOAT_PRECISION);
		Assert.assertEquals(5f, BundleUtils.getFloat(bundle, "key", 1f), FLOAT_PRECISION);
	}

	@Test
	public void getDouble() throws Exception {
		Bundle bundle = bundle();
		bundle.putDouble("key", 5.0);

		Assert.assertTrue(BundleUtils.matchedType(bundle, "key", Double.class));
		Assert.assertFalse(BundleUtils.matchedType(bundle, "key", Short.class));

		Assert.assertEquals(null, BundleUtils.getDouble(bundle, "unknown"));
		Assert.assertEquals(null, BundleUtils.getDouble(bundle, "otherKey"));
		Assert.assertEquals(Double.valueOf(5.0), BundleUtils.getDouble(bundle, "key"));
	}

	@Test
	public void getDoubleWithDefault() throws Exception {
		Bundle bundle = bundle();
		bundle.putDouble("key", 5.0);

		Assert.assertEquals(1.0, BundleUtils.getDouble(bundle, "unknown", 1.0), FLOAT_PRECISION);
		Assert.assertEquals(1.0, BundleUtils.getDouble(bundle, "otherKey", 1.0), FLOAT_PRECISION);
		Assert.assertEquals(5.0, BundleUtils.getDouble(bundle, "key", 1.0), FLOAT_PRECISION);
	}

	@Test
	public void getString() throws Exception {
		Bundle bundle = bundle();
		bundle.putString("key", "value");

		Assert.assertTrue(BundleUtils.matchedType(bundle, "key", String.class));
		Assert.assertFalse(BundleUtils.matchedType(bundle, "key", Short.class));

		Assert.assertEquals(null, BundleUtils.getString(bundle, "unknown"));
		Assert.assertEquals(null, BundleUtils.getString(bundle, "otherKey"));
		Assert.assertEquals("value", BundleUtils.getString(bundle, "key"));
	}

	@Test
	public void getStringWithDefault() throws Exception {
		Bundle bundle = bundle();
		bundle.putString("key", "value");

		Assert.assertEquals("default", BundleUtils.getString(bundle, "unknown", "default"));
		Assert.assertEquals("default", BundleUtils.getString(bundle, "otherKey", "default"));
		Assert.assertEquals("value", BundleUtils.getString(bundle, "key", "default"));
	}

	@Test
	public void getSerializable() throws Exception {
		SerializableObject expectedValue = new SerializableObject();

		Bundle bundle = bundle();
		bundle.putSerializable("key", expectedValue);

		Assert.assertTrue(BundleUtils.matchedType(bundle, "key", Serializable.class));
		Assert.assertFalse(BundleUtils.matchedType(bundle, "key", Short.class));

		Assert.assertEquals(null, BundleUtils.getSerializable(bundle, "unknown"));
		Assert.assertEquals(null, BundleUtils.getSerializable(bundle, "otherKey"));
		Assert.assertEquals(expectedValue, BundleUtils.getSerializable(bundle, "key"));
	}

	@Test
	public void getSerializableWithDefault() throws Exception {
		SerializableObject expectedValue = new SerializableObject();
		SerializableObject otherdValue = new SerializableObject();

		Bundle bundle = bundle();
		bundle.putSerializable("key", expectedValue);

		Assert.assertEquals(otherdValue, BundleUtils.getSerializable(bundle, "unknown", otherdValue));
		Assert.assertEquals(otherdValue, BundleUtils.getSerializable(bundle, "otherKey", otherdValue));
		Assert.assertEquals(expectedValue, BundleUtils.getSerializable(bundle, "key", otherdValue));
	}

	@Test
	public void getParcelable() throws Exception {
		ParcelableObject expectedValue = new ParcelableObject();

		Bundle bundle = bundle();
		bundle.putParcelable("key", expectedValue);

		Assert.assertTrue(BundleUtils.matchedType(bundle, "key", Parcelable.class));
		Assert.assertFalse(BundleUtils.matchedType(bundle, "key", Short.class));

		Assert.assertEquals(null, BundleUtils.getParcelable(bundle, "unknown"));
		Assert.assertEquals(null, BundleUtils.getParcelable(bundle, "otherKey"));
		Assert.assertEquals(expectedValue, BundleUtils.getParcelable(bundle, "key"));
	}

	@Test
	public void getParcelableWithDefault() throws Exception {
		ParcelableObject expectedValue = new ParcelableObject();
		ParcelableObject otherdValue = new ParcelableObject();

		Bundle bundle = bundle();
		bundle.putParcelable("key", expectedValue);

		Assert.assertEquals(otherdValue, BundleUtils.getParcelable(bundle, "unknown", otherdValue));
		Assert.assertEquals(otherdValue, BundleUtils.getParcelable(bundle, "otherKey", otherdValue));
		Assert.assertEquals(expectedValue, BundleUtils.getParcelable(bundle, "key", otherdValue));
	}

	private class SerializableObject implements Serializable {
	}

	private class ParcelableObject implements Parcelable {
		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
		}
	}

	private Bundle bundle() {
		Bundle bundle = new Bundle();
		bundle.putSize("otherKey", new Size(0, 0));    // We just needed a type not covered by BundleUtils
		return bundle;
	}
}