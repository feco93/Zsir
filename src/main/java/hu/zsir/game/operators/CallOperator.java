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
package hu.zsir.game.operators;

import hu.zsir.game.model.Card;
import hu.zsir.game.model.Game;
import hu.zsir.game.model.Rank;
import hu.zsir.game.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A model class for call operator.
 *
 * The call operator call a card into the table.
 *
 * @author Feco
 */
public class CallOperator implements Operator {

    private static final Logger logger = LoggerFactory.getLogger(CallOperator.class);

    /**
     * The call operator.
     */
    private static final CallOperator calloperator = new CallOperator();

    /**
     * Indicates whether the call operator is applicable.
     *
     * @param game the state of the game
     * @return true if the call operator is applicable
     */
    @Override
    public boolean isApplicable(Game game) {
        return (!game.getCurrentplayer().cards.isEmpty()) && !game.getNextplayer().isChecked()
                && !BeatOperator.getBeatoperator().isApplicable(game)
                && (game.getTable().getCards().isEmpty() || game.getTable().getCards().size() % 2 == 1
                || canCall(game.getCurrentplayer(), game.getTable().getCards().get(0)));
    }

    /**
     * Applies the call operator on the specified state of the game.
     *
     * @param game the state of the game
     */
    @Override
    public void apply(Game game) {
        Card card = game.getCurrentplayer().putCard();
        card.turnUp();
        game.getTable().addCard(card);
        game.getCurrentplayer().setChoosedCard(null);
        game.swapPlayers();
        logger.trace("Apply call operator.");
    }

    /**
     * Indicates whether the specified player can call.
     *
     * @param currentplayer the current player
     * @param firstcard first card on the table
     * @return true if the specified player can call
     */
    private boolean canCall(Player currentplayer, Card firstcard) {
        for (Card card : currentplayer.cards) {
            if (card.getRank() == firstcard.getRank() || card.getRank() == Rank.HET) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the call operator.
     *
     * @return the call operator
     */
    public static CallOperator getCallOperator() {
        return calloperator;
    }

    /**
     * Gets the string representation of the call operator.
     *
     * @return the string representation of the call operator
     */
    @Override
    public String toString() {
        return "CallOperator";
    }

}
