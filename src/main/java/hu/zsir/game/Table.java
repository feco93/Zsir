/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Feco
 */
public class Table {

    private final List<Card> cards;

    public Table() {
        cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void clear() {
        cards.clear();
    }

    public Card getBottomCard() {
        if (cards.size() > 0) {
            return cards.get(0);
        } else {
            return null;
        }
    }

    public Card getTopCard() {
        return cards.get(cards.size() - 1);
    }

}
