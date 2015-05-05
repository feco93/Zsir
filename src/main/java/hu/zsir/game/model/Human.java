/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game.model;

import hu.zsir.game.operators.CallOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Feco
 */
public class Human extends Player {

    @Override
    synchronized public void chooseCard(Game game) {
        try {
            wait();
            CallOperator.getCallOperator().apply(game);
        } catch (InterruptedException ex) {
            Logger.getLogger(Human.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    synchronized public void setChoosedCard(Card card) {
        this.choosedCard = card;
        notify();
    }

    @Override
    synchronized public void check() {
        checked = true;
        notify();
    }

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

    @Override
    public boolean isHuman() {
        return true;
    }

}
