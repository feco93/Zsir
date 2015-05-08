/*
 * Copyright (C) 2015 Feco
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package hu.zsir.scoretable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

/**
 * Class for manipulating the stored score table.
 * 
 * @author Feco
 */
public class ScoreTableUtils {

    /**
     * The source xml file of the score table.
     */
    private static File XMLFile;
    /**
     * The directory of the xml file.
     */
    private static final Path mainDir;
    /**
     * Indicates whether the xml file is valid.
     */
    private static boolean valid = true;

    static {
        String homeDir = System.getProperty("user.home");
        mainDir = Paths.get(homeDir, ".Zsir");
        Path destPath = Paths.get(mainDir.toString(), "scoretable.dtd");
        try {
            Files.copy(Paths.get(ScoreTableUtils.class.getResource("/files/scoretable.dtd").toURI()),
                    destPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(ScoreTableUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        Path xmlFile = Paths.get(mainDir.toString(), "scoretable.xml");
        if (!Files.exists(mainDir)) {
            try {
                Files.createDirectory(mainDir);
            } catch (IOException ex) {
                Logger.getLogger(ScoreTableUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!Files.exists(xmlFile)) {
            createXml(xmlFile);
        } else {
            XMLFile = xmlFile.toFile();
            validate();
            if (!isValid()) {
                initFile();
            }
        }
    }

    /**
     * Inits the xml file.
     */
    private static void initFile() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("scoretable");
            rootElement.appendChild(doc.createElement("persons"));
            doc.appendChild(rootElement);
            write(doc);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ScoreTableUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Adds the specified new Person element to the xml file.
     * 
     * @param newPerson the person to be added to the xml file
     */
    public static void addPerson(Person newPerson) {
        try {
            JAXBContext context = JAXBContext.newInstance(Persons.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setValidating(false);
            XMLReader xmlReader = spf.newSAXParser().getXMLReader();
            xmlReader.setEntityResolver((String publicId, String systemId) -> 
                    new InputSource(Paths.get(mainDir.toString(), "scoretable.dtd").toString()));
            InputSource inputSource = new InputSource(new FileReader(getFile()));
            SAXSource source = new SAXSource(xmlReader, inputSource);
            

            Persons persons = (Persons) unmarshaller.unmarshal(source);
            persons.addPerson(newPerson);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
            Document doc = df.newDocumentBuilder().newDocument();
            m.marshal(persons, doc);

            write(doc);
        } catch (ParserConfigurationException | JAXBException | SAXException | FileNotFoundException ex) {
            Logger.getLogger(ScoreTableUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Writes out the specified document into the xml file.
     * 
     * @param doc 
     */
    private static void write(Document doc) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(getFile());
            transformer.setOutputProperty(
                    OutputKeys.DOCTYPE_SYSTEM, "scoretable.dtd");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(ScoreTableUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates the xml file and inits it.
     * 
     * @param file the file to be created
     */
    private static void createXml(Path file) {
        try {
            Path path = Files.createFile(file);
            XMLFile = path.toFile();
            initFile();
        } catch (IOException ex) {
            Logger.getLogger(ScoreTableUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Validates the xml file.
     */
    private static void validate() {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setValidating(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            builder.setErrorHandler(new ErrorHandler() {
                @Override
                public void error(SAXParseException exception) {
                    setValid(false);
                }

                @Override
                public void fatalError(SAXParseException exception) {
                    setValid(false);
                }

                @Override
                public void warning(SAXParseException exception) {
                    setValid(false);
                }
            });
            Document doc = builder.parse(getFile());
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(ScoreTableUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Indicates whether the xml file is valid.
     * 
     * @return true if the xml file is valid
     */
    public static boolean isValid() {
        return valid;
    }

    /**
     * Sets the value of the valid property.
     * 
     * @param valid the specified value
     */
    public static void setValid(boolean valid) {
        ScoreTableUtils.valid = valid;
    }

    /**
     * Gets the xml file.
     * 
     * @return the xml file
     */
    public static File getFile() {
        return XMLFile;
    }

}
