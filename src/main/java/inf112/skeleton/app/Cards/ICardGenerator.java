package inf112.skeleton.app.Cards;

import java.util.ArrayList;

public interface ICardGenerator {

    /**
     * Gives a random card from all the avalible card classes
     * @return random Card
     */
    Card getRandomCard();

    /**
     * Gives a list of 9 random cards.
     * @return Arraylist of 9 random Cards
     */
    ArrayList<Card> getRandomHand();
}
