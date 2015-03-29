/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Feco
 */
public class Computer extends Player {

    public Computer(List<Card> cards) {
        super(cards);
    }

    public Card putCard(Card callingCard) {
        Card toPut = null;
        for (Card card : cards) {
            if (card.number == callingCard.number) {
                toPut = card;
                break;
            }
        }
        if (toPut == null) {
            for (Card card : cards) {
                if (card.number == Number.HET) {
                    toPut = card;
                    break;
                }
            }
        }

        Iterator<Card> cardIterator = cards.iterator();

        while (cardIterator.hasNext()) {
            if (cardIterator.next().equals(toPut)) {
                cardIterator.remove();
            }
        }

        return toPut;
    }

    @Override
    public Card putCard() {

        Card toPut = null;

        for (Card card : cards) {
            if (card.getNumber() == Number.HET) {
                toPut = card;
                break;
            }
        }

        if (toPut == null) {
            Random rand = new Random();
            int cardIndex;
            if (cards.size() > 1) {
                cardIndex = rand.nextInt(cards.size() - 1);
            } else {
                cardIndex = 0;
            }
            toPut = cards.get(cardIndex);
        }

        Iterator<Card> cardIterator = cards.iterator();

        while (cardIterator.hasNext()) {
            if (cardIterator.next().equals(toPut)) {
                cardIterator.remove();
            }
        }
        return toPut;
    }

}
