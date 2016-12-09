package fr.beapp.utils.android.drawable;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Px;
import android.view.Gravity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Display an inner {@link Drawable} with a specific {@link Gravity} within a potentially larger container.
 */
public class GravityDrawable extends Drawable {

	private final Drawable drawable;

	private int gravity;
	private int xPadding;
	private int yPadding;

	public GravityDrawable(@NonNull Drawable drawable, @GravityType int gravity) {
		this(drawable, gravity, 0, 0);
	}

	public GravityDrawable(@NonNull Drawable drawable, @GravityType int gravity, @Px int xPadding, @Px int yPadding) {
		this.drawable = drawable;
		this.gravity = gravity;
		this.xPadding = xPadding;
		this.yPadding = yPadding;
	}

	@Override
	public void draw(@NonNull Canvas canvas) {
		drawable.draw(canvas);
	}

	@Override
	public void setAlpha(int alpha) {
		drawable.setAlpha(alpha);
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		drawable.setColorFilter(cf);
	}

	@Override
	public int getOpacity() {
		return drawable.getOpacity();
	}

	@Override
	protected void onBoundsChange(Rect bounds) {
		super.onBoundsChange(bounds);

		Rect outRect = new Rect();
		Rect dBounds = new Rect(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		Gravity.apply(gravity, dBounds.width(), dBounds.height(), getBounds(), xPadding, yPadding, outRect);
		drawable.setBounds(outRect);
	}

	@SuppressLint("RtlHardcoded")
	@IntDef({
			Gravity.LEFT,
			Gravity.TOP,
			Gravity.RIGHT,
			Gravity.BOTTOM,
			Gravity.START,
			Gravity.END,
			Gravity.CENTER,
			Gravity.CENTER_HORIZONTAL,
			Gravity.CENTER_VERTICAL,
			Gravity.FILL,
			Gravity.FILL_HORIZONTAL,
			Gravity.FILL_VERTICAL,
	})
	@Retention(RetentionPolicy.SOURCE)
	public @interface GravityType {
	}
}
