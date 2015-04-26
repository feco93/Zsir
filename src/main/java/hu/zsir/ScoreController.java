/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Feco
 */
public class ScoreController {

    @FXML
    private TableView<Person> tableview;

    private List<Person> persons = new ArrayList<>();

    public void initialize() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(getClass().getResource("/files/input.xml").toString());
            NodeList nlist = doc.getElementsByTagName("person");

            for (int i = 0; i < nlist.getLength(); ++i) {
                Element element = (Element) nlist.item(i);
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                int score = Integer.valueOf(element.getElementsByTagName("score").item(0).getTextContent());
                SimpleDateFormat parser = new SimpleDateFormat("YYYY.MM.DD");
                String date = element.getElementsByTagName("date").item(0).getTextContent();
                Person person = new Person(name, score, date);
                persons.add(person);
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(ScoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Person> list = FXCollections.observableArrayList(persons);
        tableview.setItems(list);
    }

}
