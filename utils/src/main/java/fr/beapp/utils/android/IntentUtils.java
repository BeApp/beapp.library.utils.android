package fr.beapp.utils.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import fr.beapp.logger.Logger;
import fr.beapp.utils.ArrayUtils;
import fr.beapp.utils.StringUtils;
import fr.beapp.utils.StringValidationUtils;

/**
 * Inspired from <a href="https://developer.android.com/guide/components/intents-common.html">Official Common Intents</a> documentation.
 */
public class IntentUtils {

	private IntentUtils() {
	}

	/**
	 * Launch phone application with pre-filled phone number, ready to call.
	 *
	 * @param context     the calling context
	 * @param phoneNumber the phone number to dial
	 */
	public static void launchPhone(@NonNull Context context, @Nullable String phoneNumber) {
		phoneNumber = StringUtils.trimToNull(phoneNumber);

		if (StringUtils.isBlank(phoneNumber))
			return;

		Intent intent = new Intent(Intent.ACTION_DIAL);
		intent.setData(Uri.parse("tel:" + Uri.encode(phoneNumber)));

		safelyStartActivity(context, intent);
	}

	/**
	 * Launch SMS application with pre-filled phone number and content, ready to send.
	 *
	 * @param context     the calling context
	 * @param phoneNumber the phone number to send a SMS to
	 * @param body        the content to send
	 * @param attachments optional attachments to send with SMS/MMS
	 */
	public static void launchSMS(@NonNull Context context, @Nullable String phoneNumber, @Nullable String body, @NonNull Uri... attachments) {
		if (StringUtils.areAllBlank(phoneNumber, body) && ArrayUtils.isEmpty(attachments))
			return;

		Intent intent;
		if (attachments.length > 1) {
			intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
		} else {
			intent = new Intent(Intent.ACTION_SEND);
		}

		intent.setData(Uri.parse("smsto:" + Uri.encode(phoneNumber))); // only SMS apps should handle this
		intent.putExtra("sms_body", body);

		if (attachments.length > 1) {
			intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, new ArrayList<>(Arrays.asList(attachments)));
		} else if (attachments.length == 1) {
			intent.putExtra(Intent.EXTRA_STREAM, attachments[0]);
		}

