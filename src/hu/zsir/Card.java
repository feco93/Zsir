package hu.zsir;

import javafx.scene.image.Image;

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

    Number number;
    Suit suit;
    Image cardImage;

    public Card(Number number, Suit suit) {
        this.number = number;
        this.suit = suit;
        setCardImage();
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }
    
    @Override
    public String toString() {
        return suit.toString().toLowerCase()+"_"+number.toString().toLowerCase();
    }

    public Image getCardImage() {
        return cardImage;
    }

    private void setCardImage() {
        cardImage = new Image("resources/" + this.toString() + ".jpg");
    }
    
    
}