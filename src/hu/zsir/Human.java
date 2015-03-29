package hu.zsir;

import java.util.Iterator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Feco
 */
public class Human extends Player {

    Card selectedCard;

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public Human(List<Card> cards) {
        super(cards);
    }

    @Override
    public Card putCard() {
        Iterator<Card> cardIterator = cards.iterator();
        while (cardIterator.hasNext()) {
            if (cardIterator.next().equals(getSelectedCard())) {
                cardIterator.remove();
            }
        }
        Card toPut = getSelectedCard();
        setSelectedCard(null);
        return toPut;
    }

}
