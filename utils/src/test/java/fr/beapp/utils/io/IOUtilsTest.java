package fr.beapp.utils.io;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import fr.beapp.utils.BaseRobolectric;
import fr.beapp.utils.io.IOUtils;

public class IOUtilsTest extends BaseRobolectric {

	@Test
	public void testCloseQuietly() throws Exception {
		Closeable closeableMock = Mockito.mock(Closeable.class);
		IOUtils.closeQuietly(closeableMock);
		Mockito.verify(closeableMock).close();
	}

	@Test
	public void testCloseQuietly_null() throws Exception {
		IOUtils.closeQuietly(null);
	}

	@Test
	public void testCloseQuietly_exception() throws Exception {
		Closeable closeableMock = Mockito.mock(Closeable.class);
		Mockito.doThrow(new IOException()).when(closeableMock).close();
		IOUtils.closeQuietly(closeableMock);
		Mockito.verify(closeableMock).close();
	}


	@Test
	public void testReadFromStream_null() throws Exception {
		Assert.assertNull(IOUtils.readFromStream(null));
	}

	@Test
	public void testReadFromStream() throws Exception {
		Assert.assertEquals("", IOUtils.readFromStream(new ByteArrayInputStream(new byte[0])));
		Assert.assertEquals("test", IOUtils.readFromStream(new ByteArrayInputStream("test".getBytes())));
	}


	@Test
	public void testReadFromFile_null() throws Exception {
		Assert.assertNull(IOUtils.readFromFile(null));
	}

	@Test(expected = FileNotFoundException.class)
	public void testReadFromFile_unknownFile() throws Exception {
		IOUtils.readFromFile(new File("unknownFile"));
	}

//	@Test
//	public void testReadFromFile() throws Exception {
//		Assert.assertEquals("plaintext content", IOUtils.readFromFile(TestUtils.resourceFile("plaintext")));
//	}


	@Test
	public void testWriteToStream_null() throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Assert.assertFalse(IOUtils.writeToStream(null, outputStream));
		Assert.assertEquals(0, outputStream.size());
	}

	@Test
	public void testWriteToStream_empty() throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Assert.assertTrue(IOUtils.writeToStream("", outputStream));
		Assert.assertEquals(0, outputStream.size());
	}

	@Test
	public void testWriteToStream() throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Assert.assertTrue(IOUtils.writeToStream("test", outputStream));
		Assert.assertEquals("test", outputStream.toString());
	}


	@Test
	public void testWriteToFile_null() throws Exception {
		File file = File.createTempFile("beapp", null);
		Assert.assertFalse(IOUtils.writeToFile(null, file));
		Assert.assertEquals("", IOUtils.readFromFile(file));
	}

	@Test
	public void testWriteToFile() throws Exception {
		File file = File.createTempFile("beapp", null);
		Assert.assertTrue(IOUtils.writeToFile("test", file));
		Assert.assertEquals("test", IOUtils.readFromFile(file));
	}


	@Test
	public void testCopy_stream_empty() throws Exception {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[0]);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		IOUtils.copy(inputStream, outputStream);
		Assert.assertEquals(0, outputStream.size());
	}

	@Test
	public void testCopy_stream() throws Exception {
		ByteArrayInputStream inputStream = new ByteArrayInputStream("test".getBytes());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		IOUtils.copy(inputStream, outputStream);
		Assert.assertEquals("test", outputStream.toString());
	}


	@Test
	public void testCopy_reader_empty() throws Exception {
		Reader reader = new StringReader("");
		Writer writer = new StringWriter();
		IOUtils.copy(reader, writer);
		Assert.assertEquals("", writer.toString());
	}

	@Test
	public void testCopy_reader() throws Exception {
		Reader reader = new StringReader("test");
		Writer writer = new StringWriter();
		IOUtils.copy(reader, writer);
		Assert.assertEquals("test", writer.toString());
	}

}