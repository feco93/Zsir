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
package operatorsTest;

import hu.zsir.game.model.Card;
import hu.zsir.game.operators.CheckOperator;
import hu.zsir.game.model.Game;
import hu.zsir.game.model.Suit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Feco
 */
public class CheckOperatorTest {

    CheckOperator checkoperator;
    Game game;

    public CheckOperatorTest() {
    }

    private void initTable(Card... cards) {
        for (Card card : cards) {
            game.getTable().addCard(card);
        }
    }

    @Before
    public void setUp() {
        checkoperator = CheckOperator.getCheckoperator();
        game = new Game();
        game.start();
    }

    @Test
    public void canCheck() {
        initTable(new Card(hu.zsir.game.model.Rank.ALSO, Suit.PIROS), new Card(hu.zsir.game.model.Rank.HET, Suit.MAKK));
        assertTrue(checkoperator.isApplicable(game));
    }
    
    @Test
    public void emptyTable() {
        assertFalse(checkoperator.isApplicable(game));
    }
    
    @Test
    public void cantCheck() {
        assertFalse(checkoperator.isApplicable(game));
    }

    @After
    public void tearDown() {
    }

}
