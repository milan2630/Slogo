package slogo.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLParser {
    private DocumentBuilder documentBuilder;

    public XMLParser () {
        documentBuilder = getDocumentBuilder();
    }

    public Configuration getConfiguration (File dataFile) {
        Element root = getRootElement(dataFile);
        Map<String, String> config = new HashMap<>();
        List<List<Double>> turtleList = new ArrayList<List<Double>>();
        for (String field : Configuration.CONFIGURATION_FIELDS) {
            config.put(field, getTextValue(root, field));
        }

        NodeList nList = root.getElementsByTagName(Configuration.TURTLE);
        for (int i = 0; i < nList.getLength(); i++) {
            List<Double> turtleValues = new ArrayList<>();
            Node turtle = nList.item(i);

            Element element = (Element) turtle;
            for (String field : Configuration.TURTLE_FIELDS) {
                String textValue = element.getElementsByTagName(field).item(0).getTextContent();
                turtleValues.add(Double.parseDouble(textValue));
            }
            turtleList.add(turtleValues);
        }
        return new Configuration(config, turtleList);
    }

    private Element getRootElement (File xmlFile) {
        try {
            documentBuilder.reset();
            Document xmlDocument = documentBuilder.parse(xmlFile);
            return xmlDocument.getDocumentElement();
        }
        catch (SAXException | IOException e) {
            throw new XMLException(e);
        }
    }

    private String getTextValue (Element e, String tagName) {
        NodeList nodeList = e.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            // didn't find any text
            return "";
        }
    }

    private DocumentBuilder getDocumentBuilder () {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            throw new XMLException(e);
        }
    }
}
