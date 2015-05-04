package hu.zsir.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    private final Stack<Card> cards;
    private int remainingCards;
    private int drawCounter;

    public Deck() {
        cards = new Stack<>();
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
        remainingCards = 32;
        drawCounter = 0;
    }

    public List<Card> getCards(int count) {
        List<Card> toDraw = new ArrayList<>();
        drawCounter++;
        if (drawCounter % 2 == 1) {
            if (count * 2 >= remainingCards) {
                count = remainingCards / 2;
            }
        } else {
            if (count > remainingCards) {
                count = remainingCards;
            }
        }

        for (int cardIndex = 0; cardIndex < count; ++cardIndex) {
            remainingCards--;
            toDraw.add(cards.pop());
        }
        return toDraw;
    }

    public boolean isEmpty() {
        return remainingCards == 0;
    }
}
