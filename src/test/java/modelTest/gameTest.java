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
package modelTest;

import hu.zsir.game.model.Game;
import hu.zsir.game.model.Player;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Feco
 */
public class gameTest {

    Game game;

    public gameTest() {
    }

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void newGameTest() {
        assertEquals(32, game.getDeck().getSize());
        assertEquals(0, game.getTable().getCards().size());
    }

    @Test
    public void startTest() {
        game.start();
        assertEquals(4, game.getCurrentplayer().cards.size());
        assertEquals(4, game.getCurrentplayer().cards.size());
    }

    @Test
    public void gameOverTest() {
        for (int i = 0; i < 8; ++i) {
            game.getDeck().getCards(4);
        }
        assertTrue(game.isGoal());
    }
    
    @Test
    public void notGameOverTest() {
        assertFalse(game.isGoal());
    }
    
    @Test
    public void swapPlayerTest() {
        Player player = game.getCurrentplayer();
        game.swapPlayers();
        assertEquals(player, game.getNextplayer());
    }

    @After
    public void tearDown() {
    }

}
