/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameTest;

import hu.zsir.game.Card;
import hu.zsir.game.CheckOperator;
import hu.zsir.game.Game;
import hu.zsir.game.Suit;
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
    }

    @Test
    public void canCheck() {
        initTable(new Card(hu.zsir.game.Number.ALSO, Suit.PIROS), new Card(hu.zsir.game.Number.HET, Suit.MAKK));
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
