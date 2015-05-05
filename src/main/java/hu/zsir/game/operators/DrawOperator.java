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
