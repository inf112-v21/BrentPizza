package inf112.skeleton.app.Cards;

import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IPlayer;

public class MoveThreeCard extends Card {

    public void action(IPlayer player, IBoardLogic boardLogic) {
        for (int i = 0; i < 3; i++) {
            try{
                Thread.sleep(500);
            }catch (Exception e){
                System.out.println(e);
            }
            player.moveForward();
            boardLogic.checkMovement(player);
        }
    }
}
