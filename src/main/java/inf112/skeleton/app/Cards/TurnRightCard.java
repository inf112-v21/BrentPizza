package inf112.skeleton.app.Cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameLogic.IPlayer;

public class TurnRightCard extends Card{


    public TurnRightCard(){
        textureRegionDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/rightTurn.png")));
    }

    public void action(IPlayer player) {
        player.rotatePlayer(-90);
    }
}
