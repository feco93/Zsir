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

    Deck deck;
    Human playerOne;
    Human playerTwo;
    Human currentPlayer;
    Human otherPlayer;
    Card callingCard;
    List<Card> playedCard;

    private void swapPlayers() {
        Human temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;
    }

    public void newGame() {
        deck = new Deck();
        List<Card> playerOneCards = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            playerOneCards.add(deck.drawCard());
        }
        playerOne = new Human(playerOneCards);
        List<Card> playerTwoCards = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            playerTwoCards.add(deck.drawCard());
        }
        playerTwo = new Human(playerTwoCards);
        currentPlayer = playerOne;
        otherPlayer = playerTwo;
    }

    public void gameLoop() {
        
        while (true) {
            if (deck.isEmpty()) {
                break;
            } else if (callingCard == null && currentPlayer.selectedCard != null) {
                callingCard = currentPlayer.putCard();
                playedCard.add(callingCard);
            }
            /*else if (callingCard != null && currentPlayer.canPut(callingCard) && currentPlayer.selectedCard != null) {
             playedCard.add(currentPlayer.putCard());
             } 
             if (otherPlayer.passed) {
             currentPlayer.beat(playedCard);
             currentPlayer.drawCards(deck);
             otherPlayer.drawCards(deck);
             }
             swapPlayers();*/
        }

    }

}
