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

    private final Deck deck;
    private final Player playerOne;
    private final Player playerTwo;
    private Player currentPlayer;
    private Player otherPlayer;
    private final List<Card> cardOnTable;
    private boolean gameOver;

    public Game() {
        gameOver = false;
        deck = new Deck();
        cardOnTable = new ArrayList<>();
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
        if (!deck.isEmpty() || !playerOne.cards.isEmpty() || !playerTwo.cards.isEmpty() || !cardOnTable.isEmpty()) {
            if (currentPlayer.isPassed()) {
                currentPlayer.setPassed(false);
                swapPlayers();
            }
            else if(!currentPlayer.isCanput()) {
                currentPlayer.setCanput(true);
                swapPlayers();
                currentPlayer.beat(cardOnTable);
            }
            else {
                currentPlayer.nextDecision(cardOnTable, deck);
            }
            if (currentPlayer.isBeated()) {
                if (!deck.isEmpty()) {
                    deck.drawCard(4 - currentPlayer.getCardCounter(), currentPlayer);
                    deck.drawCard(4 - otherPlayer.getCardCounter(), otherPlayer);
                }
                currentPlayer.setBeated(false);
                clearTable();
            }
        } else {
            gameOver = true;
            clearTable();
        }
    }

    private void clearTable() {
        cardOnTable.clear();
    }

    public void swapPlayers() {
        Player temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;
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

    public List<Card> getPlayedCard() {
        return cardOnTable;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Card getBottomCard() {
        if (cardOnTable.size() > 0) {
            return cardOnTable.get(0);
        } else {
            return null;
        }
    }
}
