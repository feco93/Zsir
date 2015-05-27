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

import hu.zsir.game.model.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A model class for the check operator.
 *
 * The check operator sets the value of the property check of the current player
 * to true.
 *
 * @author Feco
 */
public class CheckOperator implements Operator {

    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    /**
     * The check operator.
     */
    private static final CheckOperator checkoperator = new CheckOperator();

    /**
     * Indicates whether the check operator is applicable.
     *
     * @param game the state of the game
     * @return true if the check operator is applicable
     */
    @Override
    public boolean isApplicable(Game game) {
        return !game.getTable().getCards().isEmpty() && !BeatOperator.getBeatoperator().isApplicable(game) && game.getTable().getCards().size() % 2 == 0;
    }

    /**
     * Applies the check operator on the specified state of the
     * <code>game</code>.
     *
     * @param game the state of the game
     */
    @Override
    public void apply(Game game) {
        game.getCurrentplayer().check();
        game.swapPlayers();
        logger.trace("Apply check operator.");
    }

    /**
     * Gets the check operator.
     *
     * @return the check operator
     */
    public static CheckOperator getCheckoperator() {
        return checkoperator;
    }

    /**
     * Gets the string representation of the check operator.
     *
     * @return the string representation of the check operator
     */
    @Override
    public String toString() {
        return "CheckOperator";
    }

}
