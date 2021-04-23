package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

public interface IHudLogic {

    /**
     * Updates the list of Images that are used for the Program table based on the current programCards
     */
    void updateProgramImageList();

    /**
     * Updates the Image buttons based on the current cards the player has in their hand
     */
    void updateHandButtonList();

    /**
     * Updates the cards in the players hand, for now it only fills the players hand with random cards
     */
    void updateHand();

    /**
     * Gives the ready button used when all the program cards are added
     * @return A button with a clickListener()
     */
    Button getReadyButton();

    /**
     * Gives a list of all the buttons with their respective textures based on the hand Card list
     * @return Arraylist of Buttons
     */
    ArrayList<Button> getHandButtonList();

    /**
     * Gives the current Images used to display what cards are in the programCards list
     * @return Arraylist of Images
     */
    ArrayList<Image> getProgramImageList();

    /**
     * After a button from HandButtonList is pressed this transfers the correct card to programCards
     * list and updates programImageList to the correct card
     * @param button button that was pressed
     */
    void addToProgramButtonList(Button button);

    /**
     * Adds all nullCards to the programCards list
     */
    void clearProgramCards();

    /**
     * Updates the HashMap that maps the list of cards in the players hand to the handButton list
     */
    void updateButtonToHandMap();

    /**
     * Adds the listeners to the buttons in the handButton list
     */
    void addClickListenersHand();

    /**
     * Retrieves the priority numbers of the cards in hand
     * @return list of hand cards priority number
     */

    ArrayList<Integer> getHandPriority();

    /**
     * Retrieves heal button
     * @return
     */
    Button getHealButton();

}
