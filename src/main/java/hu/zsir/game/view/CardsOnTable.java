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
