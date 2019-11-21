package fr.beapp.utils.android;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ClipboardUtils {

	private ClipboardUtils() {
	}

	/**
	 * Copy the given text in clipboard, with the given label.
	 *
	 * @param context the calling context
	 * @param label   the user-visible label to give for this content in clipboard
	 * @param text    the content to put in clipboard
	 */
	public static void copyText(@NonNull Context context, @NonNull String label, @NonNull String text) {
		ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		if (clipboard != null) {
			ClipData clip = ClipData.newPlainText(label, text);
			clipboard.setPrimaryClip(clip);
		}
	}

	/**
	 * Try to retrieve a text content from the clipboard, if any.
	 *
	 * @param context the calling context
	 * @return the text content if any, <code>null</code> otherwise
	 */
	@Nullable
	public static String getTextContent(@NonNull Context context) {
		ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		if (clipboard == null)
			return null;

		if (clipboard.hasPrimaryClip() && clipboard.getPrimaryClip().getItemCount() > 0) {
			ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
			return item.getText().toString();
		}
		return null;
	}

}
