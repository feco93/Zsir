/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Feco
 */
public class Player {

    public List<Card> cards;
    private int score;
    private final boolean computer;
    private Card choosedCard;
    private boolean checked;

    public Player(boolean isComputer) {
        cards = new ArrayList<>();
        score = 0;
        computer = isComputer;
        checked = false;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public boolean isComputer() {
        return computer;
    }

    public void addCards(List<Card> cards) {
        for (Card card : cards) {
            this.cards.add(card);
        }
    }

    public Card getChoosedCard() {
        return choosedCard;
    }

    synchronized public void setChoosedCard(Card card) {
        this.choosedCard = card;
        notify();
    }

    synchronized public void chooseCard(Game game) {
        if (!isComputer()) {
            try {
                wait();
                CallOperator.getCallOperator().apply(game);
            } catch (InterruptedException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (game.getTable().getCards().isEmpty()) {
                choosedCard = chooseCallingCard();
            } else if (game.getTable().getCards().size() % 2 == 1) {
                choosedCard = chooseCard(game.getTable().getCards());
            } else {
                choosedCard = chooseTrampCard(game.getTable().getBottomCard());
            }
            CallOperator.getCallOperator().apply(game);
        }

    }

    public boolean isValidChoose(Card choosedCard, Table table) {
        if (table.getCards().size() > 0) {
            if (table.getCards().size() % 2 == 0) {
                if (table.getBottomCard().getNumber() == choosedCard.getNumber()
                        || choosedCard.getNumber() == Number.HET) {
                    return true;
                } else {
                    return false;
                }
            }
            else {
                return true;
            }
        } else {
            return true;
        }
    }

    synchronized public void check() {
        checked = true;
        notify();
    }

    public boolean isChecked() {
        return checked;
    }

    public void setCheck(boolean flag) {
        checked = flag;
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

    private Card chooseCallingCard() {
        for (Card card : cards) {
            if (card.getNumber() != Number.HET && card.getNumber() != Number.ASZ
                    && card.getNumber() != Number.TIZ) {
                return card;
            }
        }
        int numberOfTiz = 0;
        int numberOfAsz = 0;
        Card asz = null;
        Card tiz = null;
        for (Card card : cards) {
            switch (card.getNumber()) {
                case ASZ:
                    numberOfAsz++;
                    asz = card;
                    break;
                case TIZ:
                    numberOfTiz++;
                    tiz = card;
                    break;
            }
        }
        if (numberOfAsz == 0 && numberOfTiz == 0) {
            return chooseRandomCard();
        } else if (numberOfAsz > numberOfTiz) {
            return asz;
        } else {
            return tiz;
        }
    }

    private Card chooseRandomCard() {
        Random rand = new Random();
        int cardIndex;
        if (cards.size() > 1) {
            cardIndex = rand.nextInt(cards.size() - 1);
        } else {
            cardIndex = 0;
        }
        return cards.get(cardIndex);
    }

    private Card chooseCard(List<Card> cardOnTable) {
        Card toPut = null;
        Card bottomCard = cardOnTable.get(0);

        for (Card card : cardOnTable) {
            if (card.getNumber() == Number.ASZ || card.getNumber() == Number.TIZ) {
                toPut = chooseTrampCard(cardOnTable.get(0));
                break;
            }
        }
        if (toPut == null) {
            if (bottomCard.getNumber() != Number.TIZ && bottomCard.getNumber() != Number.ASZ
                    && bottomCard.getNumber() != Number.HET) {
                for (Card card : cards) {
                    if (bottomCard.getNumber() == card.getNumber()) {
                        toPut = card;
                        break;
                    }
                }
            }
        }

        if (toPut == null) {
            toPut = chooseNonTrampCard();
        }
        if (toPut == null) {
            toPut = chooseRandomCard();
        }
        return toPut;
    }

    private Card chooseTrampCard(Card callingCard) {
        Card toPut = null;
        for (Card card : cards) {
            if (card.getNumber() == callingCard.getNumber()) {
                toPut = card;
                break;
            }
        }
        if (toPut == null) {
            for (Card card : cards) {
                if (card.getNumber() == Number.HET) {
                    toPut = card;
                    break;
                }
            }
        }
        return toPut;
    }

    private Card chooseNonTrampCard() {
        Card toPut = null;
        for (Card card : cards) {
            if (card.getNumber() != Number.ASZ && card.getNumber() != Number.TIZ
                    && card.getNumber() != Number.HET) {
                toPut = card;
            }
        }
        return toPut;
    }

    synchronized void chooseOperator(Game game) {
        if (game.getCurrentplayer().isComputer()) {
            CheckOperator.getCheckoperator().apply(game);
        } else {
            try {
                wait();
                if (choosedCard != null) {
                    CallOperator.getCallOperator().apply(game);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
