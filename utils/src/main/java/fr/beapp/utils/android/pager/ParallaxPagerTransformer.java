package fr.beapp.utils.android.pager;

import androidx.annotation.FloatRange;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import android.view.View;

/**
 * parallax transformer class found here: <a href="https://github.com/xgc1986/ParallaxPagerTransformer">ParallaxPagerTransformer</a>
 */
public class ParallaxPagerTransformer implements ViewPager.PageTransformer {
	@IdRes
	private final int id;
	private float speed = 0.3f;
	private View parallaxView;

	public ParallaxPagerTransformer(@IdRes int parallaxViewId) {
		this.id = parallaxViewId;
	}

	@Override
	public void transformPage(@NonNull View view, float position) {
		if (parallaxView == null) {
			parallaxView = view.findViewById(id);
		}

		if (parallaxView != null && position >= -1 && position <= 1) {
			float width = parallaxView.getWidth();
			parallaxView.setTranslationX(-(position * width * speed));
		}
	}

	public void setSpeed(@FloatRange(from = 0) float speed) {
		this.speed = speed;
	}

}