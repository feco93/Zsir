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
 * Model abstract class for a player.
 *
 * @author Feco
 */
public abstract class Player {

    /**
     * Cards in this player hand.
     */
    public List<Card> cards;
    /**
     * Score of this player.
     */
    private int score;
    /**
     * Choosed card for call.
     */
    protected Card choosedCard;
    /**
     * Indicates whether this player is checked.
     */
    protected boolean checked;

    /**
     * Constructs a new Player object.
     */
    public Player() {
        cards = new ArrayList<>();
        score = 0;
        checked = false;
    }

    /**
     * Gets the value of score.
     *
     * @return the score of this player
     */
    public int getScore() {
        return score;
    }

    /**
     * Add the specified score to the score of this player.
     *
     * @param score how many score to be added to the score of this player
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Add the specified list of cards to the cards of this player.
     *
     * @param cards list of cards to be added to the cards of this player
     */
    public void addCards(List<Card> cards) {
        cards.stream().forEach((card) -> {
            this.cards.add(card);
        });
    }

    /**
     * Gets the choosed card.
     *
     * @return choosed card
     */
    public Card getChoosedCard() {
        return choosedCard;
    }

    /**
     * Removes and returns the choosed card from the deck.
     *
     * @return choosed card
     */
    public Card putCard() {
        int i = 0;
        for (Card card : cards) {
            if (card.equals(getChoosedCard())) {
                return cards.remove(i);
            }
            ++i;
        }
        return null;
    }

    /**
     * Validates the choosing.
     *
     * @param choosedCard choosed card
     * @param table table of the game
     * @return true if the choosing is valid
     */
    public boolean isValidChoose(Card choosedCard, Table table) {
        if (table.getCards().size() > 0) {
            if (table.getCards().size() % 2 == 0) {
                return table.getBottomCard().getRank() == choosedCard.getRank()
                        || choosedCard.getRank() == Rank.HET;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * Indicates whether this player is checked.
     *
     * @return true if this player is checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * Sets the value of the property checked to the specified <code>flag</code>
     * value.
     *
     * @param flag the new value of the check field
     */
    public void setCheck(boolean flag) {
        checked = flag;
    }

    /**
     * Abstract method for check.
     */
    public abstract void check();

    /**
     * Abstract method for choosing card.
     *
     * @param game state of the game
     */
    public abstract void chooseCard(Game game);

    /**
     * Abstract method for set choosed card.
     *
     * @param card card to be choosed
     */
    public abstract void setChoosedCard(Card card);

    /**
     * Abstract method for choosing operator.
     *
     * @param game state of the game
     */
    public abstract void chooseOperator(Game game);

    /**
     * Abstract method for indicate whether this player is human.
     *
     * @return true if this player is human
     */
    public abstract boolean isHuman();

}
