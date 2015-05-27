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
package hu.zsir.game;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * A dialog for confirm to begin a new game.
 *
 * @author Feco
 */
public class ConfirmDialog extends Dialog<ButtonType> {

    /**
     * The confirm dialog.
     */
    private static final ConfirmDialog dialog = new ConfirmDialog();

    /**
     * Constructs a new confirm dialog.
     */
    private ConfirmDialog() {
        super();
        setHeaderText("Are you sure want to begin new game?");
        setTitle("Confirm dialog");
        ButtonType okButton = ButtonType.YES;
        ButtonType noButton = ButtonType.NO;
        getDialogPane().getButtonTypes().addAll(okButton, noButton);
        getDialogPane().getStylesheets().add(getClass().getResource("/styles/confirmdialog.css").toExternalForm());
    }

    /**
     * Gets the confirm dialog.
     *
     * @return the confirm dialog
     */
    public static ConfirmDialog getDialog() {
        return dialog;
    }

}
