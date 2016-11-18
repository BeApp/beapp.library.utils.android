package fr.beapp.utils.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.media.ExifInterface;
import android.support.annotation.ColorInt;

import fr.beapp.logger.Logger;

public class BitmapUtils {

	/**
	 * Unconditionally recycle a {@link Bitmap}.
	 * <p>
	 * Equivalent to {@link Bitmap#recycle()}, except any exceptions will be ignored.
	 * This is typically used in finally blocks.
	 */
	public static void recycleQuietly(Bitmap bitmap) {
		try {
			if (bitmap != null) {
				bitmap.recycle();
			}
		} catch (Exception ignored) {
		}
	}

	/**
	 * Return a round copy of the given bitmap with a border.
	 * <p>
	 * If border size <= 0, the output image will be a round copy of the input, without border.
	 * <p>
	 * Don't forget to recycle both source and output bitmaps when you're done with it.
	 *
	 * @param source      the bitmap to make a copy of
	 * @param borderWidth width of the border
	 * @param borderColor color of the border
	 * @return a new instance of Bitmap
	 */
	public static Bitmap round(Bitmap source, int borderWidth, int borderColor) {
		if (source == null)
			return null;
		if (borderWidth < 0)
			borderWidth = 0;

		int size = Math.min(source.getWidth(), source.getHeight());

		Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		Paint paint = new Paint();
		paint.setAntiAlias(true);

		if (borderWidth > 0) {
			paint.setColor(borderColor);
			canvas.drawCircle(size / 2, size / 2, size / 2, paint);
		}

		paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
		canvas.drawCircle(size / 2, size / 2, size / 2 - borderWidth, paint);

		return output;
	}

	/**
	 * Return a round-rect copy of the given bitmap with a border.
	 * <p>
	 * If border size is <= 0, the output image will be a round-rect copy of the input, without border.
	 * If border radius is <= 0, the output image will be handled as a rectangle.
	 * <p>
	 * Don't forget to recycle both source and output bitmaps when you're done with it.
	 *
	 * @param source       the bitmap to make a copy of
	 * @param borderWidth  width of the border
	 * @param borderColor  color of the border
	 * @param borderRadius radius of the border
	 * @return a new instance of Bitmap
	 */
	public static Bitmap roundRect(Bitmap source, int borderWidth, int borderColor, int borderRadius) {
		if (source == null)
			return null;
		if (borderRadius < 0)
			borderRadius = 0;

		int width = source.getWidth();
		int height = source.getHeight();
		RectF rect = new RectF(0f, 0f, width, height);

		Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		Paint paint = new Paint();
		paint.setAntiAlias(true);

		if (borderWidth > 0) {
			paint.setColor(borderColor);
			canvas.drawRoundRect(rect, borderRadius, borderRadius, paint);

			rect.inset(borderWidth, borderWidth);
		}

		paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
		canvas.drawRoundRect(rect, borderRadius, borderRadius, paint);

		return output;
	}

	public static Bitmap overlay(Bitmap source, Bitmap overlay) {
		if (source == null)
			return null;
		if (overlay == null)
			return source;

		Paint paint = new Paint();
		paint.setAntiAlias(true);

		Rect sourceRect = new Rect(0, 0, source.getWidth(), source.getHeight());
		Rect destRect = calculateCroppedSrcRect(overlay.getWidth(), overlay.getHeight(), source.getWidth(), source.getHeight());

		Bitmap output = Bitmap.createBitmap(sourceRect.width(), sourceRect.height(), source.getConfig());
		Canvas canvas = new Canvas(output);
		canvas.drawBitmap(source, null, sourceRect, paint);
		canvas.drawBitmap(overlay, null, destRect, paint);

		return output;
	}

	public static Rect calculateCroppedSrcRect(int srcW, int srcH, int dstW, int dstH) {
		final float scale = Math.min(
				(float) srcW / dstW,
				(float) srcH / dstH);

		final int srcCroppedW = Math.round(dstW * scale);
		final int srcCroppedH = Math.round(dstH * scale);

		int left = (srcW - srcCroppedW) / 2;
		int top = (srcH - srcCroppedH) / 2;
		return new Rect(left, top, left + srcCroppedW, top + srcCroppedH);
	}

	/**
	 * TODO Debug this one
	 */
	public static Bitmap drawTextToBitmap(Context context, Bitmap bitmap, String text) {
		Resources resources = context.getResources();
		Bitmap.Config bitmapConfig = bitmap.getConfig();
		if (bitmapConfig == null) {
			bitmapConfig = Bitmap.Config.ARGB_8888;
		}

		Bitmap bitmapWithText = bitmap.copy(bitmapConfig, true);
		Canvas canvas = new Canvas(bitmapWithText);
		Paint textBackground = new Paint();
		textBackground.setColor(Color.BLACK);

		Rect bounds = new Rect();
		textBackground.setAlpha(130);
		canvas.drawRect(0, bitmap.getHeight() - 100, bitmap.getWidth(), bitmap.getHeight() - 20, textBackground);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

		if (text != null) {
			float scale = resources.getDisplayMetrics().density;

			paint.setColor(Color.WHITE);
			paint.setTextSize((int) (14 * scale));
			paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
			paint.getTextBounds(text, 0, text.length(), bounds);
			int x = bounds.height();
			int y = bitmap.getHeight() - bounds.height() - 10;
			canvas.drawText(text, x, y, paint);
		}

		return bitmapWithText;
	}

	/**
	 * Return a new bitmap with orientation fixed. This is really useful to properly handle pictures taken on Samsung devices
	 * <p>
	 * The input bitmap should be recycle.
	 *
	 * @param bitmap the bitmap to rotate
	 * @param exif   the exit associated to this bitmap
	 * @return a new Bitmap instance rotated accordingly to exif data
	 */
	public static Bitmap fixOrientation(Bitmap bitmap, ExifInterface exif) {
		if (bitmap == null)
			return null;
		if (exif == null)
			return bitmap;

		int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
		Logger.info("Fixing rotation for bitmap with orientation %d", orientation);

		Matrix m = new Matrix();
		switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_180:
				m.postRotate(180);
				break;
			case ExifInterface.ORIENTATION_ROTATE_90:
				m.postRotate(90);
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				m.postRotate(270);
				break;
		}

		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
	}

	public static Bitmap applyColor(Bitmap source, @ColorInt int color) {
		ColorFilter colorFilter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP);

		Paint paint = new Paint();
		paint.setColorFilter(colorFilter);

		Bitmap coloredBitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight(), source.getConfig());
		Canvas canvas = new Canvas(coloredBitmap);
		canvas.drawBitmap(source, 0, 0, paint);
		return coloredBitmap;
	}
}
