package fr.beapp.utils.android.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.Gravity;

public class GravityDrawable extends Drawable {

	private final Drawable drawable;

	private int gravity;
	private int xPadding;
	private int yPadding;

	public GravityDrawable(@NonNull Drawable drawable, int gravity) {
		this(drawable, gravity, 0, 0);
	}

	public GravityDrawable(@NonNull Drawable drawable, int gravity, int xPadding, int yPadding) {
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
}
