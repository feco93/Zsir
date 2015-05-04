package hu.zsir.game;

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

    static {
        backImage = new Image("/images/hatlap.jpg");
    }

    private final Number number;
    private final Suit suit;
    private static final Image backImage;
    private final Image frontImage;

    public Card(Number number, Suit suit) {
        this.number = number;
        this.suit = suit;
        frontImage = new Image("/images/" + suit.toString().toLowerCase() + "_" + number.toString().toLowerCase() + ".jpg");
    }

    public Number getNumber() {
        return number;
    }

    public Suit getSuit() {
        return suit;
    }

    public static Image getBackImage() {
        return backImage;
    }

    public Image getFrontImage() {
        return frontImage;
    }

    @Override
    public String toString() {
        return suit.toString().toLowerCase() + "_" + number.toString().toLowerCase();
    }

}
