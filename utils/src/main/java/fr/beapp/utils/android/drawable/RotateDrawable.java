package fr.beapp.utils.android.drawable;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.view.animation.LinearInterpolator;

/**
 * A drawable which can rotate it's embedded drawable
 */
public class RotateDrawable extends DrawableWrapper implements Animatable {

	public static final int DEFAULT_ROTATION_DURATION = 1500;

	private final ValueAnimator rotateAnimation;

	private int centerX;
	private int centerY;
	private float rotation;

	public RotateDrawable(@NonNull Drawable drawable) {
		super(drawable);

		rotateAnimation = ValueAnimator.ofFloat(0, 360);
		rotateAnimation.setDuration(DEFAULT_ROTATION_DURATION);
		rotateAnimation.setInterpolator(new LinearInterpolator());
		rotateAnimation.setRepeatCount(ValueAnimator.INFINITE);
		rotateAnimation.setRepeatMode(ValueAnimator.RESTART);
		rotateAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				rotation = (float) animation.getAnimatedValue();
				RotateDrawable.this.invalidateSelf();
			}
		});
		rotateAnimation.start();
	}

	@Override
	protected void onBoundsChange(Rect bounds) {
		super.onBoundsChange(bounds);
		centerX = bounds.centerX();
		centerY = bounds.centerY();
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.save();
		canvas.rotate(rotation, centerX, centerY);
		super.draw(canvas);
		canvas.restore();
	}

	@Override
	public void start() {
		if (!isRunning()) {
			rotateAnimation.start();
		}
	}

	@Override
	public void stop() {
		if (isRunning()) {
			rotateAnimation.cancel();
		}
	}

	@Override
	public boolean isRunning() {
		return rotateAnimation.isRunning();
	}

	@NonNull
	public ValueAnimator getRotateAnimation() {
		return rotateAnimation;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
}
