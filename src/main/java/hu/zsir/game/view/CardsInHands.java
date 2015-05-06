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
