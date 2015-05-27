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
 * A model interface for an operator.
 *
 * An operator change the state of the game if the operator is applicable.
 *
 * @author Feco
 */
public interface Operator {

    /**
     * Inidicates whether the operator is applicable.
     *
     * @param game the state of the game
     * @return true if the operator is applicable
     */
    public boolean isApplicable(Game game);

    /**
     * Applies the operator on the specified state of the <code>game</code>.
     *
     * @param game the state of the game
     */
    public void apply(Game game);
}
