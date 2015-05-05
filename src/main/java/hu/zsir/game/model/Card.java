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
    private final Number number;
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
     * @param number number property of this card
     * @param suit suit property of this card
     */
    public Card(Number number, Suit suit) {
        this.number = number;
        this.suit = suit;
    }

    /**
     * Gets the value of the property Number.
     *
     * @return the value of the property Number
     */
    public Number getNumber() {
        return number;
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
        return suit.toString().toLowerCase() + "_" + number.toString().toLowerCase();
    }

}
