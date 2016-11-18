package fr.beapp.utils.parser;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;

import java.io.FileNotFoundException;

import fr.beapp.utils.TestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BaseXmlParserTest {

	BaseXmlParser xmlParser = new BaseXmlParser() {
	};

	@Test
	public void testParseDocument() throws Exception {
		assertNotNull(xmlParser.parseDocument(TestUtils.resourceFile("xmlparser.xml")));
	}

	@Test(expected = FileNotFoundException.class)
	public void testParseDocument_notfound() throws Exception {
		xmlParser.parseDocument(TestUtils.resourceFile("notfound"));
	}

	@Test(expected = SAXParseException.class)
	public void testParseDocument_notxml() throws Exception {
		xmlParser.parseDocument(TestUtils.resourceFile("plaintext"));
	}

	@Test
	public void testGetNode() throws Exception {
		Document document = xmlParser.parseDocument(TestUtils.resourceFile("xmlparser.xml"));

		assertEquals(null, xmlParser.getNode(document, "unknown"));

		Node catalogNode = xmlParser.getNode(document, "catalog");
		assertNotNull(catalogNode);

		Node bookNodeFromCatalog = xmlParser.getNode(catalogNode, "book");
		assertNotNull(bookNodeFromCatalog);
		assertEquals("bk101", bookNodeFromCatalog.getAttributes().getNamedItem("id").getTextContent());

		Node bookNodeFromDocument = xmlParser.getNode(document, "book");
		assertNotNull(bookNodeFromDocument);
		assertEquals("bk101", bookNodeFromDocument.getAttributes().getNamedItem("id").getTextContent());
	}

	@Test
	public void testGetXNode() throws Exception {
		Document document = xmlParser.parseDocument(TestUtils.resourceFile("xmlparser.xml"));

		assertNull(xmlParser.getXNode(document, "unknown"));

		assertNotNull(xmlParser.getXNode(document, "catalog"));

		Node bookNode = xmlParser.getXNode(document, "catalog/book");
		assertNotNull(bookNode);
		assertEquals("bk101", bookNode.getAttributes().getNamedItem("id").getTextContent());

		Node bookNode2 = xmlParser.getXNode(document, "catalog/book[@id='bk107']");
		assertNotNull(bookNode2);
		assertEquals("bk107", bookNode2.getAttributes().getNamedItem("id").getTextContent());
	}

	@Test
	public void testGetNodes() throws Exception {
		Document document = xmlParser.parseDocument(TestUtils.resourceFile("xmlparser.xml"));

		NodeList unknownNodes = xmlParser.getNodes(document, "unknown");
		assertNotNull(unknownNodes);
		assertEquals(0, unknownNodes.getLength());

		NodeList catalogNodes = xmlParser.getNodes(document, "catalog");
		assertNotNull(catalogNodes);
		assertEquals(1, catalogNodes.getLength());

		NodeList bookNodes = xmlParser.getNodes(document, "book");
		assertNotNull(bookNodes);
		assertEquals(12, bookNodes.getLength());
	}

	@Test
	public void testGetXNodes() throws Exception {
		Document document = xmlParser.parseDocument(TestUtils.resourceFile("xmlparser.xml"));

		assertNull(xmlParser.getXNodes(document, "corruptedXPath["));

		NodeList unknownNodes = xmlParser.getXNodes(document, "unknown");
		assertNotNull(unknownNodes);
		assertEquals(0, unknownNodes.getLength());

		NodeList catalogNodes = xmlParser.getXNodes(document, "catalog");
		assertNotNull(catalogNodes);
		assertEquals(1, catalogNodes.getLength());

		NodeList bookNodes = xmlParser.getXNodes(document, "catalog/book");
		assertNotNull(bookNodes);
		assertEquals(12, bookNodes.getLength());

		NodeList bookNodes2 = xmlParser.getXNodes(document, "catalog/book[starts-with(@id, 'bk10')]");
		assertNotNull(bookNodes2);
		assertEquals(9, bookNodes2.getLength());
	}

	@Test
	public void testContainsOnlyContent() throws Exception {
		Document document = xmlParser.parseDocument(TestUtils.resourceFile("xmlparser.xml"));

		assertFalse(xmlParser.containsOnlyContent(xmlParser.getNode(document, "unknown")));
		assertFalse(xmlParser.containsOnlyContent(xmlParser.getNode(document, "catalog")));
		assertFalse(xmlParser.containsOnlyContent(xmlParser.getXNode(document, "catalog/book")));
		assertFalse(xmlParser.containsOnlyContent(xmlParser.getXNode(document, "catalog/book[@id='bk103']/description")));

		assertTrue(xmlParser.containsOnlyContent(xmlParser.getXNode(document, "catalog/book/author")));
		assertTrue(xmlParser.containsOnlyContent(xmlParser.getXNode(document, "catalog/book/description")));
		assertTrue(xmlParser.containsOnlyContent(xmlParser.getXNode(document, "catalog/book[@id='bk102']/description")));
	}

	@Test
	public void testGetNodeContent_direct() throws Exception {
		String defaultValue = "default";

		Document document = xmlParser.parseDocument(TestUtils.resourceFile("xmlparser.xml"));

		assertEquals(defaultValue, xmlParser.getNodeContent(xmlParser.getNode(document, "unknown"), false, defaultValue));
		assertEquals(defaultValue, xmlParser.getNodeContent(xmlParser.getNode(document, "unknown"), true, defaultValue));

		assertEquals(defaultValue, xmlParser.getNodeContent(xmlParser.getNode(document, "book"), false, defaultValue));
		assertEquals("Gambardella, Matthew\n" +
				"        XML Developer's Guide\n" +
				"        Computer\n" +
				"        44.95\n" +
				"        2000-10-01\n" +
				"        An in-depth look at creating applications with XML.", xmlParser.getNodeContent(xmlParser.getNode(document, "book"), true, defaultValue));

		Node bookNode = xmlParser.getNode(document, "book");

		assertEquals("Gambardella, Matthew", xmlParser.getNodeContent(xmlParser.getNode(bookNode, "author"), false, defaultValue));
		assertEquals("Gambardella, Matthew", xmlParser.getNodeContent(xmlParser.getNode(bookNode, "author"), true, defaultValue));
		assertEquals("An in-depth look at creating applications with XML.", xmlParser.getNodeContent(xmlParser.getNode(bookNode, "description"), false, defaultValue));
		assertEquals("An in-depth look at creating applications with XML.", xmlParser.getNodeContent(xmlParser.getNode(bookNode, "description"), true, defaultValue));
	}

	@Test
	public void testGetNodeContent() throws Exception {
		String defaultValue = "default";

		Document document = xmlParser.parseDocument(TestUtils.resourceFile("xmlparser.xml"));

		assertEquals(defaultValue, xmlParser.getNodeContent(document, "unknown", false, defaultValue));
		assertEquals(defaultValue, xmlParser.getNodeContent(document, "unknown", true, defaultValue));

		assertEquals(defaultValue, xmlParser.getNodeContent(document, "book", false, defaultValue));
		assertEquals("Gambardella, Matthew\n" +
				"        XML Developer's Guide\n" +
				"        Computer\n" +
				"        44.95\n" +
				"        2000-10-01\n" +
				"        An in-depth look at creating applications with XML.", xmlParser.getNodeContent(document, "book", true, defaultValue));

		Node bookNode = xmlParser.getNode(document, "book");

		assertEquals("Gambardella, Matthew", xmlParser.getNodeContent(bookNode, "author", false, defaultValue));
		assertEquals("Gambardella, Matthew", xmlParser.getNodeContent(bookNode, "author", true, defaultValue));
		assertEquals("An in-depth look at creating applications with XML.", xmlParser.getNodeContent(bookNode, "description", false, defaultValue));
		assertEquals("An in-depth look at creating applications with XML.", xmlParser.getNodeContent(bookNode, "description", true, defaultValue));
	}

	@Test
	public void testGetXNodeContent() throws Exception {
		String defaultValue = "default";

		Document document = xmlParser.parseDocument(TestUtils.resourceFile("xmlparser.xml"));

		assertEquals(defaultValue, xmlParser.getXNodeContent(document, "corruptedXPath[", false, defaultValue));
		assertEquals(defaultValue, xmlParser.getXNodeContent(document, "corruptedXPath[", true, defaultValue));

		assertEquals(defaultValue, xmlParser.getXNodeContent(document, "unknown", false, defaultValue));
		assertEquals(defaultValue, xmlParser.getXNodeContent(document, "unknown", true, defaultValue));


		assertEquals(defaultValue, xmlParser.getXNodeContent(document, "catalog/book", false, defaultValue));
		assertEquals("Gambardella, Matthew\n" +
				"        XML Developer's Guide\n" +
				"        Computer\n" +
				"        44.95\n" +
				"        2000-10-01\n" +
				"        An in-depth look at creating applications with XML.", xmlParser.getXNodeContent(document, "catalog/book", true, defaultValue));

		assertEquals("Thurman, Paula", xmlParser.getXNodeContent(document, "//catalog/book[@id='bk107']/author", false, defaultValue));
		assertEquals("Thurman, Paula", xmlParser.getXNodeContent(document, "//catalog/book[@id='bk107']/author", true, defaultValue));
		assertEquals("A deep sea diver finds true love twenty thousand leagues beneath the sea.", xmlParser.getXNodeContent(document, "//catalog/book[@id='bk107']/description", false, defaultValue));
		assertEquals("A deep sea diver finds true love twenty thousand leagues beneath the sea.", xmlParser.getXNodeContent(document, "//catalog/book[@id='bk107']/description", true, defaultValue));
	}

	@Test
	public void testGetAttributeByName() throws Exception {
		String defaultValue = "default";

		Document document = xmlParser.parseDocument(TestUtils.resourceFile("xmlparser.xml"));
		Node bookNode = xmlParser.getXNode(document, "catalog/book[@id='bk104']");

		assertEquals(defaultValue, xmlParser.getAttributeByName(bookNode, "unknown", defaultValue));
		assertEquals("bk104", xmlParser.getAttributeByName(bookNode, "id", defaultValue));

		assertEquals(Integer.valueOf(-1), xmlParser.getAttributeIntegerByName(bookNode, "id", -1));
		assertEquals(Long.valueOf(-1L), xmlParser.getAttributeLongByName(bookNode, "id", -1L));
		assertEquals(Double.valueOf(-1.0), xmlParser.getAttributeDoubleByName(bookNode, "id", -1.0));
		assertEquals(false, xmlParser.getAttributeBooleanByName(bookNode, "id"));
	}

}