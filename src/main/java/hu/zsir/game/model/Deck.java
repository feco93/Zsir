package hu.zsir.game.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Model class for a deck.
 *
 * @author Feco
 */
public class Deck {

    /**
     * Cards in this deck.
     */
    private final Stack<Card> cards;
    /**
     * Remaining cards in this deck.
     */
    private int remainingcards;
    /**
     * How many draw occured on this deck.
     */
    private int drawcounter;

    /**
     * Constructs a new Deck object.
     */
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
        remainingcards = 32;
        drawcounter = 0;
    }

    /**
     * Returns the specified number of cards from this deck.
     *
     * @param count the required number of cards
     * @return right cardinality cards from this deck
     */
    public List<Card> getCards(int count) {
        List<Card> toDraw = new ArrayList<>();
        drawcounter++;
        if (drawcounter % 2 == 1) {
            if (count * 2 >= remainingcards) {
                count = remainingcards / 2;
            }
        } else {
            if (count > remainingcards) {
                count = remainingcards;
            }
        }

        for (int cardIndex = 0; cardIndex < count; ++cardIndex) {
            remainingcards--;
            toDraw.add(cards.pop());
        }
        return toDraw;
    }

    /**
     * Indicates whether the deck is empty.
     * 
     * @return true if this deck is empty
     */
    public boolean isEmpty() {
        return remainingcards == 0;
    }
}
