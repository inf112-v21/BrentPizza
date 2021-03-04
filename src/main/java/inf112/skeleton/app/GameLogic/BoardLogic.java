package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Network.NetworkClient;

import java.util.ArrayList;

public class BoardLogic {

    private NetworkClient networkClient;

    private TiledMap tiledMap;
    ArrayList<Player> players;
    public Player myPlayer;
    Boolean gameOver = false;
    private Integer nrOfPlayers;



    public BoardLogic(TiledMap tiledMap) throws InterruptedException {

        this.tiledMap = tiledMap;

        try{
            this.networkClient = new NetworkClient(this);
        }catch (Exception e){
            System.out.println(e);
        }

        while(nrOfPlayers == null){
            Thread.sleep(1000);
        }
        players = new ArrayList<>();
        for (int i = 1; i <= nrOfPlayers; i++) {
            Player playerToAdd = new Player(i, new Sprite(new Texture(Gdx.files.internal("src/main/Resources/robot" + i + ".png"))));
            this.players.add(playerToAdd);
        }
        myPlayer = players.get(networkClient.getId()-1);



    }


    /**
     * Checks if player is inside map.
     *
     * Returns true if player is inside map.
     * Returns false if player is outside map.
     */


    public boolean checkOutOfBounds() {

        MapProperties prop = tiledMap.getProperties();

        // Get tiledMap width and height.
        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        // Get tile pixel width and height.
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);
        // Calculate map width and height
        int mapPixelWidth = (mapWidth * tilePixelWidth)-150;
        int mapPixelHeight = (mapHeight * tilePixelHeight)-150;

        Vector2 playerLoc = myPlayer.getLocation();

        if(playerLoc.x > mapPixelWidth || playerLoc.y > mapPixelHeight) {
            return false;
        }
        else return !(playerLoc.x < 0) && !(playerLoc.y < 0);
    }

    public boolean checkWin(){
        int index = tiledMap.getLayers().getIndex("flag");
        MapLayer winLayer = tiledMap.getLayers().get(index);
        float flagX = Float.parseFloat(winLayer.getObjects().get("Flag1").getProperties().get("x").toString());
        float flagY = Float.parseFloat(winLayer.getObjects().get("Flag1").getProperties().get("y").toString());
        Vector2 playerLoc = myPlayer.getLocation();
        return playerLoc.x == flagX & playerLoc.y == flagY;
    }

    public void changePlayer(float x, float y, int id, float rotation){
        Player curPlayer = players.get(id-1);
        curPlayer.setX(x);
        curPlayer.setY(y);
        curPlayer.setRoation(rotation);
        players.set(id-1, curPlayer);
    }

    public void gameOver(int id){
        System.out.println("Player with ID " + id + " has won");
        gameOver = true;
    }

    public Boolean getGameOver(){
        return this.gameOver;
    }


    public void setNrOfPlayers(Integer nr){
        this.nrOfPlayers = nr;
    }
    public ArrayList<Player> getPlayers(){
        return this.players;
    }
    public Player getMyPlayer(){
        return this.myPlayer;
    }
    public void setGameOver(Boolean gameOverValue){
        this.gameOver = gameOverValue;
    }
    public void sendWin(){
        networkClient.sendWin();
    }
    public void sendPlayer(Player player){
        networkClient.sendPlayer(player);
    }
}
