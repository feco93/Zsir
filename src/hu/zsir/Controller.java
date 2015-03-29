package hu.zsir;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
    private void newGame(ActionEvent event) {
        start();
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void check(ActionEvent event) {
        if (game.getCurrentPlayer() instanceof Human &&
                game.getPlayedCard().size() > 0 && game.getPlayedCard().size() % 2 == 0) {
            game.getCurrentPlayer().setPassed(true);
            game.swapPlayers();
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
                cardImage = card.getCardImage();
                cardViews[cardIndex].setOnMouseClicked(event -> {
                    Human human = (Human) player;
                    human.setSelectedCard(card);
                });
            } else {
                cardImage = new Image("resources/hatlap.jpg");
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
        if (game.getCallingCard() != null) {
            callingCardView.setVisible(true);
            callingCardView.setImage(game.getCallingCard().getCardImage());
        } else {
            callingCardView.setVisible(false);
        }
        if (game.getPlayedCard().size() > 1) {
            topCardView.setVisible(true);
            topCardView.setImage(game.getPlayedCard().get(game.getPlayedCard().size() - 1).getCardImage());
        } else {
            topCardView.setVisible(false);
        }
    }

    private void showScoreDialog() {
        Stage dialogStage = new Stage();

        Text text = new Text();
        text.setFont(new Font(22));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("Your score is: " + String.valueOf(game.getPlayerOne().getScore()));
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER);
        box.getChildren().add(text);
        Parent root = new Pane(box);
        Scene scene = new Scene(root);

        dialogStage.setMinWidth(300);
        dialogStage.setMinHeight(300);
        dialogStage.setTitle("Score");
        dialogStage.setResizable(false);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    public void start() {
        game = new Game();
        initContent();
        gameLoop();
        timeline.play();
        timer.start();
    }

    private void gameLoop() {
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
