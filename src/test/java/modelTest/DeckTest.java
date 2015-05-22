/*
 * Copyright (C) 2015 University of Debrecen, Faculty of Informatics
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package modelTest;

import hu.zsir.game.model.Card;
import hu.zsir.game.model.Deck;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Feco
 */
public class DeckTest {

    private Deck deck;

    @Before
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void fullDeck() {
        assertEquals(deck.getSize(), 32);
    }

    @Test
    public void drawFourCards() {
        List<Card> cards = deck.getCards(4);
        assertEquals(4, cards.size());
    }

    @Test
    public void drawOneCard() {
        List<Card> cards = deck.getCards(1);
        assertEquals(1, cards.size());
    }

    @Test
    public void complexDrawing() {
        for (int i = 0; i < 6; i++) {
            deck.getCards(4);
        }
        assertEquals(8, deck.getSize());
        deck.getCards(2);
        assertEquals(6, deck.getSize());
        deck.getCards(2);
        assertEquals(4, deck.getSize());
        List<Card> cards = deck.getCards(4);
        assertEquals(2, cards.size());
    }

    @Test
    public void complexDrawing2() {
        for (int i = 0; i < 6; i++) {
            deck.getCards(4);
        }
        assertEquals(8, deck.getSize());
        deck.getCards(1);
        assertEquals(7, deck.getSize());
        deck.getCards(1);
        assertEquals(6, deck.getSize());
        List<Card> cards = deck.getCards(4);
        assertEquals(3, cards.size());
    }

    @Test
    public void testEmptyDeck() {
        for (int i = 0; i < 8; i++) {
            deck.getCards(4);
        }
        assertTrue(deck.isEmpty());
    }

    @After
    public void tearDown() {
    }

}
