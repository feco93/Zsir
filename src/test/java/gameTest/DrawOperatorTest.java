/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameTest;

import hu.zsir.game.DrawOperator;
import hu.zsir.game.Game;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Feco
 */
public class DrawOperatorTest {
    
    private DrawOperator drawoperator;
    private Game game;
    
    public DrawOperatorTest() {
    }
    
    @Before
    public void setUp() {
        drawoperator = DrawOperator.getDrawoperator();
        game = new Game();
    }
    
    @Test
    public void canDraw() {
        assertTrue(drawoperator.isApplicable(game));
    }
    
    @Test
    public void cantDraw() {
        drawoperator.apply(game);
        assertFalse(drawoperator.isApplicable(game));
    }
    
    @After
    public void tearDown() {
    }
    
}
