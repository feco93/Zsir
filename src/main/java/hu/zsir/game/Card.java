package hu.zsir.game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Feco
 */

public class Card {

    private final Number number;
    private final Suit suit;

    public Card(Number number, Suit suit) {
        this.number = number;
        this.suit = suit;
    }

    public Number getNumber() {
        return number;
    }

    public Suit getSuit() {
        return suit;
    }
    
    @Override
    public String toString() {
        return suit.toString().toLowerCase()+"_"+number.toString().toLowerCase();
    }   
    
}