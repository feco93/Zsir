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
 * A model class for the draw operator.
 *
 * The draw operator draws cards from the table.
 *
 * @author Feco
 */
public class DrawOperator implements Operator {

    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    /**
     * The draw operator.
     */
    private static final DrawOperator drawoperator = new DrawOperator();

    /**
     * Indicates whether the draw operator is applicable.
     *
     * @param game the state of the game
     * @return true if the draw operator is applicable
     */
    @Override
    public boolean isApplicable(Game game) {
        return !game.getDeck().isEmpty() && game.getCurrentplayer().cards.size() < 4 && game.getNextplayer().cards.size() < 4;
    }

    /**
     * Applies the draw operator on the specified state of the
     * <code>game</code>.
     *
     * @param game the state of the game
     */
    @Override
    public void apply(Game game) {
        logger.trace("Apply draw operator.");
        game.getCurrentplayer().addCards(game.getDeck().getCards(4 - game.getCurrentplayer().cards.size()));
        game.getNextplayer().addCards(game.getDeck().getCards(4 - game.getNextplayer().cards.size()));
    }

    /**
     * Gets the draw operator.
     *
     * @return the draw operator
     */
    public static DrawOperator getDrawoperator() {
        return drawoperator;
    }

    /**
     * Gets the string representation of the draw operator.
     *
     * @return the string representation of the draw operator
     */
    @Override
    public String toString() {
        return "DrawOperator";
    }

}
