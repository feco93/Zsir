/* 
 * Copyright (C) 2015 Feco
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package hu.zsir.game.model;

import hu.zsir.game.operators.CallOperator;
import java.util.List;
import java.util.Random;

/**
 * Model class for a computer player.
 *
 * @author Feco
 */
public class Computer extends Player {

    /**
     * Chooses the calling card from the cards of this player.
     *
     * @return the calling card
     */
    private Card chooseCallingCard() {
        for (Card card : cards) {
            if (card.getRank() != hu.zsir.game.model.Rank.HET && card.getRank() != hu.zsir.game.model.Rank.ASZ
                    && card.getRank() != hu.zsir.game.model.Rank.TIZ) {
                return card;
            }
        }
        int numberOfTiz = 0;
        int numberOfAsz = 0;
        Card asz = null;
        Card tiz = null;
        for (Card card : cards) {
            switch (card.getRank()) {
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

    /**
     * Chooses a random card from the cards of this player.
     *
     * @return the choosed card
     */
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

    /**
     * Chooses a card from the cards of this player.
     *
     * @param cardOnTable cards on the table
     * @return the choosed card
     */
    private Card chooseCard(List<Card> cardOnTable) {
        Card toPut = null;
        Card bottomCard = cardOnTable.get(0);

        for (Card card : cardOnTable) {
            if (card.getRank() == hu.zsir.game.model.Rank.ASZ || card.getRank() == hu.zsir.game.model.Rank.TIZ) {
                toPut = chooseTrampCard(cardOnTable.get(0));
                break;
            }
        }
        if (toPut == null) {
            if (bottomCard.getRank() != hu.zsir.game.model.Rank.TIZ && bottomCard.getRank() != hu.zsir.game.model.Rank.ASZ
                    && bottomCard.getRank() != hu.zsir.game.model.Rank.HET) {
                for (Card card : cards) {
                    if (bottomCard.getRank() == card.getRank()) {
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

    /**
     * Chooses a tramp card from the cards of this player.
     *
     * @param callingCard the calling card
     * @return the choosed card
     */
    private Card chooseTrampCard(Card callingCard) {
        Card toPut = null;
        for (Card card : cards) {
            if (card.getRank() == callingCard.getRank()) {
                toPut = card;
                break;
            }
        }
        if (toPut == null) {
            for (Card card : cards) {
                if (card.getRank() == hu.zsir.game.model.Rank.HET) {
                    toPut = card;
                    break;
                }
            }
        }
        return toPut;
    }

    /**
     * Chooses a non tramp card from the cards of this player.
     *
     * @return the choosed card
     */
    private Card chooseNonTrampCard() {
        Card toPut = null;
        for (Card card : cards) {
            if (card.getRank() != hu.zsir.game.model.Rank.ASZ && card.getRank() != hu.zsir.game.model.Rank.TIZ
                    && card.getRank() != hu.zsir.game.model.Rank.HET) {
                toPut = card;
            }
        }
        return toPut;
    }

    /**
     * Chooses a card from the cards of this player.
     *
     * @param game the state of the game
     */
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

    /**
     * Sets the choosed card.
     *
     * @param card the choosed card
     */
    @Override
    public void setChoosedCard(Card card) {
        this.choosedCard = card;
    }

    /**
     * Sets the value of the property checked to true.
     */
    @Override
    public void check() {
        checked = true;
    }

    /**
     * Chooses an operator.
     *
     * @param game the state of the game
     */
    @Override
    public void chooseOperator(Game game) {
        chooseCard(game);
    }

    /**
     * Indicates whether this player is human.
     *
     * @return returns false
     */
    @Override
    public boolean isHuman() {
        return false;
    }

    /**
     * Adds cards to the cards of this player.
     *
     * @param cards the list of cards to be added to the cards of this player
     */
    @Override
    public void addCards(List<Card> cards) {
        for (Card card : cards) {
            card.turnDown();
            this.cards.add(card);
        }
    }

}
