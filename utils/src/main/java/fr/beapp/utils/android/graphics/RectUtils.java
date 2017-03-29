package fr.beapp.utils.android.graphics;


import android.graphics.Rect;
import android.support.annotation.NonNull;

public class RectUtils {

	private RectUtils() {
	}

	/**
	 * Resize the given {@link Rect} in order to fit the max width and height.
	 * This method keeps the original ratio.
	 */
	@NonNull
	public static Rect resizeToFit(@NonNull Rect srcRect, int maxWidth, int maxHeight) {
		float ratioX = maxWidth / (float) srcRect.width();
		float ratioY = maxHeight / (float) srcRect.height();
		float ratio = Math.min(ratioX, ratioY);
		return new Rect(0, 0, (int) (srcRect.width() * ratio), (int) (srcRect.height() * ratio));
	}

	@NonNull
	public static Rect fitCenter(@NonNull Rect srcRect, @NonNull Rect dstRect) {
		Rect rect = resizeToFit(srcRect, dstRect.width(), dstRect.height());
		rect.offset(dstRect.left + (dstRect.width() - rect.width()) / 2, dstRect.top + (dstRect.height() - rect.height()) / 2);
		return rect;
	}

	@NonNull
	public static Rect fillCenter(@NonNull Rect srcRect, @NonNull Rect dstRect) {
		float ratioX = dstRect.width() / (float) srcRect.width();
		float ratioY = dstRect.height() / (float) srcRect.height();
		float ratio = Math.max(ratioX, ratioY);
		Rect rect = new Rect(0, 0, (int) (srcRect.width() * ratio), (int) (srcRect.height() * ratio));
		rect.offset(dstRect.left + (dstRect.width() - rect.width()) / 2, dstRect.top + (dstRect.height() - rect.height()) / 2);
		return rect;
	}

	/**
	 * Center the source {@link Rect} into the target {@link Rect} and return the result in a new {@link Rect}
	 *
	 * @param srcRect Rect to center
	 * @param dstRect Rect in which the source will be centered
	 * @return a new {@link Rect} copy of source, centered in target
	 */
	@NonNull
	public static Rect centerIn(@NonNull Rect srcRect, @NonNull Rect dstRect) {
		Rect rect = new Rect(srcRect);
		rect.offsetTo(dstRect.left + (dstRect.width() - srcRect.width()) / 2, dstRect.top + (dstRect.height() - srcRect.height()) / 2);
		return rect;
	}

}
