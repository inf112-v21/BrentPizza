package inf112.skeleton.app.Cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class TextureGetter {

    public TextureRegionDrawable getCardTexture(Card card){
        if(card instanceof MoveBackCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/cards/moveBack.png")));
        }
        else if(card instanceof MoveOneCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/cards/moveOne.png")));
        }
        else if(card instanceof MoveThreeCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/cards/moveThree.png")));
        }
        else if(card instanceof MoveTwoCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/cards/moveTwo.png")));
        }
        else if(card instanceof TurnLeftCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/cards/leftTurn.png")));
        }
        else if(card instanceof TurnRightCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/cards/rightTurn.png")));
        }
        else if(card instanceof uTurnCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/cards/uTurn.png")));
        }
        else if(card instanceof NullCard){
            return new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/cards/nullCard.png")));
        }
        else{
            return null;
        }
    }
}
