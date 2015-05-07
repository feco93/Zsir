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

import hu.zsir.game.operators.DrawOperator;
import hu.zsir.game.model.Game;
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
