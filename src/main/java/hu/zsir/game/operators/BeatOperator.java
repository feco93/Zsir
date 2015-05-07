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

import hu.zsir.game.model.Card;
import hu.zsir.game.model.Game;
import hu.zsir.game.model.Rank;
import java.util.List;

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
        return game.getNextplayer().isChecked() || (game.getTable().getCards().size() > 0 && game.getTable().getCards().size() % 2 == 0 && (game.getTable().getBottomCard().getRank() != game.getTable().getTopCard().getRank()
                && game.getTable().getTopCard().getRank() != Rank.HET));
    }

    @Override
    public void apply(Game game) {
        game.getCurrentplayer().addScore(computeScore(game.getTable().getCards()));
        game.getNextplayer().setCheck(false);
        game.getTable().clear();
        if (DrawOperator.getDrawoperator().isApplicable(game)) {
            DrawOperator.getDrawoperator().apply(game);
        }
    }

    public static BeatOperator getBeatoperator() {
        return beatoperator;
    }

    private int computeScore(List<Card> cards) {
        int score = 0;
        for (Card card : cards) {
            if (card.getRank() == Rank.ASZ || card.getRank() == Rank.TIZ) {
                score++;
            }
        }
        return score;
    }

}
