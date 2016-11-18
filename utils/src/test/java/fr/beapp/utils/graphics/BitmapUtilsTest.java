package fr.beapp.utils.graphics;

import android.graphics.Bitmap;

import org.junit.Test;

import fr.beapp.utils.BaseRobolectric;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

public class BitmapUtilsTest extends BaseRobolectric {

	@Test
	public void testRecycleQuietly() throws Exception {
		BitmapUtils.recycleQuietly(null);

		Bitmap bitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
		BitmapUtils.recycleQuietly(bitmap);

		BitmapUtils.recycleQuietly(bitmap);
	}

	@Test
	public void testRound() throws Exception {
		// null
		assertNull(BitmapUtils.round(null, 0, 0));

		// Wide
		{
			Bitmap bitmap = Bitmap.createBitmap(100, 15, Bitmap.Config.ARGB_8888);
			assertRoundBitmaps(15, 15, bitmap, BitmapUtils.round(bitmap, 0, 0));
		}

		// Tall
		{
			Bitmap bitmap = Bitmap.createBitmap(15, 100, Bitmap.Config.ARGB_8888);
			assertRoundBitmaps(15, 15, bitmap, BitmapUtils.round(bitmap, 0, 0));
		}

		// Square
		{
			Bitmap bitmap = Bitmap.createBitmap(15, 15, Bitmap.Config.ARGB_8888);
			assertRoundBitmaps(15, 15, bitmap, BitmapUtils.round(bitmap, 0, 0));
		}
	}

	@Test
	public void testRoundRect() throws Exception {
		// null
		assertNull(BitmapUtils.roundRect(null, 0, 0, 0));

		// Wide
		{
			Bitmap bitmap = Bitmap.createBitmap(100, 15, Bitmap.Config.ARGB_8888);
			assertRoundBitmaps(100, 15, bitmap, BitmapUtils.roundRect(bitmap, 0, 0, 0));
		}

		// Tall
		{
			Bitmap bitmap = Bitmap.createBitmap(15, 100, Bitmap.Config.ARGB_8888);
			assertRoundBitmaps(15, 100, bitmap, BitmapUtils.roundRect(bitmap, 0, 0, 0));
		}

		// Square
		{
			Bitmap bitmap = Bitmap.createBitmap(15, 15, Bitmap.Config.ARGB_8888);
			assertRoundBitmaps(15, 15, bitmap, BitmapUtils.roundRect(bitmap, 0, 0, 0));
		}
	}

	private void assertRoundBitmaps(int expectedWidth, int expectedHeight, Bitmap bitmap, Bitmap bitmapRound) {
		try {
			assertNotNull("output not null", bitmapRound);
			assertNotSame("output different than source", bitmap, bitmapRound);
			assertFalse("source not recycled", bitmap.isRecycled());
			assertFalse("output not recycled", bitmapRound.isRecycled());
			assertEquals("correct width", expectedWidth, bitmapRound.getWidth());
			assertEquals("correct height", expectedHeight, bitmapRound.getHeight());

			// TODO Check for bitmap output
		} finally {
			// Cleanup
			BitmapUtils.recycleQuietly(bitmap);
			BitmapUtils.recycleQuietly(bitmapRound);
		}
	}

	@Test
	public void testOverlay() throws Exception {
		// source null
		{
			assertNull(BitmapUtils.overlay(null, null));

			Bitmap overlay = Bitmap.createBitmap(100, 15, Bitmap.Config.ARGB_8888);
			assertNull(BitmapUtils.overlay(null, overlay));
			BitmapUtils.recycleQuietly(overlay);
		}

		// Same size
		{
			Bitmap source = Bitmap.createBitmap(100, 15, Bitmap.Config.ARGB_8888);
			Bitmap overlay = Bitmap.createBitmap(100, 15, Bitmap.Config.ARGB_8888);
			assertOverlayBitmaps(100, 15, source, overlay, BitmapUtils.overlay(source, overlay));
		}

		// Overlay larger
		{
			Bitmap source = Bitmap.createBitmap(100, 15, Bitmap.Config.ARGB_8888);
			Bitmap overlay = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
			assertOverlayBitmaps(100, 15, source, overlay, BitmapUtils.overlay(source, overlay));
		}

		// Overlay smaller
		{
			Bitmap source = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
			Bitmap overlay = Bitmap.createBitmap(100, 15, Bitmap.Config.ARGB_8888);
			assertOverlayBitmaps(100, 100, source, overlay, BitmapUtils.overlay(source, overlay));
		}
	}

	private void assertOverlayBitmaps(int expectedWidth, int expectedHeight, Bitmap source, Bitmap overlay, Bitmap output) {
		try {
			assertNotNull("output not null", output);
			assertNotSame("output different than source", source, output);
			assertNotSame("output different than overlay", overlay, output);
			assertFalse("source not recycled", source.isRecycled());
			assertFalse("overlay not recycled", overlay.isRecycled());
			assertFalse("output not recycled", output.isRecycled());
			assertEquals("correct width", expectedWidth, output.getWidth());
			assertEquals("correct height", expectedHeight, output.getHeight());

			// TODO Check for bitmap output
		} finally {
			// Cleanup
			BitmapUtils.recycleQuietly(source);
			BitmapUtils.recycleQuietly(overlay);
			BitmapUtils.recycleQuietly(output);
		}
	}

	@Test
	public void testDrawTextToBitmap() throws Exception {
		// TODO Test this
	}

	@Test
	public void testFixOrientation() throws Exception {
		// TODO Test this
	}
}