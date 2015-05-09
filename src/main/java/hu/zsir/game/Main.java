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
package hu.zsir.game;

import hu.zsir.scoretable.AddPersonDialog;
import hu.zsir.scoretable.AddPersonDialogController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Feco
 */
public class Main extends Application {

    public static Stage mainWindow;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainWindow.fxml"));
        mainWindow = stage;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Zsir");
        stage.getIcons().add(new Image(getClass().getResource("/images/Zsir.ico").toString()));
        stage.show();
        AddPersonDialogController.setScore(10);
        AddPersonDialog.getAddpersonstage().initOwner(mainWindow);
        AddPersonDialog.getAddpersonstage().show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
