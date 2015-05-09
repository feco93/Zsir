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

import hu.zsir.game.operators.CallOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Model class for Player.
 *
 * @author Feco
 */
public class Human extends Player {

    /**
     * Waits for choosing then call the choosed card.
     *
     * @param game state of the game
     */
    @Override
    synchronized public void chooseCard(Game game) {
        try {
            wait();
            CallOperator.getCallOperator().apply(game);
        } catch (InterruptedException ex) {
            Logger.getLogger(Human.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets the choosed card then notify the waiting thread.
     *
     * @param card the choosed card
     */
    @Override
    synchronized public void setChoosedCard(Card card) {
        this.choosedCard = card;
        notify();
    }

    /**
     * Sets the checked property to true then notify the waiting thread.
     */
    @Override
    synchronized public void check() {
        checked = true;
        notify();
    }

    /**
     * Waits for choosing an operator then apply it.
     *
     * @param game state of the game
     */
    @Override
    synchronized public void chooseOperator(Game game) {
        try {
            wait();
            if (choosedCard != null) {
                CallOperator.getCallOperator().apply(game);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Human.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Indicates whether this player is human.
     * 
     * @return returns true
     */
    @Override
    public boolean isHuman() {
        return true;
    }

}
