package fr.beapp.utils.android.graphics;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import fr.beapp.logger.Logger;
import fr.beapp.utils.lang.StringUtils;

public class ColorUtils {

	private ColorUtils() {
	}

	@IntRange(from = 0, to = 255)
	public static int alpha(@ColorInt int color) {
		return color >> 24 & 0xff;
	}

	@IntRange(from = 0, to = 255)
	public static int red(@ColorInt int color) {
		return color >> 16 & 0xff;
	}

	@IntRange(from = 0, to = 255)
	public static int green(@ColorInt int color) {
		return color >> 8 & 0xff;
	}

	@IntRange(from = 0, to = 255)
	public static int blue(@ColorInt int color) {
		return color & 0xff;
	}

	/**
	 * Parse the color string, and return the corresponding color-int.
	 * </p>
	 * Supported formats are:
	 * <ul>
	 * <li>R,G,B (decimal)</li>
	 * <li>#RRGGBB (hexa)</li>
	 * <li>#AARRGGBB (hexa)</li>
	 * <li>red, blue, green, black, white, gray, cyan, magenta, yellow, lightgray, darkgray, grey, lightgrey, darkgrey, aqua, fuschia, lime, maroon, navy, olive, purple, silver, teal</li>
	 * </ul>
	 *
	 * @param colorString  a color representation to parse
	 * @param defaultColor the default color to use as fallback in case of error during parsing
	 * @return a color value corresponding to the given color string, or the default value if the color couldn't be parsed
	 */
	@ColorInt
	public static int parseColor(@Nullable String colorString, @ColorInt int defaultColor) {
		if (StringUtils.isBlank(colorString))
			return defaultColor;

		if (colorString.startsWith("#")) {
			try {
				return Color.parseColor(colorString);
			} catch (IllegalArgumentException e) {
				Logger.warn("Couldn't parse RGB color %s: %s", colorString, e.getLocalizedMessage());
			}

		} else if (colorString.contains(",")) {
			String[] componentsString = colorString.split(",");
			if (componentsString.length < 3)
				return defaultColor;

			int[] components = new int[componentsString.length];
			for (int i = 0; i < componentsString.length; i++) {
				components[i] = Integer.valueOf(componentsString[i]);
			}

			if (components.length == 3) {
				return Color.rgb(components[0], components[1], components[2]);
			} else if (components.length > 3) {
				return Color.argb(components[3], components[0], components[1], components[2]);
			}
		}

		return defaultColor;
	}

	@NonNull
	public static String toHexa(@ColorInt int color) {
		StringBuilder builder = new StringBuilder(Integer.toHexString(color & 0xffffff));
		while (builder.length() < 6) {
			builder.insert(0, '0');
		}
		return "#" + builder.toString();
	}

	/**
	 * Set the alpha channel of the given color
	 *
	 * @param color a color to modify
	 * @param alpha the value of alpha channel, in percent
	 * @return a color with modified alpha-channel
	 */
	@ColorInt
	public static int alpha(@ColorInt int color, @FloatRange(from = 0, to = 1) float alpha) {
		if (color == 0)
			return 0;
		return alpha(color, (int) (alpha * 255));
	}

	/**
	 * Set the alpha channel of the given color
	 *
	 * @param color a color to modify
	 * @param alpha the value of alpha channel
	 * @return a color with modified alpha-channel
	 */
	@ColorInt
	public static int alpha(@ColorInt int color, @IntRange(from = 0, to = 255) int alpha) {
		if (color == 0)
			return 0;
		return color | (alpha << 24);
	}

	/**
	 * Brighten the given color by a percentage
	 *
	 * @param color   a color to modify
	 * @param percent percentage to brighten the color
	 * @return a brighter color
	 */
	@ColorInt
	public static int colorBrighter(@ColorInt int color, @FloatRange(from = 0, to = 1) float percent) {
		return changeColor(color, percent);
	}

	/**
	 * Darker the given color by a percentage
	 *
	 * @param color   a color to modify
	 * @param percent percentage to darker the color
	 * @return a darker color
	 */
	@ColorInt
	public static int colorDarker(@ColorInt int color, @FloatRange(from = 0, to = 1) float percent) {
		return changeColor(color, -percent);
	}

	@ColorInt
	private static int changeColor(@ColorInt int color, @FloatRange(from = 0, to = 1) float percent) {
		if (Math.abs(percent) < 0f || Math.abs(percent) > 1f)
			return color;

		float[] hsv = new float[3];
		Color.colorToHSV(color, hsv);

		hsv[2] += hsv[2] * percent;
		if (hsv[2] > 1) {
			hsv[2] = 1;
		}

		return Color.HSVToColor(hsv);
	}
}
