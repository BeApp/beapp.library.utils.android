package fr.beapp.utils.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class UiUtils {

	private UiUtils() {
	}

	/**
	 * Return the ActionBar's height in pixel.
	 *
	 * @param context the caller context
	 * @return 0 if an error occurred, the height in pixels otherwise
	 */
	public static int retrieveActionBarHeight(Context context) {
		if (context == null)
			return 0;

		Resources resources = context.getResources();
		DisplayMetrics displayMetrics = resources.getDisplayMetrics();
		int actionBarHeightComplex = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, displayMetrics);

		TypedValue tv = new TypedValue();
		Resources.Theme theme = context.getTheme();
		if (theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
			actionBarHeightComplex = tv.data;
		}

		return TypedValue.complexToDimensionPixelSize(actionBarHeightComplex, displayMetrics);
	}

}
