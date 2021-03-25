package inf112.skeleton.app.Cards;

import inf112.skeleton.app.GameLogic.IPlayer;

public class MoveOneCard extends Card{


    public void action(IPlayer player){
        player.moveForward();

    }



}
