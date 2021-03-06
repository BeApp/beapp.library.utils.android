package fr.beapp.utils.android.graphics;

import android.annotation.TargetApi;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.ColorInt;

public class DrawableUtils {

	private DrawableUtils() {
	}

	/**
	 * Apply a color to the given drawable.
	 *
	 * @param drawable the drawable to modify
	 * @param color    the color to apply to the drawable
	 */
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public static void applyColor(Drawable drawable, @ColorInt int color) {
		if (drawable == null)
			return;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			drawable.setTint(color);
		} else {
			drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
		}
	}

	/**
	 * Apply a black & white filter to the given drawable.
	 *
	 * @param drawable the drawable to modify
	 */
	public static void applyBlackAndWhiteColor(Drawable drawable) {
		if (drawable == null)
			return;

		final ColorMatrix matrixA = new ColorMatrix();
		matrixA.setSaturation(0);

		drawable.setColorFilter(new ColorMatrixColorFilter(matrixA));
	}

	/**
	 * Apply a sepia filter to the given drawable.
	 *
	 * @param drawable the drawable to modify
	 */
	public static void applySepiaColor(Drawable drawable) {
		if (drawable == null)
			return;

		final ColorMatrix matrixA = new ColorMatrix();
		matrixA.setSaturation(0);

		final ColorMatrix matrixB = new ColorMatrix();
		matrixB.setScale(1f, .95f, .82f, 1.0f);
		matrixA.setConcat(matrixB, matrixA);

		drawable.setColorFilter(new ColorMatrixColorFilter(matrixA));
	}

	/**
	 * Remove any color filter applied to the given drawable
	 *
	 * @param drawable the drawable to clear from color filter
	 */
	public static void removeColorFilter(Drawable drawable) {
		if (drawable == null)
			return;

		drawable.setColorFilter(null);
	}

}
