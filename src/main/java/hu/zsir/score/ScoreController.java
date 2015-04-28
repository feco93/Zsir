/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.score;

import hu.zsir.Person;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 *
 * @author Feco
 */
public class ScoreController {
    
    @FXML
    private TableView<Person> tableview;
    
    private ScoreService scoreservice = new ScoreService();
    
    public void initialize() {
        scoreservice.start();
        scoreservice.setOnSucceeded((WorkerStateEvent event) -> {
            tableview.setItems((ObservableList<Person>)event.getSource().getValue());
        });
    }
    
}
