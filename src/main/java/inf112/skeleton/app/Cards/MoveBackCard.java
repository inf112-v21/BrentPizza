package inf112.skeleton.app.Cards;
import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IPlayer;

public class MoveBackCard extends Card{

    public void action(IPlayer player, IBoardLogic boardLogic) {
        player.rotatePlayer(180);
        if(boardLogic.checkMove(player)){
        player.moveForward();
        }
        player.rotatePlayer(180);
        boardLogic.checkMovement(player);

    }
}
