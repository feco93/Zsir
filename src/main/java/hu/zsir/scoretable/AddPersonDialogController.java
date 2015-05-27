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

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.joda.time.LocalDate;

/**
 * FXML Controller class for controlling person adder dialog.
 *
 * @author Feco
 */
public class AddPersonDialogController {

    /**
     * The score of the person.
     */
    private static int score;

    /**
     * Sets the value of the score.
     *
     * @param score the specified score
     */
    public static void setScore(int score) {
        AddPersonDialogController.score = score;
    }

    /**
     * The main pane of the add person window.
     */
    @FXML
    private GridPane mainPane;
    /**
     * The text field of the add person window.
     */
    @FXML
    private TextField nameField;
    /**
     * A warning text that is visible if the text field is empty.
     */
    @FXML
    private Text warningText;

    /**
     * Close the add person window.
     */
    @FXML
    private void cancel() {
        AddPersonDialog.getAddpersonstage().close();
    }

    /**
     * Save a person into the score table xml file and close the window.
     */
    @FXML
    private void savePerson() {
        if (nameField.getText().isEmpty()) {
            warningText.setVisible(true);
        } else {
            Person newPerson = new Person(nameField.getText(), score, LocalDate.now().toString());
            ScoreTableUtils.addPerson(newPerson);
            cancel();
        }
    }
}
