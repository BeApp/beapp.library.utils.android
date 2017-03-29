package fr.beapp.utils.android.graphics;

import android.graphics.Rect;

import org.junit.Test;

import fr.beapp.utils.BaseRobolectric;

import static org.junit.Assert.assertEquals;

public class RectUtilsTest extends BaseRobolectric {

	@Test
	public void resizeToFit() throws Exception {
		assertEquals(new Rect(0, 0, 100, 100), RectUtils.resizeToFit(new Rect(0, 0, 50, 50), 100, 100));
		assertEquals(new Rect(0, 0, 100, 62), RectUtils.resizeToFit(new Rect(0, 0, 80, 50), 100, 100));
		assertEquals(new Rect(0, 0, 62, 100), RectUtils.resizeToFit(new Rect(0, 0, 50, 80), 100, 100));
	}

	@Test
	public void fitCenter() throws Exception {
		assertEquals(new Rect(100, 100, 200, 200), RectUtils.fitCenter(new Rect(0, 0, 50, 50), new Rect(100, 100, 200, 200)));
		assertEquals(new Rect(100, 119, 200, 181), RectUtils.fitCenter(new Rect(0, 0, 80, 50), new Rect(100, 100, 200, 200)));
		assertEquals(new Rect(119, 100, 181, 200), RectUtils.fitCenter(new Rect(0, 0, 50, 80), new Rect(100, 100, 200, 200)));
	}

	@Test
	public void fillCenter() throws Exception {
		assertEquals(new Rect(100, 100, 200, 200), RectUtils.fillCenter(new Rect(0, 0, 50, 50), new Rect(100, 100, 200, 200)));
		assertEquals(new Rect(70, 100, 230, 200), RectUtils.fillCenter(new Rect(0, 0, 80, 50), new Rect(100, 100, 200, 200)));
		assertEquals(new Rect(100, 70, 200, 230), RectUtils.fillCenter(new Rect(0, 0, 50, 80), new Rect(100, 100, 200, 200)));
	}

	@Test
	public void centerIn() throws Exception {
		assertEquals(new Rect(125, 125, 175, 175), RectUtils.centerIn(new Rect(0, 0, 50, 50), new Rect(100, 100, 200, 200)));
		assertEquals(new Rect(125, 125, 175, 175), RectUtils.centerIn(new Rect(50, 50, 100, 100), new Rect(100, 100, 200, 200)));
	}

}