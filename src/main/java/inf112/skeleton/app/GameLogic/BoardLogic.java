package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Network.NetworkClient;

import java.util.ArrayList;

public class BoardLogic implements IBoardLogic {

    private NetworkClient networkClient;

    public TiledMap tiledMap;
    ArrayList<IPlayer> players;
    IPlayer myPlayer;
    Boolean gameOver = false;
    private Integer nrOfPlayers;
    ArrayList<String> collectFlags = new ArrayList();
    ArrayList<Vector2> repairsites;
    ArrayList<Vector2> holes;




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
            IPlayer playerToAdd = new Player(i, new Sprite(new Texture(Gdx.files.internal("src/main/Resources/robot" + i + ".png"))));
            this.players.add(playerToAdd);
        }
        myPlayer = players.get(networkClient.getId()-1);

        //setter første spawn point som lastSavePoint
        myPlayer.setLastSavePoint(myPlayer.getLocation());

        repairsites = getRepairSites();
        holes = getHoles();

    }


    /**
     * Checks if player is inside map.
     *
     * Returns true if player is inside map.
     * Returns false if player is outside map.
     */


    @Override
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

    @Override
    public boolean checkWin(){
         return collectedFlags() == 4;
    }

    public Integer collectedFlags() {
        int index = tiledMap.getLayers().getIndex("flag");
        MapLayer winLayer = tiledMap.getLayers().get(index);
        float flagX1 = Float.parseFloat(winLayer.getObjects().get("Flag1").getProperties().get("x").toString());
        float flagY1 = Float.parseFloat(winLayer.getObjects().get("Flag1").getProperties().get("y").toString());
        Vector2 flag1pos = new Vector2(flagX1,flagY1);

        float flagX2 = Float.parseFloat(winLayer.getObjects().get("Flag2").getProperties().get("x").toString());
        float flagY2 = Float.parseFloat(winLayer.getObjects().get("Flag2").getProperties().get("y").toString());
        Vector2 flag2pos = new Vector2(flagX2,flagY2);

        float flagX3 = Float.parseFloat(winLayer.getObjects().get("Flag3").getProperties().get("x").toString());
        float flagY3 = Float.parseFloat(winLayer.getObjects().get("Flag3").getProperties().get("y").toString());
        Vector2 flag3pos = new Vector2(flagX3,flagY3);

        float flagX4 = Float.parseFloat(winLayer.getObjects().get("Flag4").getProperties().get("x").toString());
        float flagY4 = Float.parseFloat(winLayer.getObjects().get("Flag4").getProperties().get("y").toString());
        Vector2 playerLoc = myPlayer.getLocation();
        if(collectFlags.size()==0 && playerLoc.x == flagX1 & playerLoc.y == flagY1){
            collectFlags.add("Flag 1 collected");
            myPlayer.setLastSavePoint(flag1pos);
        }
        if(collectFlags.size()==1 && playerLoc.x == flagX2 & playerLoc.y == flagY2){
            collectFlags.add("Flag 2 collected");
            myPlayer.setLastSavePoint(flag2pos);
        }
        if(collectFlags.size()==2 && playerLoc.x == flagX3 & playerLoc.y == flagY3){
            collectFlags.add("Flag 3 collected");
            myPlayer.setLastSavePoint(flag3pos);
        }
        if(collectFlags.size()==3 && playerLoc.x == flagX4 & playerLoc.y == flagY4){
            collectFlags.add("Flag 4 collected");
        } return collectFlags.size();
    }
    public void robotFallHole() {
        for (Vector2 loc : holes) {
            if (myPlayer.getLocation().equals(loc)) {
                myPlayer.changeLifeTokens(-1);
            }
        }
    }
    public void repairRobot(){
        for (Vector2 loc : repairsites) {
            if(myPlayer.getLocation().equals(loc)){
                myPlayer.changeDamageTokens(-1);
            }
        }
    }
    public ArrayList<Vector2> getRepairSites(){
        ArrayList<Vector2> repairsites = new ArrayList<>();
        Integer index = tiledMap.getLayers().getIndex("fix1");
        MapLayer repairObject = tiledMap.getLayers().get(index);
        for (int i = 0; i < repairObject.getObjects().getCount(); i++) {
            Float x = Float.parseFloat(repairObject.getObjects().get(i).getProperties().get("x").toString());
            Float y = Float.parseFloat(repairObject.getObjects().get(i).getProperties().get("y").toString());
            Vector2 repairLocation = new Vector2(x,y);
            repairsites.add(repairLocation);
        } return repairsites;
    }

    public ArrayList<Vector2> getHoles(){
        ArrayList<Vector2> holes = new ArrayList<>();
        Integer index = tiledMap.getLayers().getIndex("hole");
        MapLayer holeObject = tiledMap.getLayers().get(index);
        for (int i = 0; i < holeObject.getObjects().getCount(); i++) {
            Float x = Float.parseFloat(holeObject.getObjects().get(i).getProperties().get("x").toString());
            Float y = Float.parseFloat(holeObject.getObjects().get(i).getProperties().get("y").toString());
            Vector2 holeLocation = new Vector2(x,y);
            holes.add(holeLocation);
        } return holes;
    }

    @Override
    public void changePlayer(float x, float y, int id, float rotation){
        IPlayer curPlayer = players.get(id-1);
        curPlayer.setX(x);
        curPlayer.setY(y);
        curPlayer.setRoation(rotation);
        players.set(id-1, curPlayer);
    }

    @Override
    public void gameOver(int id){
        System.out.println("Player with ID " + id + " has won");
        gameOver = true;
    }

    @Override
    public Boolean getGameOver(){
        return this.gameOver;
    }


    @Override
    public void setNrOfPlayers(Integer nr){
        this.nrOfPlayers = nr;
    }
    @Override
    public ArrayList<IPlayer> getPlayers(){
        return this.players;
    }
    @Override
    public IPlayer getMyPlayer(){
        return this.myPlayer;
    }
    @Override
    public void setGameOver(Boolean gameOverValue){
        this.gameOver = gameOverValue;
    }
    @Override
    public void sendWin(){
        networkClient.sendWin();
    }
    @Override
    public void sendPlayer(IPlayer player){
        networkClient.sendPlayer(player);
    }

    @Override
    public void setLocation(Vector2 location){
        myPlayer.getSprite().setX(location.x);
        myPlayer.getSprite().setY(location.y);

    }

    @Override
    public void movePlayerFromCardList(ArrayList<Card> cardArrayList){
        for (Card card: cardArrayList) {
            card.action(myPlayer);
            if(!checkOutOfBounds()){
                System.out.println("Player fell and died");
                myPlayer.changeLifeTokens(-1); //endre HP til spilleren

                if (myPlayer.getLifeTokens() <= 0){ //hvis han ikke har HP igjen avslutt spillet
                    setGameOver(true);
                }
                else setLocation(myPlayer.getLastSavePoint()); //ellers endre posisjonen til siste savepoint
            }
        }
        if(checkWin()){
            networkClient.sendWin();
        }
        sendPlayer(myPlayer);

    }


}
