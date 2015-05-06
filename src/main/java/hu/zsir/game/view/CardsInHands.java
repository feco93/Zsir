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
public class CardsInHands {

    private final ObservableList<ImageView> cards;

    public CardsInHands(Game game) {
        cards = FXCollections.observableArrayList();
        int index = 0;
        for (Card card : game.getPlayerB().cards) {
            ImageView cardview = new CardView(card);
            cardview.setLayoutX(160.0 + 120 * index);
            cardview.setLayoutY(10.0);
            cards.add(cardview);
            ++index;
        }
        index = 0;
        for (Card card : game.getPlayerA().cards) {
            ImageView cardview = new CardView(card);
            cardview.setLayoutX(160.0 + 120 * index);
            cardview.setLayoutY(440.0);
            cardview.setOnMouseClicked(event -> {
                if (game.getCurrentplayer().isHuman()) {
                    if (game.getCurrentplayer().isValidChoose(card, game.getTable())) {
                        game.getCurrentplayer().setChoosedCard(card);
                    }
                }
            });
            cards.add(cardview);
            ++index;
        }
    }

    public ObservableList<ImageView> getCards() {
        return cards;
    }

}
