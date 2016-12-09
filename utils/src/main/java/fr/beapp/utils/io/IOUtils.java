package fr.beapp.utils.io;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class IOUtils {

	public static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	private IOUtils() {
	}

	/**
	 * Closes a Closeable unconditionally.
	 * <p/>
	 * Equivalent to Closeable.close(), except any exceptions will be ignored.
	 * <p/>
	 * This is typically used in finally blocks to ensure that the closeable is closed even if an Exception was thrown before the normal close statement was reached.
	 *
	 * @param closeable
	 */
	public static void closeQuietly(@Nullable Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (Exception ignored) {
			// Ignore this
		}
	}

	/**
	 * Gets the contents of an InputStream as a String using the default character encoding of the platform.
	 * <p/>
	 * This method buffers the input internally, so there is no need to use a BufferedInputStream.
	 *
	 * @param inputStream the InputStream to read from
	 * @return the requested String
	 * @throws IOException if an I/O error occurs
	 */
	@Nullable
	public static String readFromStream(@Nullable InputStream inputStream) throws IOException {
		if (inputStream == null)
			return null;

		InputStreamReader inputStreamReader = null;
		try {
			inputStreamReader = new InputStreamReader(inputStream);
			StringWriter stringWriter = new StringWriter();
			copy(inputStreamReader, stringWriter);
			return stringWriter.toString();
		} finally {
			closeQuietly(inputStreamReader);
		}
	}

	/**
	 * Gets the contents of a File as a String using the default character encoding of the platform.
	 *
	 * @param inputFile the File to read from
	 * @return the requested String
	 * @throws IOException if an I/O error occurs
	 */
	@Nullable
	public static String readFromFile(@Nullable File inputFile) throws IOException {
		if (inputFile == null)
			return null;

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(inputFile);
			return readFromStream(fileInputStream);
		} finally {
			closeQuietly(fileInputStream);
		}
	}

	/**
	 * Writes chars from a String to bytes on an OutputStream using the default character encoding of the platform.
	 * <p/>
	 * This method uses String.getBytes().
	 *
	 * @param value        the String to write, <code>null</code> ignored
	 * @param outputStream the OutputStream to write to
	 * @return <code>true</code> if the given value wasn't <code>null</code> and has been written, <code>false</code> if the value was <code>null</code>
	 * @throws IOException if an I/O error occurs
	 */
	public static boolean writeToStream(@Nullable String value, @NonNull OutputStream outputStream) throws IOException {
		if (value == null)
			return false;

		BufferedOutputStream bufferedOutputStream = null;
		try {
			bufferedOutputStream = new BufferedOutputStream(outputStream);
			bufferedOutputStream.write(value.getBytes());
			return true;
		} finally {
			closeQuietly(bufferedOutputStream);
		}
	}

	/**
	 * Writes chars from a String into a file using the default character encoding of the platform.
	 * <p/>
	 * This method uses String.getBytes().
	 * <p/>
	 * This method will try to create parents folders if needed.
	 *
	 * @param value      the String to write, <code>null</code> ignored
	 * @param outputFile the output file to write to
	 * @return <code>true</code> if the given value wasn't <code>null</code> and has been written, <code>false</code> if the value was <code>null</code>
	 * @throws IOException if an I/O error occurs
	 */
	public static boolean writeToFile(@Nullable String value, @NonNull File outputFile) throws IOException {
		if (value == null)
			return false;

		File parentFile = outputFile.getParentFile();
		parentFile.mkdirs();

		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(outputFile);
			writeToStream(value, fileOutputStream);
			return true;
		} finally {
			closeQuietly(fileOutputStream);
		}
	}

	/**
	 * Copies bytes from an InputStream to an OutputStream.
	 * <p/>
	 * This method buffers the input internally, so there is no need to use a BufferedInputStream.
	 *
	 * @param inputStream  the InputStream to read from
	 * @param outputStream the OutputStream to write to
	 * @return the number of bytes copied, or -1 if > Integer.MAX_VALUE
	 * @throws IOException if an I/O error occurs
	 */
	public static long copy(@NonNull InputStream inputStream, @NonNull OutputStream outputStream) throws IOException {
		final byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		long count = 0;
		int n;
		while ((n = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

	/**
	 * Copies chars from a Reader to a Writer.
	 * <p/>
	 * This method buffers the input internally, so there is no need to use a BufferedReader.
	 *
	 * @param reader the Reader to read from
	 * @param writer the Writer to write to
	 * @return the number of characters copied, or -1 if > Integer.MAX_VALUE
	 * @throws IOException if an I/O error occurs
	 */
	public static long copy(@NonNull Reader reader, @NonNull Writer writer) throws IOException {
		final char[] buffer = new char[DEFAULT_BUFFER_SIZE];
		long count = 0;
		int n;
		while ((n = reader.read(buffer)) != -1) {
			writer.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

}
