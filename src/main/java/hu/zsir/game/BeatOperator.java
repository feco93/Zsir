/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game;

/**
 *
 * @author Feco
 */
public class BeatOperator implements Operator {
    
    private static BeatOperator beatoperator = new BeatOperator();
    
    private BeatOperator() {
    }
    
    @Override
    public boolean isApplicable(Game game) {
        return game.getNextplayer().isChecked() || (game.getTable().getCards().size() > 0 && game.getTable().getCards().size() % 2 == 0 && (
                game.getTable().getBottomCard().getNumber() != game.getTable().getTopCard().getNumber() && 
                game.getTable().getTopCard().getNumber() != Number.HET ));
    }
    
    @Override
    public void apply(Game game) {
        game.getTable().clear();
        game.getNextplayer().setCheck(false);
        if (DrawOperator.getDrawoperator().isApplicable(game)) {
            DrawOperator.getDrawoperator().apply(game);
        }
    }
    
    public static BeatOperator getBeatoperator() {
        return beatoperator;
    }
    
}
