package inf112.skeleton.app.Server;

public class CardNoTexture {
    String cardName;
    Integer cardPriority;
    Integer id;

    public CardNoTexture(String name, Integer cardPriority, Integer id){
        this.cardName = name;
        this.cardPriority = cardPriority;
        this.id = id;
    }
    public String getCardName() {
        return cardName;
    }

    public Integer getId(){
        return this.id;
    }

    public Integer getCardPriority() {
        return cardPriority;
    }
}
