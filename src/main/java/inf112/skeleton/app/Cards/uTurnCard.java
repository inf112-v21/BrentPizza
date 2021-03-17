package inf112.skeleton.app.Cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameLogic.IPlayer;

public class uTurnCard extends Card {

    public uTurnCard() {
        textureRegionDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/uTurn.png")));
    }

    public void action(IPlayer player) { player.rotatePlayer(180);}

}

