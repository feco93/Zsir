/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game;

import hu.zsir.scoretable.AddPersonStage;
import hu.zsir.scoretable.AddPersonWindowController;
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
        
        AddPersonStage.getAddpersonstage().show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
