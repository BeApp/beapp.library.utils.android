package fr.beapp.utils.parser;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import fr.beapp.logger.Logger;
import fr.beapp.utils.io.IOUtils;
import fr.beapp.utils.lang.StringUtils;

public abstract class BaseXmlParser {

	private final XPathFactory xPathFactory = XPathFactory.newInstance();
	private final XPath xPath = xPathFactory.newXPath();

	protected Document parseDocument(@NonNull File file) throws ParserConfigurationException, IOException, SAXException {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			return parseDocument(fileInputStream);
		} finally {
			IOUtils.closeQuietly(fileInputStream);
		}
	}

	protected Document parseDocument(@NonNull InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		return dBuilder.parse(inputStream);
	}

	@Nullable
	protected Node getNode(@Nullable Node parent, @NonNull String nodeName) {
		NodeList nodeList = getNodes(parent, nodeName);
		return nodeList != null && nodeList.getLength() > 0 ? nodeList.item(0) : null;
	}

	@Nullable
	protected Node getXNode(@Nullable Node parent, @NonNull String expression) {
		try {
			return (Node) xPath.evaluate(expression, parent, XPathConstants.NODE);
		} catch (Exception e) {
			Logger.error("Can't retrieve node from expression: %s", e, expression);
		}
		return null;
	}

	@Nullable
	protected NodeList getNodes(@Nullable Node parent, @NonNull String nodeName) {
		if (parent instanceof Element) {
			return ((Element) parent).getElementsByTagName(nodeName);
		} else if (parent instanceof Document) {
			return ((Document) parent).getElementsByTagName(nodeName);
		}
		return null;
	}

	@Nullable
	protected NodeList getXNodes(@Nullable Node parent, @NonNull String expression) {
		try {
			return (NodeList) xPath.evaluate(expression, parent, XPathConstants.NODESET);
		} catch (Exception e) {
			Logger.error("Can't retrieve nodes from expression: %s", e, expression);
		}
		return null;
	}

	protected boolean containsOnlyContent(@Nullable Node node) {
		if (node == null)
			return false;

		NodeList childNodes = node.getChildNodes();
		if (childNodes.getLength() == 0)
			return false;

		for (int i = 0, size = childNodes.getLength(); i < size; i++) {
			if (!(childNodes.item(i) instanceof Text))
				return false;
		}
		return true;
	}

	@Nullable
	protected String getNodeContent(@Nullable Node node, boolean fetchSubContent, @Nullable String defaultValue) {
		if (node != null && (fetchSubContent || containsOnlyContent(node))) {
			String value = node.getTextContent();
			if (value != null)
				return StringUtils.trimToBlank(value);
		}
		return defaultValue;
	}

	@Nullable
	protected String getNodeContent(@Nullable Node parent, @NonNull String nodeName) {
		return getNodeContent(parent, nodeName, false, null);
	}

	@Nullable
	protected String getNodeContent(@Nullable Node parent, @NonNull String nodeName, boolean fetchSubContent, @Nullable String defaultValue) {
		Node node = getNode(parent, nodeName);
		return getNodeContent(node, fetchSubContent, defaultValue);
	}

	@Nullable
	protected String getXNodeContent(@Nullable Node parent, @NonNull String expression) {
		return getXNodeContent(parent, expression, false, null);
	}

	@Nullable
	protected String getXNodeContent(@Nullable Node parent, @NonNull String expression, boolean fetchSubContent, @Nullable String defaultValue) {
		Node node = getXNode(parent, expression);
		return getNodeContent(node, fetchSubContent, defaultValue);
	}

	@Nullable
	protected String getAttributeByName(@Nullable Node parent, @NonNull String name) {
		return getAttributeByName(parent, name, null);
	}

	@Nullable
	protected String getAttributeByName(@Nullable Node parent, @NonNull String name, @Nullable String defaultValue) {
		if (parent != null && parent.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) parent;
			if (element.hasAttribute(name)) {
				return element.getAttribute(name);
			}
		}
		return defaultValue;
	}

	@Nullable
	protected Integer getAttributeIntegerByName(@Nullable Node parent, @NonNull String name) {
		return getAttributeIntegerByName(parent, name, null);
	}

	@Nullable
	protected Integer getAttributeIntegerByName(@Nullable Node parent, @NonNull String name, @Nullable Integer defaultValue) {
		return ParserUtils.parseInteger(getAttributeByName(parent, name), defaultValue);
	}

	@Nullable
	protected Long getAttributeLongByName(@Nullable Node parent, @NonNull String name) {
		return getAttributeLongByName(parent, name, null);
	}

	@Nullable
	protected Long getAttributeLongByName(@Nullable Node parent, @NonNull String name, @Nullable Long defaultValue) {
		return ParserUtils.parseLong(getAttributeByName(parent, name), defaultValue);
	}

	@Nullable
	protected Double getAttributeDoubleByName(@Nullable Node parent, @NonNull String name) {
		return getAttributeDoubleByName(parent, name, null);
	}

	@Nullable
	protected Double getAttributeDoubleByName(@Nullable Node parent, @NonNull String name, @Nullable Double defaultValue) {
		return ParserUtils.parseDouble(getAttributeByName(parent, name), defaultValue);
	}

	protected boolean getAttributeBooleanByName(@Nullable Node parent, @NonNull String name) {
		return ParserUtils.parseBoolean(getAttributeByName(parent, name));
	}

}