/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game.view;

import hu.zsir.game.model.Card;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Feco
 */
public class CardView extends ImageView {
    
    private final Image cardimage;

    public CardView(Card card) {
        if (card.isFaceup()) {
            cardimage = new Image(getClass().getResourceAsStream("/images/" + card.toString() + ".jpg"));
        }
        else {
            cardimage = new Image(getClass().getResourceAsStream("/images/hatlap.jpg"));
        }
        setImage(cardimage);
        setFitWidth(112.0);
        setFitHeight(186.0);
    }

}
