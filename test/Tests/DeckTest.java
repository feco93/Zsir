/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import hu.zsir.Computer;
import hu.zsir.Deck;
import hu.zsir.Human;
import hu.zsir.Player;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Feco
 */
public class DeckTest {

    Deck deck;
    Player playerOne;
    Player playerTwo;

    @Before
    public void setUp() {
        deck = new Deck();
        assertEquals(32, deck.getRemainingCards());
        playerOne = new Human(new ArrayList<>());
        playerTwo = new Computer(new ArrayList<>());
    }

    @Test
    public void drawOneCard() {
        drawCard(1, 1, 1);
        assertEquals(31, deck.getRemainingCards());
        assertEquals(1, deck.getDrawCounter());
    }

    @Test
    public void drawFourCards() {
        drawCard(1, 1, 4);
        assertEquals(4, playerOne.getCardCounter());
        assertEquals(1, deck.getDrawCounter());
    }

    @Test
    public void drawTwoCards() {
        deck.drawCard(2, playerOne);
        assertEquals(2, playerOne.getCardCounter());
        assertEquals(1, deck.getDrawCounter());
    }

    @Test
    public void emptyDeck() {
        drawCard(2, 4, 4);
        assertEquals(8, deck.getDrawCounter());
        assertTrue(deck.isEmpty());
    }

    @Test
    public void twoPlayerDraw8Cards() {
        drawCard(2, 1, 4);
        assertEquals(deck.getDrawCounter(), 2);
        assertEquals(deck.getRemainingCards(), 24);
    }

    @Test
    public void complexDrawing() {
        drawCard(2, 3, 4);
        deck.drawCard(2, playerOne);
        deck.drawCard(2, playerTwo);
        assertEquals(8, deck.getDrawCounter());
        assertEquals(4, deck.getRemainingCards());
        deck.drawCard(3, playerOne);
        assertEquals(2, deck.getRemainingCards());
        deck.drawCard(4, playerTwo);
        assertTrue(deck.isEmpty());
    }

    @Test
    public void complexDrawing2() {
        drawCard(2, 3, 4);
        deck.drawCard(2, playerOne);
        deck.drawCard(2, playerTwo);
        deck.drawCard(4, playerOne);
        assertEquals(2, deck.getRemainingCards());
    }

    private void drawCard(int howManyPlayer, int howManyDraw, int howManyCard) {
        if (howManyPlayer == 2) {
            for (int drawCounter = 0; drawCounter < howManyDraw; ++drawCounter) {
                deck.drawCard(howManyCard, playerOne);
                deck.drawCard(howManyCard, playerTwo);
            }
        }
        else if (howManyPlayer == 1) {
            for (int drawCounter = 0; drawCounter < howManyDraw; ++drawCounter) {
                deck.drawCard(howManyCard, playerOne);
            }
        }
    }
}
