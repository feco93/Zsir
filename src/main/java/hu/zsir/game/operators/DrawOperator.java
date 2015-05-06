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

/**
 *
 * @author Feco
 */
public class DrawOperator implements Operator {

    private static DrawOperator drawoperator = new DrawOperator();

    private DrawOperator() {
    }

    @Override
    public boolean isApplicable(Game game) {
        return !game.getDeck().isEmpty() && game.getCurrentplayer().cards.size() < 4 && game.getNextplayer().cards.size() < 4;
    }

    @Override
    public void apply(Game game) {
        game.getCurrentplayer().addCards(game.getDeck().getCards(4 - game.getCurrentplayer().cards.size()));
        game.getNextplayer().addCards(game.getDeck().getCards(4 - game.getNextplayer().cards.size()));
    }

    public static DrawOperator getDrawoperator() {
        return drawoperator;
    }

}
