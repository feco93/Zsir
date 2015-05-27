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
package hu.zsir.scoretable;

import static hu.zsir.game.Main.mainWindow;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.LoggerFactory;

/**
 * A stage for display the score table.
 *
 * @author Feco
 */
public class ScoreTableDialog extends Stage {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ScoreTableDialog.class);

    /**
     * Constructs the score table dialog.
     */
    public ScoreTableDialog() {
        super();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/scoreWindow.fxml"));
            Scene scene = new Scene(root);
            setScene(scene);
            setResizable(false);
            initOwner(mainWindow);
            initStyle(StageStyle.UTILITY);
            initModality(Modality.WINDOW_MODAL);
            setTitle("Score table");
            getIcons().add(new Image(getClass().getResource("/images/Zsir.ico").toString()));
        } catch (IOException ex) {
            logger.error("An error occured during the loading the score table window.", ex);
        }
    }

}
