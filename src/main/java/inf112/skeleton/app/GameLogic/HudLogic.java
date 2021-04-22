package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.Cards.*;
import inf112.skeleton.app.GUI.HUD.Hud;

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
    IBoardLogic boardLogic;
    TextureGetter textureGetter;

    public HudLogic(IBoardLogic boardLogic, Hud hud){
        this.hud = hud;
        this.boardLogic = boardLogic;
        this.textureGetter = new TextureGetter();
        nullCard = new NullCard();

        programCards = new ArrayList<>();
        programImageList = new ArrayList<>();

        ICardGenerator = new CardGenerator();

        hand = ICardGenerator.getRandomHand();
        handButtonList = new ArrayList<>();

        readyButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/buttons/ready.png"))));

        //Create initial programCards list
        for (int i = 0; i < 5; i++) {
            programCards.add(new NullCard());
        }

        //Create programImageList
        for (int i = 0; i < programCards.size(); i++) {
            programImageList.add(new Image(textureGetter.getCardTexture(programCards.get(i))));
        }

        //Create handButtonList
        for (int i = 0; i < hand.size(); i++) {
            handButtonList.add(new ImageButton(textureGetter.getCardTexture(hand.get(i))));
        }
        updateButtonToHandMap();
        addClickListenersHand();

        readyButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                if(boardLogic.isReadyForNextRound()){
                    programCounter = 0;
                    boardLogic.sendProgramList(programCards);
                    newRound();
                }
                else{
                    System.out.println("YOU NEED TO WAIT FOR NEXT ROUND!");
                }
            }
        });
    }


    @Override
    public void updateProgramImageList(){
        for (int i = 0; i < 5; i++) {
            programImageList.set(i,  new Image(textureGetter.getCardTexture(programCards.get(i))));
        }
    }
    @Override
    public void updateHandButtonList(){
        for (int i = 0; i < hand.size(); i++) {
            handButtonList.set(i,  new ImageButton(textureGetter.getCardTexture(hand.get(i))));
        }
        addClickListenersHand();
    }

    @Override
    public void updateHand(){
        hand = getHandLimited();
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
        programImageList.set(programCounter, new Image(textureGetter.getCardTexture(programCards.get(programCounter))));

        Integer indexHandLists = handButtonList.indexOf(button);
        hand.set(indexHandLists, new NullCard());
        handButtonList.set(indexHandLists, new ImageButton(textureGetter.getCardTexture(nullCard)));
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
                    hud.updateStage(boardLogic);
                }
            });
        }
    }

    @Override
    public ArrayList<Integer> getHandPriority() {
        ArrayList<Integer> priority = new ArrayList<>();
        for (Card card: hand) {
            priority.add(card.getPriorityNumber());
        }
        return priority;
    }

    public ArrayList<Card> getHand(){
        return this.hand;
    }
    public ArrayList<Card> getProgramCards(){
        return this.programCards;
    }

    public ArrayList<Card> getHandLimited(){
        ArrayList<Card> randomHand = ICardGenerator.getRandomHand();
        System.out.println(boardLogic.getMyPlayer().getDamageTokens());
        for (int i = randomHand.size()-1; i > randomHand.size()- 1 - boardLogic.getMyPlayer().getDamageTokens(); i--) {
            randomHand.set(i, new NullCard());
        }
        System.out.println(randomHand.toString());
        return randomHand;
    }
    public void newRound(){
        clearProgramCards();
        updateProgramImageList();
        updateHand();
        updateHandButtonList();
        updateButtonToHandMap();
        hud.updateStage(boardLogic);
    }
}
