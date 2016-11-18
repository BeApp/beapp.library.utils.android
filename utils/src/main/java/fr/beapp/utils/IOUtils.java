package fr.beapp.utils;

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

	public static void closeQuietly(@Nullable Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (Exception ignored) {
		}
	}

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
