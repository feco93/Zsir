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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Feco
 */
public class AddPersonWindowController implements Initializable {

    public AddPersonWindowController() {

    }

    @FXML
    private GridPane mainPane;
    @FXML
    private TextField nameField;
    @FXML
    private Text warningText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void cancel() {
        AddPersonStage.getAddpersonstage().close();
    }

    @FXML
    private void savePerson() {
        if (nameField.getText().isEmpty()) {
            warningText.setVisible(true);
        }
        else {
            System.out.println(nameField.getText());
            AddPersonStage.getAddpersonstage().close();
        }
    }

}
