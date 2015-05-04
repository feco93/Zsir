/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game;

import hu.zsir.scoretable.ScoreDialog;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Feco
 */
public class MainController implements Initializable {

    @FXML
    private AnchorPane mainPane;

    private Game game;
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
        gameTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                updateContent();
                Boolean flag = (Boolean) event.getSource().getValue();
                if (flag) {
                    gameTask.restart();
                }
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
        if (CheckOperator.getCheckoperator().isApplicable(game)) {
            CheckOperator.getCheckoperator().apply(game);
        }
    }

    @FXML
    public void showScoreDialog() {
        ScoreDialog scoredialog = ScoreDialog.getDialog();
        scoredialog.show();
    }

    public void updateContent() {
        mainPane.getChildren().clear();

        if (CheckOperator.getCheckoperator().isApplicable(game)) {
            checkbutton.setVisible(true);
            mainPane.getChildren().add(checkbutton);
        }
        int index = 0;
        for (Card card : game.getPlayerB().cards) {
            ImageView view = new ImageView(Card.getBackImage());
            view.setLayoutX(160.0 + 120 * index);
            view.setLayoutY(10.0);
            view.setFitWidth(112.0);
            view.setFitHeight(186.0);
            mainPane.getChildren().add(view);
            ++index;
        }
        index = 0;
        for (Card card : game.getPlayerA().cards) {
            ImageView view = new ImageView(card.getFrontImage());
            view.setLayoutX(160.0 + 120 * index);
            view.setLayoutY(440.0);
            view.setFitWidth(112.0);
            view.setFitHeight(186.0);
            view.setOnMouseClicked(event -> {
                if (!game.getCurrentplayer().isComputer()) {
                    if (game.getCurrentplayer().isValidChoose(card, game.getTable())) {
                        game.getCurrentplayer().setChoosedCard(card);
                    }
                }
            });
            mainPane.getChildren().add(view);
            ++index;
        }
        index = 0;
        for (Card card : game.getTable().getCards()) {
            ImageView view = new ImageView(card.getFrontImage());
            view.setLayoutX(250.0 + 30 * index);
            view.setLayoutY(230.0);
            view.setFitWidth(112.0);
            view.setFitHeight(186.0);
            view.setOnMousePressed(event -> {
                view.toFront();
            });
            view.setOnMouseReleased(event -> {
                view.toBack();
            });
            mainPane.getChildren().add(view);
            ++index;
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkbutton = new Button("Check");
        checkbutton.setVisible(false);
        checkbutton.setLayoutX(90.0);
        checkbutton.setLayoutY(440.0);
        checkbutton.setOnMouseClicked(event -> {
            if (CheckOperator.getCheckoperator().isApplicable(game)) {
                CheckOperator.getCheckoperator().apply(game);
            }
        });
    }
}
