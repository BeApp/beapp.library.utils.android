package fr.beapp.utils.android.graphics;

import android.graphics.Color;

import org.junit.Assert;
import org.junit.Test;

import fr.beapp.utils.BaseRobolectric;

public class ColorUtilsTest extends BaseRobolectric {
	@Test
	public void alpha() throws Exception {
		Assert.assertEquals(0, ColorUtils.alpha(Color.argb(0, 0, 0, 0)));
		Assert.assertEquals(41, ColorUtils.alpha(Color.argb(41, 62, 83, 124)));
		Assert.assertEquals(255, ColorUtils.alpha(Color.argb(255, 255, 255, 255)));
	}

	@Test
	public void red() throws Exception {
		Assert.assertEquals(0, ColorUtils.red(Color.argb(0, 0, 0, 0)));
		Assert.assertEquals(62, ColorUtils.red(Color.argb(41, 62, 83, 124)));
		Assert.assertEquals(255, ColorUtils.red(Color.argb(255, 255, 255, 255)));
	}

	@Test
	public void green() throws Exception {
		Assert.assertEquals(0, ColorUtils.green(Color.argb(0, 0, 0, 0)));
		Assert.assertEquals(83, ColorUtils.green(Color.argb(41, 62, 83, 124)));
		Assert.assertEquals(255, ColorUtils.green(Color.argb(255, 255, 255, 255)));
	}

	@Test
	public void blue() throws Exception {
		Assert.assertEquals(0, ColorUtils.blue(Color.argb(0, 0, 0, 0)));
		Assert.assertEquals(124, ColorUtils.blue(Color.argb(41, 62, 83, 124)));
		Assert.assertEquals(255, ColorUtils.blue(Color.argb(255, 255, 255, 255)));
	}

	@Test
	public void toHexa() throws Exception {
		Assert.assertEquals("#000000", ColorUtils.toHexa(Color.rgb(0, 0, 0)));
		Assert.assertEquals("#000000", ColorUtils.toHexa(Color.argb(0, 0, 0, 0)));
		Assert.assertEquals("#c87841", ColorUtils.toHexa(Color.rgb(200, 120, 65)));
		Assert.assertEquals("#c87841", ColorUtils.toHexa(Color.argb(0, 200, 120, 65)));
		Assert.assertEquals("#ffffff", ColorUtils.toHexa(Color.rgb(255, 255, 255)));
		Assert.assertEquals("#ffffff", ColorUtils.toHexa(Color.argb(0, 255, 255, 255)));
	}

}