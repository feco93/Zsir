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

import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 * FXML Controller class for controlling score table window.
 * 
 * @author Feco
 */
public class ScoreTableController {
    
    /**
     * The table view of the persons.
     */
    @FXML
    private TableView<Person> tableview;
    
    /**
     * The score table service.
     */
    private final ScoreTable scoreservice = new ScoreTable();
    
    /**
     * Starts the score table service.
     */
    public void initialize() {
        scoreservice.restart();
        scoreservice.setOnSucceeded((WorkerStateEvent event) -> {
            tableview.setItems((ObservableList<Person>)event.getSource().getValue());
        });
    }
    
}
