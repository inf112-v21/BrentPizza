package inf112.skeleton.app.Cards;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameLogic.IPlayer;

public class MoveBackCard extends Card{

    public void action(IPlayer player) {
        player.rotatePlayer(180);
        player.moveForward();
        player.rotatePlayer(180);
    }
}
