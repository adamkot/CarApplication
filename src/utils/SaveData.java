package utils;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Adam
 */
public class SaveData {

    private String directory;
    private String musicFileName;

    public void saveToFile() throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("config");
        doc.appendChild(rootElement);

        // staff elements
        Element staff = doc.createElement("musicConfig");
        rootElement.appendChild(staff);

        Element firstname = doc.createElement("directory");
        firstname.appendChild(doc.createTextNode(directory));
        staff.appendChild(firstname);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("config.xml"));

        transformer.transform(source, result);
        System.out.println("File saved!");
    }

    public String readFromFile() throws ParserConfigurationException, SAXException, IOException {
        File fXmlFile = new File("config.xml");
        if (fXmlFile.exists()) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("musicConfig");
            Node nNode = nList.item(0);
            Element eElement = (Element) nNode;
            directory = eElement.getElementsByTagName("directory").item(0).getTextContent();
            System.out.println("Wczytano " + directory);
            return directory;
        } else {
            return "";
        }

    }

    /**
     * @return the directory
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * @param directory the directory to set
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * @return the musicFileName
     */
    public String getMusicFileName() {
        return musicFileName;
    }

    /**
     * @param musicFileName the musicFileName to set
     */
    public void setMusicFileName(String musicFileName) {
        this.musicFileName = musicFileName;
    }

}
