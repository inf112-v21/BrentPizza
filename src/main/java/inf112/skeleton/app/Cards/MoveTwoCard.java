package inf112.skeleton.app.Cards;

import inf112.skeleton.app.GameLogic.Player;

public class MoveTwoCard extends Card{


    @Override
    public void action(Player player) {
        for (int i = 0; i < 2; i++) {
            player.moveForward();
        }
    }

}
