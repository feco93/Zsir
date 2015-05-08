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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import org.joda.time.LocalDate;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author Feco
 */
public class AddPersonWindowController {

    private static int score;

    public static void setScore(int score) {
        AddPersonWindowController.score = score;
    }

    public AddPersonWindowController() {

    }

    @FXML
    private GridPane mainPane;
    @FXML
    private TextField nameField;
    @FXML
    private Text warningText;

    @FXML
    private void cancel() {
        AddPersonStage.getAddpersonstage().close();
    }

    @FXML
    private void savePerson() {
        if (nameField.getText().isEmpty()) {
            warningText.setVisible(true);
        } else {
            Person newPerson = new Person(nameField.getText(), score, LocalDate.now().toString());
            ScoreTableUtils.addPerson(newPerson);
            AddPersonStage.getAddpersonstage().close();
        }
    }
}
