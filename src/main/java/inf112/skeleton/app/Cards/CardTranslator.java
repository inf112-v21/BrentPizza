package inf112.skeleton.app.Cards;

public class CardTranslator {


    public String translateFromCardToString(Card card){
        if(card instanceof MoveBackCard){
            return "MoveBack";
        }
        else if(card instanceof MoveOneCard){
            return "MoveOne";
        }
        else if(card instanceof MoveThreeCard){
            return "MoveThree";
        }
        else if(card instanceof MoveTwoCard){
            return "MoveTwo";
        }
        else if(card instanceof TurnLeftCard){
            return "TurnLeft";
        }
        else if(card instanceof TurnRightCard){
            return "TurnRight";
        }
        else if(card instanceof uTurnCard){
            return "UTurn";
        }
        else{
            return "NullCard";
        }
    }

    public Card translateFromStringToCard(String cardName){
        if(cardName.equals("MoveBack")){
            return new MoveBackCard();
        }
        else if(cardName.equals("MoveOne")){
            return new MoveOneCard();
        }
        else if(cardName.equals("MoveThree")){
            return new MoveThreeCard();
        }
        else if(cardName.equals("MoveTwo")){
            return new MoveTwoCard();
        }
        else if(cardName.equals("TurnLeft")){
            return new TurnLeftCard();
        }
        else if(cardName.equals("TurnRight")){
            return new TurnRightCard();
        }
        else if(cardName.equals("UTurn")){
            return new uTurnCard();
        }
        else{
            return new NullCard();
        }
    }
}
