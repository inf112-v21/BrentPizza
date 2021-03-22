package inf112.skeleton.app.Cards;

import inf112.skeleton.app.GameLogic.Player;

import java.util.ArrayList;
import java.util.Collections;

public class CardGenerator implements ICardGenerator {

    @Override
    public Card getRandomCard(){
        ArrayList<Card> card = new ArrayList<>();
        card.add(new MoveOneCard());
        card.add(new MoveTwoCard());
        card.add(new MoveThreeCard());
        card.add(new TurnLeftCard());
        card.add(new TurnRightCard());
        card.add(new uTurnCard());
        card.add(new MoveBackCard());
        Collections.shuffle(card);
        return card.get(1);

    }

    @Override
    public ArrayList<Card> getRandomHand(){
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            hand.add(getRandomCard());
        }
        return hand;
    }
}
