package inf112.skeleton.app.Cards;

import java.util.ArrayList;
import java.util.Collections;

public class CardGenerator {

    public Card getRandomCard(){
        ArrayList<Card> card = new ArrayList<>();
        card.add(new MoveOneCard());
        card.add(new MoveTwoCard());
        card.add(new MoveThreeCard());
        card.add(new TurnLeftCard());
        card.add(new TurnRightCard());
        Collections.shuffle(card);
        return card.get(1);

    }

    public ArrayList<Card> getRandomHand(){
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            hand.add(getRandomCard());
        }
        return hand;
    }
}
