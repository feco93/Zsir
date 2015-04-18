package hu.zsir;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Feco
 */
public class Controller {

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
    private void newGame(ActionEvent event) {
        start();
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void check(ActionEvent event) {
        if (game != null) {
            if (game.getCurrentPlayer() instanceof Human
                    && game.getPlayedCard().size() > 0 && game.getPlayedCard().size() % 2 == 0) {
                game.getCurrentPlayer().setCanput(false);
            }
        }
    }

    Game game;
    Timeline timeline;
    AnimationTimer timer;

    private ImageView[] playerOneCardViews;
    private ImageView[] playerTwoCardViews;

    private void initContent() {
        playerOneCardViews = new ImageView[]{player11, player12, player13, player14};
        playerTwoCardViews = new ImageView[]{player21, player22, player23, player24};
        updateCardViews(playerOneCardViews, game.getPlayerOne());
        updateCardViews(playerTwoCardViews, game.getPlayerTwo());
        callingCardView.setVisible(false);
        topCardView.setVisible(false);
    }

    private void updateCardViews(ImageView[] cardViews, Player player) {
        int cardIndex = 0;
        for (Card card : player.cards) {
            Image cardImage;
            if (player instanceof Human) {
                cardImage = new Image("/images/" + card.toString() + ".jpg");
                cardViews[cardIndex].setOnMouseClicked(event -> {
                    Human human = (Human) player;
                    human.setSelectedCard(card);
                });
            } else {
                cardImage = new Image("/images/hatlap.jpg");
            }
            cardViews[cardIndex].setVisible(true);
            cardViews[cardIndex].setImage(cardImage);

            ++cardIndex;
        }
        while (cardIndex < cardViews.length) {
            cardViews[cardIndex].setVisible(false);
            ++cardIndex;
        }
    }

    private void showPlayedCards() {
        if (game.getBottomCard() != null) {
            callingCardView.setVisible(true);
            Image cardImage = new Image("/images/" + game.getBottomCard().toString() + ".jpg");
            callingCardView.setImage(cardImage);
        } else {
            callingCardView.setVisible(false);
        }
        if (game.getPlayedCard().size() > 1) {
            topCardView.setVisible(true);
            Image cardImage
                    = new Image("/images/" + game.getPlayedCard().
                            get(game.getPlayedCard().size() - 1).toString() + ".jpg");
            topCardView.setImage(cardImage);
        } else {
            topCardView.setVisible(false);
        }
    }

    private void showScoreDialog() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/score.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            Stage dialogStage = new Stage();
            scoreText.setText("Your score is: " + String.valueOf(game.getPlayerOne().getScore()));
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            dialogStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void start() {
        game = new Game();
        initContent();
        initTimeLine();
        timeline.play();
        timer.start();
    }

    private void initTimeLine() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(Duration.millis(400), (ActionEvent event) -> {
            game.gameLoop();
        });
        timeline.getKeyFrames().add(kf);
        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                showPlayedCards();
                updateCardViews(playerOneCardViews, game.getPlayerOne());
                updateCardViews(playerTwoCardViews, game.getPlayerTwo());
                if (game.isGameOver()) {
                    timeline.stop();
                    stop();
                    showScoreDialog();
                }
            }
        };
    }
}
