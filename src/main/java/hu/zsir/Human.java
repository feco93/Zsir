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

    public Human(List<Card> cards) {
        super(cards);
    }

    

    @Override
    public void nextDecision(List<Card> cardOnTable, Deck deck) {
        if (canBeat(cardOnTable)) {
            beat(cardOnTable);
        } else if ((cardOnTable.isEmpty() || cardOnTable.size() % 2 == 1)) {
            if (getSelectedCard() != null) {
                putCard(cardOnTable);
            }
        } else if (canPut(cardOnTable.get(0))) {
            if (getSelectedCard() != null
                    && (getSelectedCard().number == cardOnTable.get(0).number || getSelectedCard().number == Number.HET)) {
                putCard(cardOnTable);
            }

        } else {
            setCanput(false);
        }
    }
}
