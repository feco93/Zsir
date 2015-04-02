/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import hu.zsir.Card;
import hu.zsir.Suit;
import hu.zsir.Number;
import java.util.ArrayList;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Feco
 */
@RunWith(Parameterized.class)
public class CardTest {
    
    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        List<Object[]> toRet = new ArrayList<>();
        for(Suit suit : Suit.values()) {
            for(Number number : Number.values()) {
                Object[] pair = new Object[] {number, suit};
                toRet.add(pair);
            }
        }
        return toRet;
    }
    
    Card card;
    
    public CardTest(Number number, Suit suit) {
        card = new Card(number,suit);
    }
    
    @Test
    public void testPirosAsz() {
        if (card.getNumber() == Number.ASZ && card.getSuit() == Suit.PIROS) {
            assertEquals("piros_asz",card.toString());
        }
    } 
    
    @Test
    public void testZoldAlso() {
        if (card.getNumber() == Number.ALSO && card.getSuit() == Suit.ZOLD) {
            assertEquals("zold_also",card.toString());
        }
    }
    
}
