package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

public interface IHudLogic {

    /**
     * Updates the list of
     */
    void updateProgramImageList();

    void updateHandButtonList();

    void updateHand();

    Button getReadyButton();

    ArrayList<Button> getHandButtonList();

    ArrayList<Image> getProgramImageList();

    void addToProgramButtonList(Button button);

    void clearProgramCards();

    void updateButtonToHandMap();

    void addClickListenersHand();
}
