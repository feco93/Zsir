/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game;

import hu.zsir.scoretable.ScoreDialog;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 *
 * @author Feco
 */
public class MainController implements Initializable {

    @FXML
    private ImageView player11;
    @FXML
    private ImageView player12;
    @FXML
    private ImageView player13;
    @FXML
    private ImageView player14;
    @FXML
    private ImageView player21;
    @FXML
    private ImageView player22;
    @FXML
    private ImageView player23;
    @FXML
    private ImageView player24;
    @FXML
    private ImageView callingCardView;
    @FXML
    private ImageView topCardView;
    @FXML
    private Text scoreText;
    @FXML
    private Button checkButton;

    private Game game;
    private ImageView[] playerOneCardViews;
    private ImageView[] playerTwoCardViews;

    @FXML
    private void newGame(ActionEvent event) {
        checkButton.setVisible(true);
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
        int cardIndex = 0;
        for (Card card : game.getPlayerB().cards) {
            Image cardImage = new Image("/images/hatlap.jpg");
            playerTwoCardViews[cardIndex].setVisible(true);
            playerTwoCardViews[cardIndex].setImage(cardImage);
            cardIndex++;
        }
        while (cardIndex < playerTwoCardViews.length) {
            playerTwoCardViews[cardIndex++].setVisible(false);
        }

        cardIndex = 0;
        for (Card card : game.getPlayerA().cards) {
            Image cardImage = new Image("/images/" + card.toString() + ".jpg");
            playerOneCardViews[cardIndex].setVisible(true);
            playerOneCardViews[cardIndex].setImage(cardImage);
            playerOneCardViews[cardIndex].setOnMouseClicked(event -> {
                if (!game.getCurrentplayer().isComputer()) {
                    if (game.getCurrentplayer().isValidChoose(card, game.getTable())) {
                        game.getCurrentplayer().setChoosedCard(card);                        
                    }
                }
            });
            cardIndex++;
        }
        while (cardIndex < playerOneCardViews.length) {
            playerOneCardViews[cardIndex++].setVisible(false);
        }
        if (game.getTable().getBottomCard() != null) {
            callingCardView.setVisible(true);
            Image cardImage = new Image("/images/" + game.getTable().getBottomCard().toString() + ".jpg");
            callingCardView.setImage(cardImage);
        } else {
            callingCardView.setVisible(false);
        }
        if (game.getTable().getCards().size() > 1) {
            topCardView.setVisible(true);
            Image cardImage = new Image("/images/" + game.getTable().getTopCard().toString() + ".jpg");
            topCardView.setImage(cardImage);
        } else {
            topCardView.setVisible(false);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerOneCardViews = new ImageView[]{player11, player12, player13, player14};
        playerTwoCardViews = new ImageView[]{player21, player22, player23, player24};
        callingCardView.setVisible(false);
        topCardView.setVisible(false);
        checkButton.setVisible(false);
    }
}
