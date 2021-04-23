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
    void doTurn(TurnPacket turnPacket);

    /**
     * Set's the ready boolean of this class to true. This enables players to excecute the next program
     */
    void nextRound();

    /**
     * Checks if the current turn is over
     * @return
     */
    boolean isReadyForNextRound();

    /**
     *
     * @return Current tiledMap
     */
    TiledMap getTiledMap();


    /**
     * Checks if players movement is allowed due to walls
     * @param player
     * @return False if movement is not allowed
     */
    boolean checkMove(IPlayer player);

    /**
     * Checks if players movement is allowed
     * @param player
     * @return
     */
    boolean checkMovement(IPlayer player);

    /**
     * Moves players if their robot is on conveyer belt
     */
    void convey();

    /**
     * Retrieves list of objects position based on objects name in tmx file.
     * @param name
     * @return list of Vector2 positions
     */
    ArrayList<Vector2> getObjects(String name);

    /**
     * Retrieves list of positions for the spawn points
     * @return list of Vector2 positions for spawn points
     */
    ArrayList<Vector2> getSpawnPoints();

    /**
     * Retrieves hashmap of the position of conveyer belts on map
     * @return Hashmap with position as key and conveyer type as value
     */
    HashMap<Vector2, String> getConveyorBelts();

    /**
     * Retrieves hashmap of the position of walls on map
     * @return Hashmap with position as key
     */
    HashMap<Vector2, String> getWalls();

    /**
     * Gives list of the location of all flags
     * @return
     */
    ArrayList<Vector2> getFlags();


    /**
     * Repairs this players robot
     */
    void repairRobot(IPlayer player);


    /**
     * When robot falls outside of map this is to be run
     */
    void robotFullDamage(IPlayer player);

    /**
     * checks if robot falls in hole
     */
    void robotFallHole(IPlayer player);

    /**
     * Gives how many flags the player has collected
     * @return Nr of flags collected
     */
    Integer collectedFlags(IPlayer player);

    /**
     * Retrieves laser
     * @return list of sprite
     */
    ArrayList<Sprite> getLaser();


}