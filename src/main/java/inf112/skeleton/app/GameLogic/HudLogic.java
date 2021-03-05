package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardGenerator;
import inf112.skeleton.app.Cards.ICardGenerator;
import inf112.skeleton.app.Cards.NullCard;
import inf112.skeleton.app.GUI.Hud;

import java.util.ArrayList;
import java.util.HashMap;

public class HudLogic implements IHudLogic {
    ArrayList<Button> handButtonList;
    ArrayList<Image> programImageList;
    ArrayList<Card> hand;
    Button readyButton;
    ArrayList<Card> programCards;
    NullCard nullCard;
    ICardGenerator ICardGenerator;
    HashMap<Button, Card> buttonToCard;
    Integer programCounter = 0;
    Hud hud;

    public HudLogic(IBoardLogic boardLogic, Hud hud){
        this.hud = hud;
        nullCard = new NullCard();

        programCards = new ArrayList<>();
        ICardGenerator = new CardGenerator();
        hand = ICardGenerator.getRandomHand();
        handButtonList = new ArrayList<>();
        programImageList = new ArrayList<>();
        readyButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/ready.png"))));

        //Create initial programCards list
        for (int i = 0; i < 5; i++) {
            programCards.add(new NullCard());
        }

        //Create programImageList
        for (int i = 0; i < programCards.size(); i++) {
            programImageList.add(new Image(programCards.get(i).getTextureRegionDrawable()));
        }

        //Create handButtonList
        for (int i = 0; i < hand.size(); i++) {
            handButtonList.add(new ImageButton(hand.get(i).getTextureRegionDrawable()));
        }
        updateButtonToHandMap();
        addClickListenersHand();

        readyButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                programCounter = 0;
                boardLogic.movePlayerFromCardList(programCards);
                clearProgramCards();
                updateProgramImageList();
                updateHand();
                updateHandButtonList();
                updateButtonToHandMap();
                hud.updateStage();
            }
        });
    }


    @Override
    public void updateProgramImageList(){
        for (int i = 0; i < 5; i++) {
            programImageList.set(i,  new Image(programCards.get(i).getTextureRegionDrawable()));
        }
    }
    @Override
    public void updateHandButtonList(){
        for (int i = 0; i < hand.size(); i++) {
            handButtonList.set(i,  new ImageButton(hand.get(i).getTextureRegionDrawable()));
        }
        addClickListenersHand();
    }

    @Override
    public void updateHand(){
        hand = ICardGenerator.getRandomHand();
    }

    @Override
    public Button getReadyButton() {
        return readyButton;
    }

    @Override
    public ArrayList<Button> getHandButtonList() {
        return handButtonList;
    }

    @Override
    public ArrayList<Image> getProgramImageList() {
        return programImageList;
    }
    @Override
    public void addToProgramButtonList(Button button){
        if(programCounter >= 5){
            System.out.println("You cannot add any more cards");
            return;
        }

        Card currentCard = buttonToCard.get(button);

        programCards.set(programCounter, currentCard);
        programImageList.set(programCounter, new Image(programCards.get(programCounter).getTextureRegionDrawable()));

        Integer indexHandLists = handButtonList.indexOf(button);
        hand.set(indexHandLists, new NullCard());
        handButtonList.set(indexHandLists, new ImageButton(nullCard.getTextureRegionDrawable()));
        programCounter += 1;

    }

    @Override
    public void clearProgramCards(){
        programCards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            programCards.add(new NullCard());
        }
    }

    @Override
    public void updateButtonToHandMap(){
        buttonToCard = new HashMap<>();

        for(int i = 0; i < hand.size(); i++){
            buttonToCard.put(handButtonList.get(i), hand.get(i));
        }
    }

    @Override
    public void addClickListenersHand(){
        for (Button button: handButtonList) {
            button.addListener(new ClickListener(){
                public void clicked(InputEvent event, float x, float y) {
                    addToProgramButtonList(button);
                    hud.updateStage();
                }
            });
        }
    }
    public ArrayList<Card> getHand(){
        return this.hand;
    }
    public ArrayList<Card> getProgramCards(){
        return this.programCards;
    }
}
