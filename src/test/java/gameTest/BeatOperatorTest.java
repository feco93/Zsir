/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameTest;

import hu.zsir.game.operators.BeatOperator;
import hu.zsir.game.model.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Feco
 */
public class BeatOperatorTest {
    
    private BeatOperator beatoperator;
    private Game game;
    
    public BeatOperatorTest() {
    }
    
    @Before
    public void setUp() {
        beatoperator = BeatOperator.getBeatoperator();
        game = new Game();
    }
    
    @Test
    public void canBeat() {
        game.getNextplayer().check();
        assertTrue(beatoperator.isApplicable(game));
    }
    
    @Test
    public void cantBeat() {
        assertFalse(beatoperator.isApplicable(game));
    }
    
    @Test
    public void applyBeat() {
        beatoperator.apply(game);
        assertTrue(game.getTable().getCards().isEmpty());
    }
    
    @After
    public void tearDown() {
    }
    
}
