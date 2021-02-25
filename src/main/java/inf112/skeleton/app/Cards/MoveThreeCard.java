package inf112.skeleton.app.Cards;

import inf112.skeleton.app.Player;

public class MoveThreeCard extends Card {


    public void action(Player player) {
        for (int i = 0; i < 3; i++) {
            player.moveForward();
        }
    }
}
