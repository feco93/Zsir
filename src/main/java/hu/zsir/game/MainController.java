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

import static hu.zsir.game.Main.mainWindow;
import hu.zsir.game.model.Game;
import hu.zsir.game.operators.CheckOperator;
import hu.zsir.game.view.CardsInHands;
import hu.zsir.game.view.CardsOnTable;
import hu.zsir.scoretable.AddPersonStage;
import hu.zsir.scoretable.AddPersonWindowController;
import hu.zsir.scoretable.ScoreDialog;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Feco
 */
public class MainController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button checkbutton;

    @FXML
    private void newGame(ActionEvent event) {
        game = new Game();
        game.start();
        updateContent();
        Service<Boolean> gameTask = new Service<Boolean>() {

            @Override
            protected Task<Boolean> createTask() {
                return new Task<Boolean>() {

                    @Override
                    protected Boolean call() throws Exception {
                        return game.nextLoop();
                    }
                };
            }
        };
        gameTask.setOnSucceeded((WorkerStateEvent event1) -> {
            updateContent();
            Boolean flag = (Boolean) event1.getSource().getValue();
            if (flag) {
                gameTask.restart();
            } else {
                AddPersonWindowController.setScore(game.getPlayerA().getScore());
                AddPersonStage.getAddpersonstage().initOwner(mainWindow);
                AddPersonStage.getAddpersonstage().show();
            }
        });
        gameTask.start();
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void check(ActionEvent event) {
        if (CheckOperator.getCheckoperator().isApplicable(game) && game.getCurrentplayer().isHuman()) {
            CheckOperator.getCheckoperator().apply(game);
        }
    }

    @FXML
    public void showScoreDialog() {
        ScoreDialog scoredialog = ScoreDialog.getDialog();
        scoredialog.show();
    }

    private Game game;

    public void updateContent() {
        mainPane.getChildren().clear();

        mainPane.getChildren().add(checkbutton);

        CardsInHands cardinhands = new CardsInHands(game);
        CardsOnTable cardontable = new CardsOnTable(game);
        mainPane.getChildren().addAll(cardinhands.getCards());
        mainPane.getChildren().addAll(cardontable.getCards());
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
