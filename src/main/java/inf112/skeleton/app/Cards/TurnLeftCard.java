package inf112.skeleton.app.Cards;

import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IPlayer;

public class TurnLeftCard extends Card{

    public void action(IPlayer player, IBoardLogic boardLogic) {
        player.rotatePlayer(90);
    }
}
