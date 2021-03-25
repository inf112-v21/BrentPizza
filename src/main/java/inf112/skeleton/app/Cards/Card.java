package inf112.skeleton.app.Cards;

import java.util.Random;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameLogic.IPlayer;

public class Card {

    Integer priorityNumber = new Random().nextInt(1001);;

    public void action(IPlayer player) {
    }

    public Integer getPriorityNumber() {
        return priorityNumber;
    }
}
