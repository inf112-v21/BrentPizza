package inf112.skeleton.app.Cards;

import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IPlayer;

public class MoveTwoCard extends Card{

    @Override
    public void action(IPlayer player, IBoardLogic boardLogic) {
        for (int i = 0; i < 2; i++) {
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
