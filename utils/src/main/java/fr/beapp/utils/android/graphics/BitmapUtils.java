package fr.beapp.utils.android.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.media.ExifInterface;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import fr.beapp.logger.Logger;
import fr.beapp.utils.android.ViewUtils;

public class BitmapUtils {

	private BitmapUtils() {
	}

	/**
	 * Unconditionally recycle a {@link Bitmap}.
	 * <p>
	 * Equivalent to {@link Bitmap#recycle()}, except any exceptions will be ignored.
	 * This is typically used in finally blocks.
	 *
	 * @param bitmap The bitmap to recycle
	 */
	public static void recycleQuietly(@Nullable Bitmap bitmap) {
		try {
			if (bitmap != null && !bitmap.isRecycled()) {
				bitmap.recycle();
			}
		} catch (Exception ignored) {
		}
	}

	/**
	 * Resize the given bitmap with a ratio to apply on both width and height.
	 * <p/>
	 * This method will generate a new bitmap.
	 * <p/>
	 * The develop MAY wan to recycle the source bitmap if it's no longer used.
	 *
	 * @param source the {@link Bitmap} to resize
	 * @param ratio  the ratio to apply on his size
	 * @return the resized bitmap, or <code>null</code> if the source was <code>null</code>
	 */
	@Nullable
	public static Bitmap resize(@Nullable Bitmap source, float ratio) {
		if (source == null)
			return null;

		int width = Math.round(ratio * source.getWidth());
		int height = Math.round(ratio * source.getHeight());

		return Bitmap.createScaledBitmap(source, width, height, true);
	}

	/**
	 * Return a round copy of the given bitmap with an optional border.
	 * <p>
	 * If border size <= 0, the output image will be a round copy of the input, without border.
	 * <p>
	 * This method will generate a new bitmap.
	 * <p/>
	 * The develop MAY wan to recycle the source bitmap if it's no longer used.
	 *
	 * @param source      the bitmap to make a copy of
	 * @param borderWidth width of the border
	 * @param borderColor color of the border
	 * @return a round copy of the source Bitmap
	 */
	@Nullable
	public static Bitmap round(@Nullable Bitmap source, int borderWidth, int borderColor) {
		if (source == null)
			return null;

		borderWidth = Math.max(0, borderWidth);
		int size = Math.min(source.getWidth(), source.getHeight());

		Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		Paint paint = new Paint();
		paint.setAntiAlias(true);

		if (borderWidth > 0) {
			paint.setColor(borderColor);
			canvas.drawCircle(size / 2f, size / 2f, size / 2f, paint);
		}

		paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
		canvas.drawCircle(size / 2f, size / 2f, size / 2f - borderWidth, paint);

		return output;
	}

