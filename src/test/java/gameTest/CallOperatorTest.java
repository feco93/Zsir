/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameTest;

import hu.zsir.game.operators.CallOperator;
import hu.zsir.game.model.Card;
import hu.zsir.game.model.Game;
import hu.zsir.game.model.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Feco
 */
public class CallOperatorTest {

    private Game game;
    private CallOperator calloperator;

    public CallOperatorTest() {
    }

    private void initPlayer() {
        Card[] cards = new Card[]{new Card(hu.zsir.game.model.Number.ALSO, Suit.TOK),
            new Card(hu.zsir.game.model.Number.ALSO, Suit.PIROS),
            new Card(hu.zsir.game.model.Number.FELSO, Suit.TOK),
            new Card(hu.zsir.game.model.Number.TIZ, Suit.ZOLD)};
        List<Card> inHand = new ArrayList<>(Arrays.asList(cards));
        game.getCurrentplayer().addCards(inHand);

    }

    private void initTable(Card... cards) {
        for (Card card : cards) {
            game.getTable().addCard(card);
        }
    }

    @Before
    public void setUp() {
        game = new Game();
        calloperator = CallOperator.getCallOperator();
        initPlayer();
    }

    @Test
    public void testEmptyTable() {
        assertTrue(calloperator.isApplicable(game));
    }

    @Test
    public void firstCall() {
        assertTrue(calloperator.isApplicable(game));
    }

    @Test
    public void canHold() {
        initTable(new Card(hu.zsir.game.model.Number.ALSO, Suit.ZOLD), new Card(hu.zsir.game.model.Number.HET, Suit.MAKK));
        assertTrue(calloperator.isApplicable(game));
    }

    @Test
    public void cantHold() {
        initTable(new Card(hu.zsir.game.model.Number.KIRALY, Suit.TOK), new Card(hu.zsir.game.model.Number.KIRALY, Suit.MAKK));
        assertFalse(calloperator.isApplicable(game));
    }
    
    @Test
    public void applyCall() {
        game.getCurrentplayer().setChoosedCard(game.getCurrentplayer().cards.get(0));
        calloperator.apply(game);
        assertFalse(game.getTable().getCards().isEmpty());
    }

    @After
    public void tearDown() {
    }

}
