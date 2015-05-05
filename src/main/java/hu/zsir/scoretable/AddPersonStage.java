/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.scoretable;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Feco
 */
public class AddPersonStage extends Stage {

    private static AddPersonStage addpersonstage = new AddPersonStage();

    private AddPersonStage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResourceAsStream("/fxml/addPersonWindow.fxml"));
            root.getStylesheets().add(getClass().getResource("/styles/addpersonwindow.css").toExternalForm());
            Scene scene = new Scene(root);
            setScene(scene);
            setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(AddPersonWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static AddPersonStage getAddpersonstage() {
        return addpersonstage;
    }

}
