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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class for the card view.
 *
 * @author Feco
 */
public class CardView extends ImageView {

    /**
     * The image of the card.
     */
    private final Image cardimage;

    /**
     * Constructs the view from the specified card.
     *
     * @param card the specified card
     */
    public CardView(Card card) {
        if (card.isFaceup()) {
            cardimage = new Image(getClass().getResourceAsStream("/images/" + card.toString() + ".jpg"));
        } else {
            cardimage = new Image(getClass().getResourceAsStream("/images/hatlap.jpg"));
        }
        setImage(cardimage);
        setFitWidth(112.0);
        setFitHeight(186.0);
    }

}
