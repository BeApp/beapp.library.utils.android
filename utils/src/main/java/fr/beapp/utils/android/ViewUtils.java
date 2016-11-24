package fr.beapp.utils.android;

import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

import fr.beapp.utils.graphics.ColorUtils;
import fr.beapp.utils.graphics.DrawableUtils;
import fr.beapp.utils.graphics.SelectorPressedType;

public class ViewUtils {

	private ViewUtils() {
	}

	private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

	/**
	 * Inspired from <a href="http://stackoverflow.com/a/15442898/815737">http://stackoverflow.com/a/15442898/815737</a>
	 *
	 * @return a unique Id for programmatically created views
	 */
	public static int generateViewId() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
			return View.generateViewId();
		}

		for (; ; ) {
			final int result = sNextGeneratedId.get();
			// aapt-generated IDs have the high byte nonzero; clamp to the range under that.
			int newValue = result + 1;
			if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
			if (sNextGeneratedId.compareAndSet(result, newValue)) {
				return result;
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
		if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
			view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
		} else {
			view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
		}
	}

	public static void executeOnGlobalLayout(final View view, final Runnable runnable) {
		view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				ViewUtils.removeOnGlobalLayoutListener(view, this);
				runnable.run();
			}
		});
	}

	/**
	 * Apply a background selector to the given view with the given color as default state and a darker/brighter color for pressed state.
	 *
	 * @param view                the view to apply the selector to
	 * @param color               the base color for the custom selector
	 * @param selectorPressedType the effect to apply to the selector
	 */
	public static void applySelector(View view, @ColorInt int color, SelectorPressedType selectorPressedType) {
		if (view == null)
			return;

		ColorDrawable colorDrawableNormal = new ColorDrawable(color);
		ColorDrawable colorDrawablePressed;
		if (selectorPressedType == SelectorPressedType.BRIGHTER) {
			colorDrawablePressed = new ColorDrawable(ColorUtils.colorBrighter(color, 0.4f));
		} else {
			colorDrawablePressed = new ColorDrawable(ColorUtils.colorDarker(color, 0.2f));
		}

		int focused = android.R.attr.state_focused;
		int selected = android.R.attr.state_selected;
		int pressed = android.R.attr.state_pressed;
		int active = android.R.attr.state_active;
		int enabled = android.R.attr.state_enabled;

		StateListDrawable states = new StateListDrawable();
		states.addState(new int[]{-focused, -selected, -pressed}, colorDrawableNormal);
		states.addState(new int[]{focused, selected, pressed}, colorDrawablePressed);
		states.addState(new int[]{active}, colorDrawablePressed);
		states.addState(new int[]{enabled}, colorDrawablePressed);
		states.addState(new int[]{}, colorDrawableNormal);

		setBackground(view, states);
	}

	/**
	 * Safely set a background drawable to the view.
	 *
	 * @param view     the view to modify
	 * @param drawable the drawable to set on the view
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static void setBackground(View view, Drawable drawable) {
		if (view == null || drawable == null)
			return;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			view.setBackground(drawable);
		} else {
			view.setBackgroundDrawable(drawable);
		}
	}

	/**
	 * Apply a color on all drawables set on a TextView.
	 *
	 * @param textView the textview on which to apply color on drawables
	 * @param color    the color to apply
	 */
	public static void applyColorOnDrawables(TextView textView, @ColorInt int color) {
		if (textView == null)
			return;

		Drawable[] compoundDrawables = textView.getCompoundDrawables();
		for (Drawable compoundDrawable : compoundDrawables) {
			DrawableUtils.applyColor(compoundDrawable, color);
		}
		textView.setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
	}

	/**
	 * This method provides a fix for dashed line drawn as solid line since Honeycomb when the application has {@code hardwareAccelerated="true"}
	 * <p>
	 * Note: This issue is not fixed yet on Android 5.1
	 *
	 * @param separatorView the view to fix
	 * @see <a href="https://code.google.com/p/android/issues/detail?id=29944">Google issue 29944</a>
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static void fixDashedLine(View separatorView) {
		if (separatorView == null)
			return;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Disable hardware acceleration in order to display dashed line
			separatorView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
	}

}
