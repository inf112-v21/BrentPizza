package inf112.skeleton.app.Cards;

import inf112.skeleton.app.GameLogic.IPlayer;

public class NullCard extends Card {

    public void action(IPlayer player) {
        System.out.println("Null card cannot move");
    }

}
