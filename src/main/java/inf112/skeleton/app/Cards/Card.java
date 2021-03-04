package inf112.skeleton.app.Cards;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameLogic.IPlayer;

public class Card {

    TextureRegionDrawable textureRegionDrawable;


    public void action(IPlayer player) {
    }

    public TextureRegionDrawable getTextureRegionDrawable(){
        return this.textureRegionDrawable;
    }

}
