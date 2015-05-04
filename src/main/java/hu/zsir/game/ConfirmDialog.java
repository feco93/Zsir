/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 *
 * @author Feco
 */
public class ConfirmDialog extends Dialog<ButtonType> {

    private static ConfirmDialog dialog = new ConfirmDialog();

    private ConfirmDialog() {
        super();
        setHeaderText("Are you sure want to begin new game?");
        setTitle("Confirm dialog");
        ButtonType okButton = ButtonType.YES;
        ButtonType noButton = ButtonType.NO;
        getDialogPane().getButtonTypes().addAll(okButton, noButton);
        getDialogPane().getStylesheets().add(getClass().getResource("/styles/confirmdialog.css").toExternalForm());
    }

    public static ConfirmDialog getDialog() {
        return dialog;
    }

}
