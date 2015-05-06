/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game.view;

import hu.zsir.game.model.Card;
import hu.zsir.game.model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

/**
 *
 * @author Feco
 */
public class CardsOnTable {

    private final ObservableList<ImageView> cards;

    public CardsOnTable(Game game) {
        cards = FXCollections.observableArrayList();
        int index = 0;
        for (Card card : game.getTable().getCards()) {
            ImageView cardview = new CardView(card);
            cardview.setLayoutX(250.0 + 30 * index);
            cardview.setLayoutY(230.0);
            cardview.setOnMousePressed(event -> {
                cardview.toFront();
            });
            cardview.setOnMouseReleased(event -> {
                cardview.toBack();
            });
            ++index;
            cards.add(cardview);
        }
    }

    public ObservableList<ImageView> getCards() {
        return cards;
    }

}
