/* 
 * Copyright (C) 2015 Feco
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
package hu.zsir.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class for table.
 * 
 * @author Feco
 */
public class Table {

    /**
     * Cards on this table.
     */
    private final List<Card> cards;

    /**
     * Constructs a new Table object.
     */
    public Table() {
        cards = new ArrayList<>();
    }

    /**
     * Gets the cards from this table.
     * 
     * @return the list of cards on this table
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Adds the specified card to the cards of this table.
     * 
     * @param card card to be added to the cards of this table
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Removes the cards of this table.
     */
    public void clear() {
        cards.clear();
    }

    /**
     * Gets the bottom card from the cards of this table.
     * 
     * @return the bottom card from the cards of this table
     */
    public Card getBottomCard() {
        if (cards.size() > 0) {
            return cards.get(0);
        } else {
            return null;
        }
    }

    /**
     * Gets the top card from the cards of this table.
     * 
     * @return the top card from the cards of this table
     */
    public Card getTopCard() {
        return cards.get(cards.size() - 1);
    }

}
