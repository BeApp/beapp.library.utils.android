package fr.beapp.utils;

public class ComparatorUtils {

	private ComparatorUtils() {
	}

	/**
	 * Backport {@link Integer#compare(int, int)} which have been added in Android 19.
	 * <p/>
	 * Compares two {@code int} values.
	 *
	 * @return 0 if lhs = rhs, less than 0 if lhs &lt; rhs, and greater than 0 if lhs &gt; rhs.
	 */
	public static int compare(int lhs, int rhs) {
		return lhs < rhs ? -1 : (lhs == rhs ? 0 : 1);
	}

	/**
	 * Backport {@link Long#compare(long, long)} which have been added in Android 19.
	 * <p/>
	 * Compares two {@code long} values.
	 *
	 * @return 0 if lhs = rhs, less than 0 if lhs &lt; rhs, and greater than 0 if lhs &gt; rhs.
	 */
	public static int compare(long lhs, long rhs) {
		return lhs < rhs ? -1 : (lhs == rhs ? 0 : 1);
	}

	/**
	 * Backport {@link Short#compare(short, short)} which have been added in Android 19.
	 * <p/>
	 * Compares two {@code short} values.
	 *
	 * @return 0 if lhs = rhs, less than 0 if lhs &lt; rhs, and greater than 0 if lhs &gt; rhs.
	 */
	public static int compare(short lhs, short rhs) {
		return lhs > rhs ? 1 : (lhs < rhs ? -1 : 0);
	}

	/**
	 * Backport {@link Byte#compare(byte, byte)} which have been added in Android 19.
	 * <p/>
	 * Compares two {@code byte} values.
	 *
	 * @return 0 if lhs = rhs, less than 0 if lhs &lt; rhs, and greater than 0 if lhs &gt; rhs.
	 */
	public static int compare(byte lhs, byte rhs) {
		return lhs > rhs ? 1 : (lhs < rhs ? -1 : 0);
	}

	/**
	 * Backport {@link Character#compare(char, char)} which have been added in Android 19.
	 * <p/>
	 * Compares two {@code char} values.
	 *
	 * @return 0 if lhs = rhs, less than 0 if lhs &lt; rhs, and greater than 0 if lhs &gt; rhs.
	 */
	public static int compare(char lhs, char rhs) {
		return lhs - rhs;
	}

	/**
	 * Backport {@link Boolean#compare(boolean, boolean)} which have been added in Android 19.
	 * <p/>
	 * Compares two {@code boolean} values.
	 *
	 * @return 0 if lhs = rhs, less than 0 if lhs &lt; rhs, and greater than 0 if lhs &gt; rhs.
	 * (Where true &gt; false.)
	 */
	public static int compare(boolean lhs, boolean rhs) {
		return lhs == rhs ? 0 : lhs ? 1 : -1;
	}

}
