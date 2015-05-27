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

import hu.zsir.game.model.Game;
import hu.zsir.game.operators.CheckOperator;
import hu.zsir.game.view.CardsInHands;
import hu.zsir.game.view.CardsOnTable;
import hu.zsir.scoretable.AddPersonDialog;
import hu.zsir.scoretable.AddPersonDialogController;
import hu.zsir.scoretable.ScoreTableDialog;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

/**
 * Controls the main window.
 *
 * @author Feco
 */
public class MainController {

    /**
     * The main pane of the main window.
     */
    @FXML
    private AnchorPane mainPane;
    /**
     * Check button for checking.
     */
    @FXML
    private Button checkbutton;

    /**
     * Starts a new game.
     *
     * @param event an event that occured on the new game menu item
     */
    @FXML
    private void newGame(ActionEvent event) {
        if (game == null) {
            startNewGame();
        } else if (!game.isGoal()) {
            ConfirmDialog confirmDialog = ConfirmDialog.getDialog();
            Optional<ButtonType> result = confirmDialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                startNewGame();
            }
        } else {
            startNewGame();
        }
    }

    /**
     * Terminates the process.
     *
     * @param event
     */
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Checks if the check operator is applicable.
     *
     * @param event an event that occured on the check button
     */
    @FXML
    private void check(ActionEvent event) {
        if (game != null && CheckOperator.getCheckoperator().isApplicable(game) && game.getCurrentplayer().isHuman()) {
            CheckOperator.getCheckoperator().apply(game);
        }
    }

    /**
     * Shows the score dialog.
     */
    @FXML
    public void showScoreDialog() {
        ScoreTableDialog scoredialog = new ScoreTableDialog();
        scoredialog.show();
    }

    /**
     * Game object for modelling the current game state.
     */
    private Game game;
    /**
     * Game service for executing the game.
     */
    private Service<Boolean> gameService;

    /**
     * Starts a new game.
     */
    private void startNewGame() {
        game = new Game();
        game.start();
        gameService = new Service<Boolean>() {

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
        gameService.setOnSucceeded((WorkerStateEvent event1) -> {
            updateContent();
            Boolean flag = (Boolean) event1.getSource().getValue();
            if (flag) {
                gameService.restart();
            } else {
                AddPersonDialogController.setScore(game.getPlayerA().getScore());
                AddPersonDialog.getAddpersonstage().show();
            }
        });
        updateContent();
        gameService.start();
    }

    /**
     * Updates the content of the main window.
     */
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
}
