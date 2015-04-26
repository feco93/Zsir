package hu.zsir;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
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
        boolean restart = false;
        if (!game.isRunning()) {
            start();
        }
        else {
            ConfirmDialog confirmDialog = ConfirmDialog.getDialog();
            Optional<ButtonType> result = confirmDialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                restart = true;
            }
        }
        if (restart) {
            game.cancel();
            game = new Game();
            game.start();
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

    public static Game game = new Game();
    public static Timeline timeline;

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
    
    @FXML
    public void showScoreDialog() {
        ScoreDialog scoredialog = ScoreDialog.getDialog();
        scoredialog.show();
    }

    public void start() {
        game.start();
        initContent();
        game.setOnRunning(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                timeline = new Timeline();
                timeline.setCycleCount(Timeline.INDEFINITE);
                KeyFrame kf = new KeyFrame(Duration.millis(400), (ActionEvent actionEvent) -> {
                    showPlayedCards();
                    updateCardViews(playerOneCardViews, game.getPlayerOne());
                    updateCardViews(playerTwoCardViews, game.getPlayerTwo());
                });
                timeline.getKeyFrames().add(kf);
                timeline.play();
            }
        });
        game.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                timeline.stop();
                showScoreDialog();
            }
        });
    }
}
