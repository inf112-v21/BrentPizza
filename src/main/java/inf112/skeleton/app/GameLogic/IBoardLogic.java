package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Packets.TurnPacket;

import java.util.ArrayList;
import java.util.HashMap;

public interface IBoardLogic {

    /**
     * Checks if myPlayer is outside of the map
     *
     * @return true if player is inside the map, false otherwise
     */
    boolean checkOutOfBounds(IPlayer player);

    /**
     * Checks if the player has won by walking on a flag,
     * @return true if myPlayer has won the game
     */
    boolean checkWin(IPlayer player);

    /**
     * Changes a given player in the list of players position based on the id
     * @param x new x position
     * @param y new y position
     * @param id id of player, the index of this player in the list will be id-1
     * @param rotation current rotation of the player
     */
    void changePlayer(float x, float y, int id, float rotation);

    /**
     * signalizes that the game is over, and who has won
     * @param id the id of the winning player, the index of this player in the list is id-1
     */
    void gameOver(int id);

    /**
     * Gives a boolean value so the GUI can check if the game is over
     * @return true if the game is over
     */
    Boolean getGameOver();

    /**
     * This is used by the network client to set the number of players in the game based on what the
     * client was told by the server
     * @param nr how many players
     */
    void setNrOfPlayers(Integer nr);

    /**
     * Gives a list of all the current players in the game
     * @return
     */
    ArrayList<IPlayer> getPlayers();

    /**
     * Gives the player from the list that is this clients player
     * @return Player
     */
    IPlayer getMyPlayer();

    /**
     * Let's the network client set if the game is over when another player has won
     * @param gameOverValue true if game is over
     */
    void setGameOver(Boolean gameOverValue);

    /**
     * Sends a win message to the server that this client has won the game
     */
    void sendWin();

    /**
     * After this clients player has moved, the parameters from this clients player are sent to the server, can
     * also send another players parameters to the server
     * @param player
     */
    void sendPlayer(IPlayer player);

    /**
     * Does the moves based on a list of cards and the given card objects action
     * @param cardArrayList
     */
    void sendProgramList(ArrayList<Card> cardArrayList);


    /**
     * Moves all the players based on sorted recieved cards from the server
     * @param turnPacket List of cards to be played this turn and their respective player id
     */
    public void doTurn(TurnPacket turnPacket);

    /**
     * Set's the ready boolean of this class to true. This enables players to excecute the next program
     */
    public void nextRound();

    /**
     * Checks if the current turn is over
     * @return
     */
    public boolean isReadyForNextRound();

    /**
     *
     * @return Current tiledMap
     */
    public TiledMap getTiledMap();


    public boolean checkMove(IPlayer player);

    public void convey();
    public ArrayList<Vector2> getObjects(String name);
    public ArrayList<Vector2> getSpawnPoints();
    public HashMap<Vector2, String> getConveyorBelts();
    public HashMap<Vector2, String> getWalls();

    /**
     * Gives list of the location of all flags
     * @return
     */
    public ArrayList<Vector2> getFlags();


    /**
     * Repairs this players robot
     */
    public void repairRobot(IPlayer player);


    /**
     * When robot falls outside of map this is to be run
     */
    public void robotFullDamage(IPlayer player);

    /**
     * checks if robot falls in hole
     */
    public void robotFallHole(IPlayer player);

    /**
     * Gives how many flags the player has collected
     * @return Nr of flags collected
     */
    public Integer collectedFlags(IPlayer player);

    public ArrayList<Sprite> getLaser();


    public boolean checkMovement(IPlayer player);



}