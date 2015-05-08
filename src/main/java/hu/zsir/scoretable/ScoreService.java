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
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
                    Document doc = dBuilder.parse(ScoreTableUtils.getFile());
                    NodeList nlist = doc.getElementsByTagName("person");

                    for (int i = 0; i < nlist.getLength(); ++i) {
                        Element element = (Element) nlist.item(i);
                        String name = element.getElementsByTagName("name").item(0).getTextContent();
                        int score = Integer.valueOf(element.getElementsByTagName("score").item(0).getTextContent());
                        String date = element.getElementsByTagName("date").item(0).getTextContent();
                        Person person = new Person(name, score, LocalDate.parse(date, DateTimeFormat.forPattern("YYYY-MM-DD")).toString());
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
