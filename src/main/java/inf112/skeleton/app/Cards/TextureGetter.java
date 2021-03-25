package inf112.skeleton.app.Cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class TextureGetter {

    public TextureRegionDrawable getCardTexture(Card card){
        if(card instanceof MoveBackCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/moveBack.png")));
        }
        else if(card instanceof MoveOneCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/moveOne.png")));
        }
        else if(card instanceof MoveThreeCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/moveThree.png")));
        }
        else if(card instanceof MoveTwoCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/moveTwo.png")));
        }
        else if(card instanceof TurnLeftCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/leftTurn.png")));
        }
        else if(card instanceof TurnRightCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/rightTurn.png")));
        }
        else if(card instanceof uTurnCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/moveBack.png")));
        }
        else if(card instanceof NullCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/nullCard.png")));
        }
        else{
            return null;
        }
    }
}
