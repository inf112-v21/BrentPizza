package inf112.skeleton.app.Cards;

import inf112.skeleton.app.GameLogic.Player;

public class MoveOneCard extends Card{


    public void action(Player player){

        player.moveForward();

    }
}
