/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Feco
 */
public abstract class Player {

    public List<Card> cards;
    private int score;
    protected Card choosedCard;
    protected boolean checked;

    public Player() {
        cards = new ArrayList<>();
        score = 0;
        checked = false;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void addCards(List<Card> cards) {
        for (Card card : cards) {
            this.cards.add(card);
        }
    }

    public Card getChoosedCard() {
        return choosedCard;
    }

    public Card putCard() {
        int i = 0;
        for (Card card : cards) {
            if (card.equals(getChoosedCard())) {
                return cards.remove(i);
            }
            ++i;
        }
        return null;
    }

    public boolean isValidChoose(Card choosedCard, Table table) {
        if (table.getCards().size() > 0) {
            if (table.getCards().size() % 2 == 0) {
                if (table.getBottomCard().getNumber() == choosedCard.getNumber()
                        || choosedCard.getNumber() == hu.zsir.game.model.Number.HET) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public boolean isChecked() {
        return checked;
    }

    public void setCheck(boolean flag) {
        checked = flag;
    }

    public abstract void check();

    public abstract void chooseCard(Game game);

    public abstract void setChoosedCard(Card card);

    public abstract void chooseOperator(Game game);

    public abstract boolean isHuman();

}
