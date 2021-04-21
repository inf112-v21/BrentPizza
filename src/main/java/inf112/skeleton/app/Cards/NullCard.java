package inf112.skeleton.app.Cards;

import inf112.skeleton.app.GameLogic.IPlayer;

import java.util.Random;

public class NullCard extends Card {

    public NullCard(){
        this.priorityNumber = 0;
    }
    public void action(IPlayer player) {
        System.out.println("Null card cannot move");
    }

}
