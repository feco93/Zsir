package hu.zsir;

import java.util.ArrayList;
import java.util.List;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Feco
 */
public class Game {

    private Deck deck;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    private Player otherPlayer;
    private Card callingCard;
    private List<Card> playedCard;
    private boolean gameOver;

    public Game() {
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
    }

    public void gameLoop() {
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
    }

    private void clearTable() {
        callingCard = null;
        playedCard.clear();
    }

    public void swapPlayers() {
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

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Card getCallingCard() {
        return callingCard;
    }

    public List<Card> getPlayedCard() {
        return playedCard;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
}
