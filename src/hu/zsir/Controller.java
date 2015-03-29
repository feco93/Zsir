package hu.zsir;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
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
        if (currentPlayer instanceof Human && playedCard.size() > 0 && playedCard.size() % 2 == 0) {
            currentPlayer.setPassed(true);
            swapPlayers();
        }
    }

    Deck deck;
    Player playerOne;
    Player playerTwo;
    Player currentPlayer;
    Player otherPlayer;
    Card callingCard;
    List<Card> playedCard;
    Timeline timeline;
    AnimationTimer timer;
    private boolean gameOver;

    private ImageView[] playerOneCardViews;
    private ImageView[] playerTwoCardViews;

    private void initContent() {
        playerOneCardViews = new ImageView[]{player11, player12, player13, player14};
        playerTwoCardViews = new ImageView[]{player21, player22, player23, player24};
        updateCardViews(playerOneCardViews, playerOne);
        updateCardViews(playerTwoCardViews, playerTwo);
        callingCardView.setVisible(false);
        topCardView.setVisible(false);
    }

    private void updateCardViews(ImageView[] cardViews, Player player) {
        int cardIndex = 0;
        for (Card card : player.cards) {
            Image cardImage;
            if (player instanceof Human) {
                String cardName = card.toString();
                cardImage = new Image("resources/" + cardName + ".jpg");
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
        if (callingCard != null) {
            callingCardView.setVisible(true);
            String cardName = callingCard.toString();
            Image cardImage = new Image("resources/" + cardName + ".jpg");
            callingCardView.setImage(cardImage);
        } else {
            callingCardView.setVisible(false);
        }
        if (playedCard.size() > 1) {
            topCardView.setVisible(true);
            String cardName = playedCard.get(playedCard.size() - 1).toString();
            Image cardImage = new Image("resources/" + cardName + ".jpg");
            topCardView.setImage(cardImage);
        } else {
            topCardView.setVisible(false);
        }
    }

    private void showScoreDialog() {
        Stage dialogStage = new Stage();

        Text text = new Text();
        text.setFont(new Font(22));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("Your score is: " + String.valueOf(playerOne.getScore()));
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
        gameOver = false;
        deck = new Deck();
        callingCard = null;
        playedCard = new ArrayList<>();
        List<Card> playerOneCards = new ArrayList<>();
        List<Card> playerTwoCards = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            playerOneCards.add(deck.drawCard());
            playerTwoCards.add(deck.drawCard());
        }
        playerOne = new Human(playerOneCards);
        playerTwo = new Computer(playerTwoCards);
        currentPlayer = playerOne;
        otherPlayer = playerTwo;
        initContent();
        gameLoop();
        timeline.play();
        timer.start();
    }

    private void gameLoop() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(Duration.millis(400), (ActionEvent event) -> {
            if (!deck.isEmpty() || !playerOne.cards.isEmpty() || !playerTwo.cards.isEmpty()) {
                if (playedCard.size() > 0 && playedCard.size() % 2 == 0
                        && playedCard.get(playedCard.size() - 1).number != callingCard.number
                        && playedCard.get(playedCard.size() - 1).number != Number.HET) {
                    beatCards();
                }
                if (currentPlayer instanceof Human) {
                    Human human = (Human) currentPlayer;
                    if (callingCard == null) {
                        if (human.getSelectedCard() != null) {
                            callingCard = human.putCard();
                            playedCard.add(callingCard);
                            swapPlayers();
                        }
                    } else if (playedCard.size() % 2 == 1) {
                        if (human.getSelectedCard() != null) {
                            playedCard.add(human.putCard());
                            swapPlayers();
                        }
                    } else if (otherPlayer.isPassed()) {
                        beatCards();
                        otherPlayer.setPassed(false);
                    } else if (human.canPut(callingCard)) {
                        if (human.getSelectedCard() != null && (human.getSelectedCard().number == Number.HET
                                || human.getSelectedCard().number == callingCard.number)) {
                            playedCard.add(human.putCard());
                            swapPlayers();
                        }
                    } else {
                        currentPlayer.setPassed(true);
                        swapPlayers();
                    }
                } else {
                    Computer computer = (Computer) currentPlayer;
                    if (callingCard == null) {
                        callingCard = computer.putCard();
                        playedCard.add(callingCard);
                        swapPlayers();
                    } else if (playedCard.size() % 2 == 1) {
                        playedCard.add(computer.putCard());
                        swapPlayers();
                    } else if (otherPlayer.isPassed()) {
                        beatCards();
                        otherPlayer.setPassed(false);
                    } else if (computer.canPut(callingCard)) {
                        playedCard.add(computer.putCard(callingCard));
                        swapPlayers();
                    } else {
                        currentPlayer.setPassed(true);
                        swapPlayers();
                    }
                }
            } else {
                clearTable();
                gameOver = true;
            }

        });
        timeline.getKeyFrames().add(kf);
        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                showPlayedCards();
                updateCardViews(playerOneCardViews, playerOne);
                updateCardViews(playerTwoCardViews, playerTwo);
                if (gameOver) {
                    timeline.stop();
                    stop();
                    showScoreDialog();
                }
            }
        };
    }

    private void clearTable() {
        callingCard = null;
        playedCard.clear();
    }

    private void swapPlayers() {
        Player temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;
    }

    private void beatCards() {
        currentPlayer.beat(playedCard);
        if (!deck.isEmpty()) {
            deck.drawCard(4 - currentPlayer.getCardCounter(), currentPlayer);
            deck.drawCard(4 - otherPlayer.getCardCounter(), otherPlayer);
        }
        clearTable();
    }

}
