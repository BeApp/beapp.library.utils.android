package fr.beapp.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class TestUtils {

	@SafeVarargs
	public static <E> Set<E> newTree(E... values) {
		TreeSet<E> tree = new TreeSet<>();
		Collections.addAll(tree, values);
		return tree;
	}

	public static File resourceFile(String resourcePath) throws Exception {
//		return new File(extractResourcesFolder(), resourcePath);

		URL resource = TestUtils.class.getResource("/" + resourcePath);
		if (resource == null)
			throw new FileNotFoundException("Couldn't find resource " + resourcePath);

		return new File(resource.toString().replace("file:", ""));
	}

	/**
	 * Dirty hack to retrieve resources folder as Robolectric messes up class loaders
	 */
	public static File extractResourcesFolder() throws URISyntaxException {
		URI uri = TestUtils.class.getResource(TestUtils.class.getSimpleName() + ".class").toURI();

		String projectUrl = uri.toString();
		if (projectUrl.contains("/build/")) {
			projectUrl = projectUrl.substring(0, projectUrl.indexOf("/build/"));
		}
		if (projectUrl.startsWith("resourceFile:")) {
			projectUrl = projectUrl.substring(5);
		}

		File file = new File(projectUrl, "src/test/resources");
		if (file.exists()) {
			return file;
		}

		return new File(projectUrl, "build/intermediates/sourceFolderJavaResources/test/" + BuildConfig.BUILD_TYPE);
	}

}
