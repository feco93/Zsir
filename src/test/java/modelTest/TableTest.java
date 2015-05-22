/*
 * Copyright (C) 2015 University of Debrecen, Faculty of Informatics
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

import hu.zsir.game.model.Card;
import hu.zsir.game.model.Rank;
import hu.zsir.game.model.Suit;
import hu.zsir.game.model.Table;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Feco
 */
public class TableTest {
    
    private Table table;
    
    private void initTable(Card card) {
        table.addCard(card);
    }
    
    @Before
    public void setUp() {
        table = new Table();
    }
    
    @Test
    public void topCardTest() {
        Card card = new Card(Rank.ALSO, Suit.TOK);
        initTable(card);
        assertSame(card, table.getTopCard());
    }
    
    @Test
    public void bottomCardTest() {
        Card cardOne = new Card(Rank.ALSO, Suit.TOK);
        Card cardTwo = new Card(Rank.ASZ, Suit.TOK);
        initTable(cardOne);
        initTable(cardTwo);
        assertSame(table.getBottomCard(), cardOne);
    }
    
    @After
    public void tearDown() {
    }
    
}
