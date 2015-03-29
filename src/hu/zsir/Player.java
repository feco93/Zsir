/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir;

import java.util.List;

/**
 *
 * @author Feco
 */
public abstract class Player {

    List<Card> cards;
    boolean passed;
    int score;

    public abstract Card putCard();

    public Player(List<Card> cards) {
        this.cards = cards;
        passed = false;
        score = 0;
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
    }

    public boolean canPut(Card callingCard) {
        for (Card card : cards) {
            if (card.number == callingCard.number || card.number == Number.HET) {
                return true;
            }
        }
        return false;
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
}
