/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game.model;

import hu.zsir.game.operators.CallOperator;
import hu.zsir.game.operators.CheckOperator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Feco
 */
public class Computer extends Player {

    private Card chooseCallingCard() {
        for (Card card : cards) {
            if (card.getNumber() != hu.zsir.game.model.Number.HET && card.getNumber() != hu.zsir.game.model.Number.ASZ
                    && card.getNumber() != hu.zsir.game.model.Number.TIZ) {
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
            if (card.getNumber() == hu.zsir.game.model.Number.ASZ || card.getNumber() == hu.zsir.game.model.Number.TIZ) {
                toPut = chooseTrampCard(cardOnTable.get(0));
                break;
            }
        }
        if (toPut == null) {
            if (bottomCard.getNumber() != hu.zsir.game.model.Number.TIZ && bottomCard.getNumber() != hu.zsir.game.model.Number.ASZ
                    && bottomCard.getNumber() != hu.zsir.game.model.Number.HET) {
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
                if (card.getNumber() == hu.zsir.game.model.Number.HET) {
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
            if (card.getNumber() != hu.zsir.game.model.Number.ASZ && card.getNumber() != hu.zsir.game.model.Number.TIZ
                    && card.getNumber() != hu.zsir.game.model.Number.HET) {
                toPut = card;
            }
        }
        return toPut;
    }

    @Override
    public void chooseCard(Game game) {
        if (game.getTable().getCards().isEmpty()) {
            choosedCard = chooseCallingCard();
        } else if (game.getTable().getCards().size() % 2 == 1) {
            choosedCard = chooseCard(game.getTable().getCards());
        } else {
            choosedCard = chooseTrampCard(game.getTable().getBottomCard());
        }
        CallOperator.getCallOperator().apply(game);
    }

    @Override
    public void setChoosedCard(Card card) {
        this.choosedCard = card;
    }

    @Override
    public void check() {
        checked = true;
    }

    @Override
    public void chooseOperator(Game game) {
        CheckOperator.getCheckoperator().apply(game);
    }

    @Override
    public boolean isHuman() {
        return false;
    }
    
    @Override
    public void addCards(List<Card> cards) {
        for (Card card : cards) {
            card.turnDown();
            this.cards.add(card);
        }
    }

}
