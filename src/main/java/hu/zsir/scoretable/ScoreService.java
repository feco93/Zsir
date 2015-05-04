/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.scoretable;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
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
public class ScoreService extends Service<ObservableList<Person>> {

    @Override
    protected Task<ObservableList<Person>> createTask() {
        return new Task<ObservableList<Person>>() {

            @Override
            protected ObservableList<Person> call() throws Exception {
                List<Person> persons = new ArrayList<>();
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
                return list;
            }
        };
    }

}
