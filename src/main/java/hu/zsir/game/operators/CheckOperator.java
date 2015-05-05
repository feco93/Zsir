/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game.operators;

import hu.zsir.game.model.Game;

/**
 *
 * @author Feco
 */
public class CheckOperator implements Operator {

    private static CheckOperator checkoperator = new CheckOperator();

    private CheckOperator() {
    }

    @Override
    public boolean isApplicable(Game game) {
        return !game.getTable().getCards().isEmpty() && !BeatOperator.getBeatoperator().isApplicable(game) && game.getTable().getCards().size() % 2 == 0;
    }

    @Override
    public void apply(Game game) {
        game.getCurrentplayer().check();
        game.swapPlayers();
    }

    public static CheckOperator getCheckoperator() {
        return checkoperator;
    }

}
