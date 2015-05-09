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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * A stage for add a person into the score tabe.
 * 
 * @author Feco
 */
public class AddPersonDialog extends Stage {

    /**
     * The add person stage.
     */
    private static final AddPersonDialog addpersonstage = new AddPersonDialog();

    /**
     * Contructs the add person stage.
     */
    private AddPersonDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addPersonWindow.fxml"));
            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("/styles/addpersonwindow.css").toExternalForm());
            Scene scene = new Scene(root);
            setScene(scene);
            setResizable(false);
            initStyle(StageStyle.UTILITY);
            initModality(Modality.WINDOW_MODAL);
            getIcons().add(new Image(getClass().getResource("/images/Zsir.ico").toString()));
        } catch (IOException ex) {
            Logger.getLogger(AddPersonDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets the add person stage.
     * 
     * @return the add person stage
     */
    public static AddPersonDialog getAddpersonstage() {
        return addpersonstage;
    }

}
