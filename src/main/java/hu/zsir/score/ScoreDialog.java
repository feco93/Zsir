/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.score;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Feco
 */
public class ScoreDialog extends Stage {

    private static ScoreDialog dialog = new ScoreDialog();

    private ScoreDialog() {
        super();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/score.fxml"));
            Scene scene = new Scene(root);
            setScene(scene);
            setResizable(false);
            setTitle("Score table");
            getIcons().add(new Image(getClass().getResource("/images/Zsir.ico").toString()));
        } catch (IOException ex) {
            Logger.getLogger(ScoreDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ScoreDialog getDialog() {
        return dialog;
    }

}
