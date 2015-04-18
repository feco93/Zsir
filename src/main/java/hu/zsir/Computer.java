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

    @Override
    public void nextDecision(List<Card> cardOnTable, Deck deck) {
        if (canBeat(cardOnTable)) {
            beat(cardOnTable);
        } else if (cardOnTable.isEmpty()) {
            setSelectedCard(chooseCallingCard());
            putCard(cardOnTable);
        } else if (cardOnTable.size() % 2 == 1) {
            setSelectedCard(chooseCard(cardOnTable));
            putCard(cardOnTable);
        } else if (canPut(cardOnTable.get(0))) {
            setSelectedCard(chooseTrampCard(cardOnTable.get(0)));
            putCard(cardOnTable);
        } else {
            setCanput(false);
        }

    }

    private Card chooseCallingCard() {
        Card toPut = null;
        for (Card card : cards) {
            if (card.getNumber() != Number.HET && card.getNumber() != Number.ASZ
                    && card.getNumber() != Number.TIZ) {
                toPut = card;
                break;
            }
        }
        if (toPut == null) {
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
                toPut = chooseRandomCard();
            } else if (numberOfAsz > numberOfTiz) {
                toPut = asz;
            } else {
                toPut = tiz;
            }
        }
        return toPut;
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

    private Card chooseTrampCard(Card callingCard) {
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
        return toPut;
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

}
