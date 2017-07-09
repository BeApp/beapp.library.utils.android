package fr.beapp.utils.android;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

import fr.beapp.utils.R;
import fr.beapp.utils.android.graphics.ColorUtils;
import fr.beapp.utils.android.graphics.DrawableUtils;

public class ViewUtils {

	private static final AtomicInteger NEXT_GENERATED_ID = new AtomicInteger(1);

	private ViewUtils() {
	}

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
			final int result = NEXT_GENERATED_ID.get();
			// aapt-generated IDs have the high byte nonzero; clamp to the range under that.
			int newValue = result + 1;
			if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
			if (NEXT_GENERATED_ID.compareAndSet(result, newValue)) {
				return result;
			}
		}
	}

	/**
	 * Attach a {@link Runnable} method to call once the given {@link View} is fully ready
	 *
	 * @param view     the View against which to invoke the method.
	 * @param runnable the runnable to execute once the view is ready
	 */
	public static void executeOnGlobalLayout(@NonNull final View view, @Nullable final Runnable runnable) {
		if (runnable == null)
			return;

		view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				ViewUtils.removeOnGlobalLayoutListener(view, this);
				runnable.run();
			}
		});
	}

	/**
	 * Convenient method to remove a previously installed global layout callback.
	 *
	 * @param view                   the View against which to invoke the method.
	 * @param onGlobalLayoutListener the GlobalLayoutListener to remove from the view
	 */
	@SuppressWarnings("deprecation")
	public static void removeOnGlobalLayoutListener(@NonNull View view, @Nullable ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
		if (onGlobalLayoutListener == null)
			return;

		if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
			view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
		} else {
			view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
		}
	}

	/**
	 * Apply a background selector to the given view with the given color as default state and a darker/brighter color for pressed state.
	 *
	 * @param view     the view to apply the selector to
	 * @param color    the base color for the custom selector
	 * @param brighter <code>true</code> to have a brighten color as press effect, <code>false</code> to have a darker color
	 */
	public static void applySelector(@NonNull View view, @ColorInt int color, boolean brighter) {
		ColorDrawable colorDrawableNormal = new ColorDrawable(color);
		ColorDrawable colorDrawablePressed;
		if (brighter) {
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
	 * Convenient method to set or remove a background drawable to the view.
	 *
	 * @param view     the view to modify
	 * @param drawable the drawable to set on the view, or <code>null</code> to remove the background
	 */
	@SuppressWarnings("deprecation")
	public static void setBackground(@NonNull View view, @Nullable Drawable drawable) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			view.setBackground(drawable);
		} else {
			view.setBackgroundDrawable(drawable);
		}
	}

	/**
	 * Programmatically apply {@code ?attr/selectableItemBackground} on foreground
	 *
	 * @param view the view to modify
	 */
	public static void applySelectableForeground(@NonNull View view) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			TypedValue typedValue = new TypedValue();
			Context context = view.getContext();

			context.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
			view.setForeground(ResourcesCompat.getDrawable(context.getResources(), typedValue.resourceId, context.getTheme()));
		}
	}

	/**
	 * Apply a color on all drawables set on a TextView.
	 *
	 * @param textView the {@link TextView} on which to apply color on drawables
	 * @param color    the color to apply
	 */
	public static void applyColorOnDrawables(@NonNull TextView textView, @ColorInt int color) {
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
	public static void fixDashedLine(@NonNull View separatorView) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Disable hardware acceleration in order to display dashed line
			separatorView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
	}

	/**
	 * Force a view to measure itself
	 *
	 * @param view the view to measure
	 */
	public static void forceMeasure(@NonNull View view) {
		int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		view.measure(spec, spec);
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
	}

}
