package inf112.skeleton.app.Cards;

import inf112.skeleton.app.Player;

public class TurnRightCard extends Card{


    public void action(Player player) {
        player.rotatePlayer(-90);
    }
}
