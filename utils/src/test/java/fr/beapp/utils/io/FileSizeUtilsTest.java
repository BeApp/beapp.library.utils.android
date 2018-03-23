package fr.beapp.utils.io;

import org.junit.Test;

import static fr.beapp.utils.io.FileSizeUtils.humanReadableSize;
import static org.junit.Assert.assertEquals;

public class FileSizeUtilsTest {

	@Test
	public void testHumanReadableSize() throws Exception {
		assertEquals("0 B", humanReadableSize(0, FileSizeUtils.SizeUnit.SI));
		assertEquals("0 B", humanReadableSize(0, FileSizeUtils.SizeUnit.BINARY));
		assertEquals("0 B", humanReadableSize(0, FileSizeUtils.SizeUnit.KIBIBYTE));

		assertEquals("27 B", humanReadableSize(27, FileSizeUtils.SizeUnit.SI));
		assertEquals("27 B", humanReadableSize(27, FileSizeUtils.SizeUnit.BINARY));
		assertEquals("27 B", humanReadableSize(27, FileSizeUtils.SizeUnit.KIBIBYTE));

		assertEquals("999 B", humanReadableSize(999, FileSizeUtils.SizeUnit.SI));
		assertEquals("999 B", humanReadableSize(999, FileSizeUtils.SizeUnit.BINARY));
		assertEquals("999 B", humanReadableSize(999, FileSizeUtils.SizeUnit.KIBIBYTE));

		assertEquals("1.0 kB", humanReadableSize(1000, FileSizeUtils.SizeUnit.SI));
		assertEquals("1000 B", humanReadableSize(1000, FileSizeUtils.SizeUnit.BINARY));
		assertEquals("1000 B", humanReadableSize(1000, FileSizeUtils.SizeUnit.KIBIBYTE));

		assertEquals("1.0 kB", humanReadableSize(1023, FileSizeUtils.SizeUnit.SI));
		assertEquals("1023 B", humanReadableSize(1023, FileSizeUtils.SizeUnit.BINARY));
		assertEquals("1023 B", humanReadableSize(1023, FileSizeUtils.SizeUnit.KIBIBYTE));

		assertEquals("1.0 kB", humanReadableSize(1024, FileSizeUtils.SizeUnit.SI));
		assertEquals("1.0 KB", humanReadableSize(1024, FileSizeUtils.SizeUnit.BINARY));
		assertEquals("1.0 KiB", humanReadableSize(1024, FileSizeUtils.SizeUnit.KIBIBYTE));

		assertEquals("1.7 kB", humanReadableSize(1728, FileSizeUtils.SizeUnit.SI));
		assertEquals("1.7 KB", humanReadableSize(1728, FileSizeUtils.SizeUnit.BINARY));
		assertEquals("1.7 KiB", humanReadableSize(1728, FileSizeUtils.SizeUnit.KIBIBYTE));

		assertEquals("1.9 TB", humanReadableSize(1855425871872L, FileSizeUtils.SizeUnit.SI));
		assertEquals("1.7 TB", humanReadableSize(1855425871872L, FileSizeUtils.SizeUnit.BINARY));
		assertEquals("1.7 TiB", humanReadableSize(1855425871872L, FileSizeUtils.SizeUnit.KIBIBYTE));

		assertEquals("9.2 EB", humanReadableSize(Long.MAX_VALUE, FileSizeUtils.SizeUnit.SI));
		assertEquals("8.0 EB", humanReadableSize(Long.MAX_VALUE, FileSizeUtils.SizeUnit.BINARY));
		assertEquals("8.0 EiB", humanReadableSize(Long.MAX_VALUE, FileSizeUtils.SizeUnit.KIBIBYTE));
	}

}