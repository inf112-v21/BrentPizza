package inf112.skeleton.app.GameLogic;

import inf112.skeleton.app.Cards.Card;

import java.util.ArrayList;

public interface IBoardLogic {


    boolean checkOutOfBounds();

    boolean checkWin();

    void changePlayer(float x, float y, int id, float rotation);

    void gameOver(int id);

    Boolean getGameOver();

    void setNrOfPlayers(Integer nr);

    ArrayList<IPlayer> getPlayers();

    IPlayer getMyPlayer();

    void setGameOver(Boolean gameOverValue);

    void sendWin();

    void sendPlayer(IPlayer player);

    void movePlayerFromCardList(ArrayList<Card> cardArrayList);
}
