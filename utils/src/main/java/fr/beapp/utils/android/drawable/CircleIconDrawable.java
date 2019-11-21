package fr.beapp.utils.android.drawable;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.core.content.ContextCompat;
import android.view.Gravity;

public class CircleIconDrawable extends LayerDrawable {

	private CircleIconDrawable(@NonNull Drawable[] layers) {
		super(layers);
	}

	public static Builder withColorRes(@NonNull Context context, @ColorRes int backgroundColorRes) {
		return new Builder(context, ContextCompat.getColor(context, backgroundColorRes));
	}

	public static Builder withColor(@NonNull Context context, @ColorInt int backgroundColorRes) {
		return new Builder(context, backgroundColorRes);
	}

	@Override
	public int getIntrinsicWidth() {
		return getBounds().width();
	}

	@Override
	public int getIntrinsicHeight() {
		return getBounds().height();
	}

	public static class Builder {
		private final Context context;
		@ColorInt
		private final int backgroundColor;

		private int size = -1;
		private Drawable innerDrawable;

		public Builder(@NonNull Context context, @ColorInt int backgroundColor) {
			this.context = context;
			this.backgroundColor = backgroundColor;
		}

		public Builder withSizeRes(@DimenRes int size) {
			return withSizePx(context.getResources().getDimensionPixelOffset(size));
		}

		public Builder withSizePx(@Px int size) {
			this.size = size;
			return this;
		}

		public Builder withInnerDrawable(@NonNull Drawable drawable) {
			this.innerDrawable = drawable;
			return this;
		}

		public CircleIconDrawable build() {
			ShapeDrawable backgroundDrawable = new ShapeDrawable(new OvalShape());
			backgroundDrawable.getPaint().setColor(backgroundColor);
			backgroundDrawable.setBounds(0, 0, size, size);

			GravityDrawable centeredIconDrawable = new GravityDrawable(innerDrawable, Gravity.CENTER);

			CircleIconDrawable circleIconDrawable = new CircleIconDrawable(new Drawable[]{backgroundDrawable, centeredIconDrawable});
			circleIconDrawable.setBounds(0, 0, size, size);
			return circleIconDrawable;
		}

	}
}
