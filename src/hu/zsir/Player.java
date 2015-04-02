/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Feco
 */
public abstract class Player {

    List<Card> cards;
    boolean passed;
    boolean beated;
    boolean canput;
    int score;
    Card selectedCard;

    public abstract void nextDecision(List<Card> cardOnTable, Deck deck);

    public Player(List<Card> cards) {
        this.cards = cards;
        passed = false;
        canput = true;
        score = 0;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public void putCard(List<Card> cardOnTable) {
        Iterator<Card> cardIterator = cards.iterator();
        while (cardIterator.hasNext()) {
            if (cardIterator.next().equals(getSelectedCard())) {
                cardIterator.remove();
            }
        }
        Card toPut = getSelectedCard();
        cardOnTable.add(toPut);
        setSelectedCard(null);
        setPassed(true);
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public void beat(List<Card> playedCard) {
        for (Card card : playedCard) {
            if (card.number == Number.ASZ || card.number == Number.TIZ) {
                score++;
            }
        }
        setBeated(true);
    }

    public boolean canPut(Card callingCard) {
        for (Card card : cards) {
            if (card.number == callingCard.number || card.number == Number.HET) {
                return true;
            }
        }
        return false;
    }

    public boolean canBeat(List<Card> cardOnTable) {
        return cardOnTable.size() > 0 && cardOnTable.size() % 2 == 0
                && cardOnTable.get(cardOnTable.size() - 1).number != cardOnTable.get(0).number
                && cardOnTable.get(cardOnTable.size() - 1).number != Number.HET;
    }

    public void drawCard(Card card) {
        cards.add(card);
    }

    public int getCardCounter() {
        return cards.size();
    }

    public int getScore() {
        return score;
    }

    public boolean isBeated() {
        return beated;
    }

    public void setBeated(boolean beated) {
        this.beated = beated;
    }

    public boolean isCanput() {
        return canput;
    }

    public void setCanput(boolean canput) {
        this.canput = canput;
    }

}
