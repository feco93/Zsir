/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game.operators;

import hu.zsir.game.model.Card;
import hu.zsir.game.model.Game;
import hu.zsir.game.model.Number;
import hu.zsir.game.model.Player;

/**
 *
 * @author Feco
 */
public class CallOperator implements Operator {
    
    private static CallOperator calloperator = new CallOperator();
    
    private CallOperator() {
    }
    
    @Override
    public boolean isApplicable(Game game) {
        return (!game.getCurrentplayer().cards.isEmpty()) && !game.getNextplayer().isChecked()
                && !BeatOperator.getBeatoperator().isApplicable(game)
                && (game.getTable().getCards().isEmpty() || game.getTable().getCards().size() % 2 == 1
                || canCall(game.getCurrentplayer(), game.getTable().getCards().get(0)));
    }
    
    @Override
    public void apply(Game game) {
        Card card = game.getCurrentplayer().putCard();
        card.turnUp();
        game.getTable().addCard(card);
        game.getCurrentplayer().setChoosedCard(null);
        game.swapPlayers();
    }
    
    private boolean canCall(Player currentplayer, Card firstcard) {
        for (Card card : currentplayer.cards) {
            if (card.getNumber() == firstcard.getNumber() || card.getNumber() == Number.HET) {
                return true;
            }
        }
        return false;
    }
    
    public static CallOperator getCallOperator() {
        return calloperator;
    }    
    
}
