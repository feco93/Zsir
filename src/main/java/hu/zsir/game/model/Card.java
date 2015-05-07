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

/**
 * Model class for a card.
 *
 * @author Feco
 */
public class Card {

    /**
     * Number of the card.
     */
    private final Rank rank;
    /**
     * Suit of the card.
     */
    private final Suit suit;
    /**
     * Indicates whether the card is face up.
     */
    private boolean faceup = true;

    /**
     * Constructs a new Card object with specified suit and number.
     *
     * @param rank number property of this card
     * @param suit suit property of this card
     */
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Gets the value of the property Number.
     *
     * @return the value of the property Number
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Gets the value of the property Suit.
     *
     * @return the value of the property Suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Sets the value of the property face up to false.
     *
     */
    public void turnDown() {
        faceup = false;
    }
    /**
     * Sets the value of the property face up to true.
     * 
     */

    public void turnUp() {
        faceup = true;
    }

    /**
     * Gets the value of the property face up.
     * 
     * @return indicates whether the card is face up 
     */
    public boolean isFaceup() {
        return faceup;
    }

    
    /**
     * Returns the string representation of this card.
     * 
     * @return the string representation of this card in the form
	 *         <span><em>suit</em><code>_</code><em>number</em></span>
     */
    @Override
    public String toString() {
        return suit.toString().toLowerCase() + "_" + rank.toString().toLowerCase();
    }

}
