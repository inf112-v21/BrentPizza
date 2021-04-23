package inf112.skeleton.app.Cards;

import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IPlayer;

public class NullCard extends Card {

    public NullCard(){
        this.priorityNumber = 0;
    }
    public void action(IPlayer player, IBoardLogic boardLogic) {
        System.out.println("Null card cannot move");
    }

}
