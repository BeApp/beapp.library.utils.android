package fr.beapp.utils.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtils {
	private KeyboardUtils() {
	}

	/**
	 * Set focus on the given {@link View} and display keyboard
	 *
	 * @param view view to gain focus
	 */
	public static void showKeyboard(@NonNull View view) {
		view.requestFocus();
		InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		if (inputMethodManager != null) {
			inputMethodManager.showSoftInput(view, 0);
		}
	}

	/**
	 * Hide keyboard which was shown from the given {@link View}
	 *
	 * @param view the view which gained focus
	 */
	public static void hideKeyboard(@NonNull View view) {
		InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		if (inputMethodManager != null) {
			inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
}