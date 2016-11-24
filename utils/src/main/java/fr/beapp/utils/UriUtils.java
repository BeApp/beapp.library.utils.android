package fr.beapp.utils;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class UriUtils {

	private UriUtils() {
	}

	/**
	 * Resolve the given URI pointing to an <b>image</b> of the gallery into an absolute path.
	 *
	 * @param context the calling context
	 * @param uri     the URI to resolve
	 * @return <code>null</code> if the URI was <code>null</code>, the resolved path otherwise
	 */
	@Nullable
	public static String retrievePathForImage(@NonNull Context context, @Nullable Uri uri) {
		return retrievePath(context, uri, MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATA);
	}

	/**
	 * Resolve the given URI pointing to a <b>video</b> of the gallery into an absolute path.
	 *
	 * @param context the calling context
	 * @param uri     the URI to resolve
	 * @return <code>null</code> if the URI was <code>null</code>, the resolved path otherwise
	 */
	@Nullable
	public static String retrievePathForVideo(@NonNull Context context, @Nullable Uri uri) {
		return retrievePath(context, uri, MediaStore.Video.Media.DATA, MediaStore.Video.Media.DATA);
	}

	@Nullable
	private static String retrievePath(@NonNull Context context, @Nullable Uri uri, String projection, String columnName) {
		if (uri == null)
			return null;

		ContentResolver contentResolver = context.getContentResolver();
		Cursor cursor = contentResolver.query(uri, new String[]{projection}, null, null, null);
		if (cursor != null) {
			try {
				if (cursor.moveToFirst()) {
					int idx = cursor.getColumnIndex(columnName);
					return cursor.getString(idx);
				}
			} finally {
				cursor.close();
			}
		}
		return uri.getPath();
	}

}
