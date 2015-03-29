package hu.zsir;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Feco
 */
public class Deck {

    Stack cards;
    int reaminingCards;
    int drawCounter;

    public int getDrawCounter() {
        return drawCounter;
    }

    public Deck() {
        cards = new Stack();
        Card[] cardsArray = new Card[32];
        int i = 0;
        for (Suit suit : Suit.values()) {
            for (Number number : Number.values()) {
                cardsArray[i++] = new Card(number, suit);
            }
        }
        Collections.shuffle(Arrays.asList(cardsArray));
        for (Card card : cardsArray) {
            cards.push(card);
        }
        reaminingCards = 32;
        drawCounter = 0;
    }

    public Card drawCard() {
        drawCounter++;
        reaminingCards--;
        return (Card) cards.pop();
    }

    public void drawCard(int counter, Player player) {
        drawCounter++;
        if (drawCounter % 2 == 1) {
            if (counter >= reaminingCards) {
                counter = reaminingCards / 2;
            }
        } else {
            if (counter > reaminingCards) {
                counter = reaminingCards;
            }
        }

        for (int cardIndex = 0; cardIndex < counter; ++cardIndex) {
            reaminingCards--;
            player.drawCard((Card) cards.pop());
        }
    }

    public boolean isEmpty() {
        return reaminingCards == 0;
    }

    public int getReaminingCards() {
        return reaminingCards;
    }
}
