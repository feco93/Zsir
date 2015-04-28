package hu.zsir;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import hu.zsir.score.ScoreDialog;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;

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

    @FXML
    private void newGame(ActionEvent event) {
        if (gameStatus.get() == Status.STOPPED) {
            start();
        } else {
            ConfirmDialog confirmDialog = ConfirmDialog.getDialog();
            Optional<ButtonType> result = confirmDialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                restart();
            }
        }
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

    @FXML
    public void showScoreDialog() {
        ScoreDialog scoredialog = ScoreDialog.getDialog();
        scoredialog.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameStatus = new SimpleObjectProperty<>(Status.STOPPED);
    }

    private ImageView[] playerOneCardViews;
    private ImageView[] playerTwoCardViews;
    private Game game;
    public ObjectProperty<Status> gameStatus;
    private Timeline timeline;

    private void initContent() {
        playerOneCardViews = new ImageView[]{player11, player12, player13, player14};
        playerTwoCardViews = new ImageView[]{player21, player22, player23, player24};
        updateCardViews(playerOneCardViews, game.getPlayerOne());
        updateCardViews(playerTwoCardViews, game.getPlayerTwo());
        callingCardView.setVisible(false);
        topCardView.setVisible(false);
        checkButton.setVisible(true);
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

    public void start() {
        game = new Game();
        initContent();
        gameStatus.set(Status.RUNNING);
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(Duration.millis(400), (ActionEvent actionEvent) -> {
            game.nextLoop();
            showPlayedCards();
            updateCardViews(playerOneCardViews, game.getPlayerOne());
            updateCardViews(playerTwoCardViews, game.getPlayerTwo());
            if(game.isGameOver()) {
                gameStatus.set(Status.STOPPED);
            }
        });
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    private void restart() {
        timeline.stop();
        start();
    }

}
