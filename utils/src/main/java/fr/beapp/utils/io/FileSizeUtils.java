package fr.beapp.utils.io;


import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import java.util.Locale;

public class FileSizeUtils {

	private FileSizeUtils() {
	}

	/**
	 * Return the formatted file size value according to the given unit.
	 * <p>
	 * Inspired from :
	 * <ul>
	 * <li><a href="http://stackoverflow.com/a/3758880/815737">http://stackoverflow.com/a/3758880/815737</a></li>
	 * <li><a href="http://programming.guide/java/formatting-byte-size-to-human-readable-format.html">http://programming.guide/java/formatting-byte-size-to-human-readable-format.html</a></li>
	 * <li><a href="https://en.wikipedia.org/wiki/Kilobyte">https://en.wikipedia.org/wiki/Kilobyte</a></li>
	 * </ul>
	 *
	 * @param bytes    the file size in bytes
	 * @param sizeUnit the unit to use
	 * @return the formatted size
	 */
	@NonNull
	public static String humanReadableSize(@IntRange(from = 0) long bytes, @NonNull SizeUnit sizeUnit) {
		int unit = sizeUnit.unit;
		if (bytes < unit) return bytes + " B";

		int exp = (int) (Math.log(bytes) / Math.log(unit));
		return String.format(Locale.ENGLISH, "%.1f %sB", bytes / Math.pow(unit, exp), sizeUnit.prefixes[exp - 1]);
	}

	public enum SizeUnit {
		SI(1000, new String[]{"k", "M", "G", "T", "P", "E"}),
		BINARY(1024, new String[]{"K", "M", "G", "T", "P", "E"}),
		KIBIBYTE(1024, new String[]{"Ki", "Mi", "Gi", "Ti", "Pi", "Ei"});

		private final int unit;
		private final String[] prefixes;

		SizeUnit(int unit, String[] prefixes) {
			this.unit = unit;
			this.prefixes = prefixes;
		}
	}
}
