package inf112.skeleton.app.Cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameLogic.IPlayer;

public class MoveTwoCard extends Card{

    public MoveTwoCard(){
        textureRegionDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/moveTwo.png")));
    }
    @Override
    public void action(IPlayer player) {
        for (int i = 0; i < 2; i++) {
            player.moveForward();
        }
    }

}
