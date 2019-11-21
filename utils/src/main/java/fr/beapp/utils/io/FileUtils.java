package fr.beapp.utils.io;


import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtils {

	private FileUtils() {
	}

	/**
	 * Deletes a file. If file is a directory, delete it and all sub-directories.
	 * <p>
	 * The difference between File.delete() and this method are:
	 * <ul>
	 * <li>A directory to be deleted does not have to be empty.</li>
	 * <li>You get exceptions when a file or directory cannot be deleted.
	 * (java.io.File methods returns a boolean)</li>
	 * </ul>
	 *
	 * @param file file or directory to delete, must not be {@code null}
	 * @throws NullPointerException  if the directory is {@code null}
	 * @throws FileNotFoundException if the file was not found
	 * @throws IOException           in case deletion is unsuccessful
	 */
	public static void forceDelete(@NonNull final File file) throws IOException {
		if (file.isDirectory()) {
			cleanDirectory(file);
		}

		final boolean filePresent = file.exists();
		if (!file.delete()) {
			if (!filePresent) {
				throw new FileNotFoundException("File does not exist: " + file);
			}
			throw new IOException("Unable to delete file: " + file);
		}
	}

	/**
	 * Cleans a directory without deleting it.
	 *
	 * @param directory directory to clean
	 * @throws IOException              in case cleaning is unsuccessful
	 * @throws IllegalArgumentException if {@code directory} does not exist or is not a directory
	 */
	public static void cleanDirectory(final File directory) throws IOException {
		if (!directory.exists()) {
			throw new IllegalArgumentException(directory + " does not exist");
		}
		if (!directory.isDirectory()) {
			throw new IllegalArgumentException(directory + " is not a directory");
		}

		final File[] files = directory.listFiles();
		if (files == null) {  // null if security restricted
			throw new IOException("Failed to list contents of " + directory);
		}

		IOException exception = null;
		for (File file : files) {
			try {
				forceDelete(file);
			} catch (final IOException ioe) {
				exception = ioe;
			}
		}

		if (exception != null) {
			throw exception;
		}
	}
}