	/**
	 * Return a round-rect copy of the given bitmap with a border.
	 * <p>
	 * If border size is <= 0, the output image will be a round-rect copy of the input, without border.
	 * If border radius is <= 0, the output image will be handled as a rectangle.
	 * <p>
	 * This method will generate a new bitmap.
	 * <p/>
	 * The develop MAY wan to recycle the source bitmap if it's no longer used.
	 *
	 * @param source       the bitmap to make a copy of
	 * @param borderWidth  width of the border
	 * @param borderColor  color of the border
	 * @param borderRadius radius of the border
	 * @return a rounded copy of the source Bitmap
	 */
	@Nullable
	public static Bitmap roundRect(@Nullable Bitmap source, int borderWidth, int borderColor, int borderRadius) {
		if (source == null)
			return null;

		borderWidth = Math.max(0, borderWidth);
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

	/**
	 * Overlay two bitmap on a new one.
	 * <p/>
	 * The overlay bitmap will be center cropped on the source bitmap.
	 * <p/>
	 * If the source bitmap is <code>null</code>, <code>null</code> will be returned
	 * If the overlay bitmap is <code>null</code>, the original source bitmap will be returned.
	 * <p/>
	 * This method will generate a new bitmap.
	 * <p/>
	 * The develop MAY wan to recycle the source bitmap if it's no longer used.
	 *
	 * @param source  the source bitmap
	 * @param overlay the bitmap to overlay on the source
	 * @return a new bitmap with the source and overlay merged, or <code>null</code> if source was <code>null</code>
	 */
	@Nullable
	public static Bitmap overlay(@Nullable Bitmap source, @Nullable Bitmap overlay) {
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

	@NonNull
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
	 * Return a new bitmap with orientation fixed. This is really useful to properly handle pictures taken on Samsung devices
	 * <p>
	 * This method will generate a new bitmap or return the given one if exif is null.
	 * <p/>
	 * The develop MAY want to recycle the source bitmap if it's no longer used.
	 *
	 * @param bitmap the bitmap to rotate
	 * @param exif   the exit associated to this bitmap, or <code>null</code>
	 * @return a new Bitmap instance rotated accordingly to exif data, or the given Bitmap if exif was <code>null</code>
	 */
	@NonNull
	public static Bitmap fixOrientation(@NonNull Bitmap bitmap, @Nullable ExifInterface exif) {
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
			default:
				// No rotation to apply
		}

		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
	}

	/**
	 * Apply a color overlay on the given bitmap using <a href="http://ssp.impulsetrain.com/porterduff.html">SRC_ATOP PorterDuff strategy</a>.
	 * <p/>
	 * This method will generate a new bitmap.
	 * <p/>
	 * The develop MAY wan to recycle the source bitmap if it's no longer used.
	 *
	 * @param source the bitmap on which to apply the color
	 * @param color  the color to apply
	 * @return a new bitmap with the color applied, or <code>null</code> if source was <code>null</code>
	 */
	@Nullable
	public static Bitmap applyColor(@Nullable Bitmap source, @ColorInt int color) {
		if (source == null)
			return null;

		Paint paint = new Paint();
		paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));

		Bitmap coloredBitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight(), source.getConfig());
		Canvas canvas = new Canvas(coloredBitmap);
		canvas.drawBitmap(source, 0, 0, paint);
		return coloredBitmap;
	}

	/**
	 * Generate a {@link Bitmap} from a given {@link View}.
	 *
	 * @param view the View to convert to Bitmap
	 * @return the generated Bitmap
	 */
	@NonNull
	public static Bitmap fromView(@NonNull View view) {
		if (view.getMeasuredWidth() == 0 || view.getMeasuredHeight() == 0) {
			ViewUtils.forceMeasure(view);
		}

		int marginLeft = 0;
		int marginTop = 0;
		int marginRight = 0;
		int marginBottom = 0;

		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
			ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
			marginLeft = marginLayoutParams.leftMargin;
			marginTop = marginLayoutParams.topMargin;
			marginRight = marginLayoutParams.rightMargin;
			marginBottom = marginLayoutParams.bottomMargin;
		}

		try {
			Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth() + marginLeft + marginRight,
					view.getMeasuredHeight() + marginTop + marginBottom,
					Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			canvas.translate(marginLeft, marginTop);
			view.draw(canvas);
			view.setDrawingCacheEnabled(true);
			return bitmap;
		} finally {
			view.destroyDrawingCache();
		}
	}

	/**
	 * Draw a {@link View} on the given {@link Bitmap}.
	 *
	 * @param view   the View to draw on Bitmap
	 * @param bitmap the Bitmap to use as canvas
	 */
	public static void fromView(@NonNull View view, @NonNull Bitmap bitmap) {
		if (view.getMeasuredWidth() == 0 || view.getMeasuredHeight() == 0) {
			ViewUtils.forceMeasure(view);
		}

		Canvas canvas = new Canvas(bitmap);
		try {
			view.draw(canvas);
			view.setDrawingCacheEnabled(true);
		} finally {
			view.destroyDrawingCache();
		}
	}
}