		safelyStartActivity(context, intent);
	}

	/**
	 * Launch mail application with pre-filled subject recipients and content.
	 *
	 * @param context     the calling context
	 * @param recipients  the recipients to send the mail to
	 * @param subject     the subject of the mail
	 * @param content     the content of the mail
	 * @param attachments optional attachments to send with mail
	 */
	public static void launchMail(@NonNull Context context, @Nullable String[] recipients, @Nullable String subject, @Nullable String content, @NonNull Uri... attachments) {
		if (StringUtils.areAllBlank(subject, content) && ArrayUtils.isEmpty(recipients) && ArrayUtils.isEmpty(attachments))
			return;

		Intent intent;
		if (attachments.length > 1) {
			intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
		} else if (attachments.length == 1) {
			intent = new Intent(Intent.ACTION_SEND);
		} else {
			intent = new Intent(Intent.ACTION_SENDTO);
		}

		intent.setData(Uri.parse("mailto:")); // only email apps should handle this
		intent.putExtra(Intent.EXTRA_EMAIL, recipients);
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		intent.putExtra(Intent.EXTRA_TEXT, content);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		if (attachments.length > 1) {
			intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, new ArrayList<>(Arrays.asList(attachments)));
		} else if (attachments.length == 1) {
			intent.putExtra(Intent.EXTRA_STREAM, attachments[0]);
		}

		safelyStartActivity(context, intent);
	}

	/**
	 * Launch web browser application on a specific page.
	 *
	 * @param context the calling context
	 * @param url     the url of the web page to load
	 */
	public static void launchBrowser(@NonNull Context context, @Nullable String url) {
		if (StringUtils.isBlank(url))
			return;

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(StringValidationUtils.fixURL(url)));

		safelyStartActivity(context, intent);
	}

	/**
	 * Launch map application on a specific location
	 *
	 * @param context   the calling context
	 * @param latitude  the latitude of the location to display
	 * @param longitude the longitude of the location to display
	 */
	public static void launchMap(@NonNull Context context, float latitude, float longitude, @Nullable String label) {
		Intent intent = new Intent(Intent.ACTION_VIEW);

		String data;
		if (StringUtils.isBlank(label)) {
			data = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
		} else {
			data = String.format(Locale.ENGLISH, "geo:0,0?q=%f,%f(%s)", latitude, longitude, Uri.encode(label));
		}
		intent.setData(Uri.parse(data));

		safelyStartActivity(context, intent);
	}

	/**
	 * Launch map application on a specific location
	 *
	 * @param context the calling context
	 * @param address the address of the location to display
	 */
	public static void launchMap(@NonNull Context context, @Nullable String address) {
		if (StringUtils.isBlank(address))
			return;

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(String.format(Locale.ENGLISH, "geo:0,0?q=%s", Uri.encode(address))));

		safelyStartActivity(context, intent);
	}

	/**
	 * Try to open the application corresponding to the given packageName. If the application is not installed, redirect the user on the play store
	 *
	 * @param context     the calling context
	 * @param packageName the application to open or install
	 */
	public static void launchAppOrShowStore(@NonNull Context context, @Nullable String packageName) {
		if (StringUtils.isBlank(packageName))
			return;

		Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
		if (intent != null) {
			// We found the activity now start the activity
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		} else {
			// Bring user to the market or let them choose an app?
			launchPlayStore(context, packageName);
		}
	}

	/**
	 * Open the play store on the given application detail view
	 *
	 * @param context     the calling context
	 * @param packageName the application to install
	 */
	public static void launchPlayStore(@NonNull Context context, @Nullable String packageName) {
		if (StringUtils.isBlank(packageName))
			return;

		launchBrowser(context, "https://play.google.com/store/apps/details?id=" + packageName);
	}

	/**
	 * Launch camera application to take a picture and save it in the given file.
	 * If the file is null, a temporary one will be created and returned.
	 *
	 * @param activity    the calling activity
	 * @param file        the output file or null
	 * @param requestCode the requestCode to use to catch the callback event
	 * @return the output file
	 */
	@Nullable
	public static File takePicture(@NonNull Activity activity, @Nullable File file, int requestCode) {
		if (file == null) {
			try {
				file = File.createTempFile("Picture_", ".jpg", activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
			} catch (IOException e) {
				Logger.error("Couldn't prepare an output file for picture", e);
				return null;
			}
		}

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

		safelyStartActivityForResult(activity, intent, requestCode);
		return file;
	}

	/**
	 * Launch camera application to take a video and save it in the given file.
	 * If the file is null, a temporary one will be created and returned.
	 *
	 * @param activity    the calling activity
	 * @param file        the output file or null
	 * @param requestCode the requestCode to use to catch the callback event
	 * @return the output file
	 */
	@Nullable
	public static File takeVideo(@NonNull Activity activity, @Nullable File file, int requestCode) {
		if (file == null) {
			try {
				file = File.createTempFile("Video_", ".jpg", activity.getExternalFilesDir(Environment.DIRECTORY_MOVIES));
			} catch (IOException e) {
				Logger.error("Couldn't prepare an output file for video", e);
				return null;
			}
		}

		Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

		safelyStartActivityForResult(activity, intent, requestCode);
		return file;
	}

	/**
	 * Launch gallery application to pick a picture.
	 * <p>
	 * Inspired from http://stackoverflow.com/a/34348903/815737
	 *
	 * @param activity    the calling activity
	 * @param requestCode the requestCode to use to catch the callback event
	 */
	public static void pickPictureFromGallery(@NonNull Activity activity, int requestCode) {
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		safelyStartActivityForResult(activity, intent, requestCode);
	}

	/**
	 * Launch gallery application to pick a video.
	 * <p>
	 * Inspired from http://stackoverflow.com/a/34348903/815737
	 *
	 * @param activity    the calling activity
	 * @param requestCode the requestCode to use to catch the callback event
	 */
	public static void pickVideoFromGallery(@NonNull Activity activity, int requestCode) {
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setData(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);

		safelyStartActivityForResult(activity, intent, requestCode);
	}

	/**
	 * Launch gallery application to pick an audio.
	 * <p>
	 * Inspired from http://stackoverflow.com/a/34348903/815737
	 *
	 * @param activity    the calling activity
	 * @param requestCode the requestCode to use to catch the callback event
	 */
	public static void pickAudioFromGallery(@NonNull Activity activity, int requestCode) {
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setData(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);

		safelyStartActivityForResult(activity, intent, requestCode);
	}

	/**
	 * Safely start an intent. If an exception is thrown, just log the error.
	 *
	 * @param context the calling context
	 * @param intent  the intent to start
	 */
	public static void safelyStartActivity(@NonNull Context context, @NonNull Intent intent) {
		try {
			if (intent.resolveActivity(context.getPackageManager()) != null) {
				context.startActivity(intent);
			}
		} catch (Exception e) {
			Logger.warn("Couldn't launch action with intent: %s", e, intent.toString());
		}
	}

	/**
	 * Safely start an intent. If an exception is thrown, just log the error.
	 *
	 * @param activity    the calling context
	 * @param intent      the intent to start
	 * @param requestCode the requestCode
	 */
	public static void safelyStartActivityForResult(@NonNull Activity activity, @NonNull Intent intent, int requestCode) {
		try {
			if (intent.resolveActivity(activity.getPackageManager()) != null) {
				activity.startActivityForResult(intent, requestCode);
			}
		} catch (Exception e) {
			Logger.warn("Couldn't launch action with intent: %s", e, intent.toString());
		}
	}

}
