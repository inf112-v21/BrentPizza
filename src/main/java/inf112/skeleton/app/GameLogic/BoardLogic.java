package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Cards.*;
import inf112.skeleton.app.Network.NetworkClient;
import inf112.skeleton.app.Packets.TurnPacket;

import javax.lang.model.element.VariableElement;
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
    ArrayList<Vector2> repairsites2;
    ArrayList<Vector2> flagList;
    ArrayList<Vector2> holes;
    ArrayList<Vector2> spawnpoints;

    ArrayList<Vector2> conveyorNorth;
    ArrayList<Vector2> conveyorSouth;
    ArrayList<Vector2> conveyorWest;
    ArrayList<Vector2> conveyorEast;

    ArrayList<Vector2> twoConveyorSouth;
    ArrayList<Vector2> twoConveyorWest;

    private boolean readyForProgram = true;

    //These will be used in the next iteration and is not just "unused" code.
    ArrayList<Vector2> spawnLocation;
    Vector2 spawnpoint;





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

        spawnpoints = getSpawnPoints();
        myPlayer.setX(spawnpoints.get(myPlayer.getID()-1).x);
        myPlayer.setY(spawnpoints.get(myPlayer.getID()-1).y);
        myPlayer.setRoation(-90);

        //setter første spawn point som lastSavePoint
        myPlayer.setLastSavePoint(myPlayer.getLocation());

        repairsites = get("fix1");
        repairsites2 = get("fix2");



        conveyorNorth = get("oneArrowNorth");
        conveyorSouth = get("oneArrowSouth");
        conveyorWest = get("oneArrowWest");
        conveyorEast= get("oneArrowEast");

        twoConveyorSouth = get("twoArrowSouth");
        twoConveyorWest = get("twoArrowWest");


        holes = get("hole");
        flagList = getFlags();


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
        Vector2 playerLoc = myPlayer.getLocation();
        if(collectFlags.size()==0 && playerLoc.equals(flagList.get(0))){
                collectFlags.add("Flag 1 collected");
                myPlayer.setLastSavePoint(flagList.get(0));
        }
        if(collectFlags.size()==1 && playerLoc.equals(flagList.get(1))){
                collectFlags.add("Flag 2 collected");
                myPlayer.setLastSavePoint(flagList.get(1));
        }
        if(collectFlags.size()==2 && playerLoc.equals(flagList.get(2))){
                collectFlags.add("Flag 3 collected");
                myPlayer.setLastSavePoint(flagList.get(2));
        }
        if(collectFlags.size()==3 && playerLoc.equals(flagList.get(3))){
                collectFlags.add("Flag 4 collected");
        } return collectFlags.size();
    }

    public void robotFallHole() {
        for (Vector2 loc : holes) {
            if (myPlayer.getLocation().equals(loc)) {
                myPlayer.changeLifeTokens(-1);
                myPlayer.setX(myPlayer.getLastSavePoint().x);
                myPlayer.setY(myPlayer.getLastSavePoint().y);
            }
        }
    }


    public void conveyer(ArrayList<Vector2> list, Integer x, Integer y){
        for (Vector2 loc : list) {
            if (myPlayer.getLocation().equals(loc)) {
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 180) {
                    myPlayer.getSprite().translate(x, y);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 270) {
                    myPlayer.getSprite().translate(x, y);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 0) {
                    myPlayer.getSprite().translate(x, y);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 90) {
                    myPlayer.getSprite().translate(x, y);
                }
            }
        }
    }

    public void conveyNorth() {
        for (Vector2 loc : conveyorNorth) {
            if (myPlayer.getLocation().equals(loc)) {
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 180) {
                    myPlayer.getSprite().translate(0, 150);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 270) {
                    myPlayer.getSprite().translate(0, 150);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 0) {
                    myPlayer.getSprite().translate(0, 150);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 90) {
                    myPlayer.getSprite().translate(0, 150);
                }
            }
        }
    }
    public void conveySouth() {
        for (Vector2 loc : conveyorSouth) {
            if (myPlayer.getLocation().equals(loc)) {
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 180) {
                    myPlayer.getSprite().translate(0, -150);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 270) {
                    myPlayer.getSprite().translate(0, -150);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 0) {
                    myPlayer.getSprite().translate(0, -150);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 90) {
                    myPlayer.getSprite().translate(0, -150);
                }
            }
        }
    }

    public void conveyEast() {
        for (Vector2 loc : conveyorEast) {
            if (myPlayer.getLocation().equals(loc)) {
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 180) {
                    myPlayer.getSprite().translate(150, 0);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 270) {
                    myPlayer.getSprite().translate(150, 0);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 0) {
                    myPlayer.getSprite().translate(150, 0);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 90) {
                    myPlayer.getSprite().translate(150, 0);
                }
            }
        }
    }
    public void conveyWest() {
        for (Vector2 loc : conveyorWest) {
            if (myPlayer.getLocation().equals(loc)) {
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 180) {
                    myPlayer.getSprite().translate(-150, 0);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 270) {
                    myPlayer.getSprite().translate(-150, 0);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 0) {
                    myPlayer.getSprite().translate(-150, 0);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 90) {
                    myPlayer.getSprite().translate(-150, 0);
                }
            }
        }
    }
    public void twoConveySouth() {
        for (Vector2 loc : twoConveyorSouth) {
            if (myPlayer.getLocation().equals(loc)) {
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 180) {
                    myPlayer.getSprite().translate(0, -300);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 270) {
                    myPlayer.getSprite().translate(0, -300);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 0) {
                    myPlayer.getSprite().translate(0, -300);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 90) {
                    myPlayer.getSprite().translate(0, -300);
                }
            }
        }
    }
    public void twoConveyWest() {
        for (Vector2 loc : twoConveyorWest) {
            if (myPlayer.getLocation().equals(loc)) {
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 180) {
                    myPlayer.getSprite().translate(-300, 0);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 270) {
                    myPlayer.getSprite().translate(-300, 0);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 0) {
                    myPlayer.getSprite().translate(-300, 0);
                }
                if (Math.abs(myPlayer.getSprite().getRotation() % 360) == 90) {
                    myPlayer.getSprite().translate(-300, 0);
                }
            }
        }
    }

    public void robotFallOutsideMap() {
            if (!checkOutOfBounds()) {
                myPlayer.changeLifeTokens(-1);
                myPlayer.setX(myPlayer.getLastSavePoint().x);
                myPlayer.setY(myPlayer.getLastSavePoint().y);
            }
    }

    public void robotFullDamage() {
        if (myPlayer.getDamageTokens()>= 9) {
            myPlayer.changeLifeTokens(-1);
            myPlayer.setX(myPlayer.getLastSavePoint().x);
            myPlayer.setY(myPlayer.getLastSavePoint().y);
        }
    }

    public void repairRobot(){
        for (Vector2 loc : repairsites) {
            if(myPlayer.getLocation().equals(loc)){
                myPlayer.changeDamageTokens(-1);
            }
        }
        for (Vector2 loc : repairsites2) {
            if(myPlayer.getLocation().equals(loc)){
                myPlayer.changeDamageTokens(-2);
            }
        }
    }


    public ArrayList<Vector2> getFlags(){
        ArrayList<Vector2> flagList = new ArrayList<>();
        Integer index = tiledMap.getLayers().getIndex("flag");
        MapLayer flagObject = tiledMap.getLayers().get(index);
        for (int i = 1; i <= 4; i++) {
            Float x = Float.parseFloat(flagObject.getObjects().get("Flag"+i).getProperties().get("x").toString());
            Float y = Float.parseFloat(flagObject.getObjects().get("Flag"+i).getProperties().get("y").toString());
            Vector2 flagLocation = new Vector2(x,y);
            flagList.add(flagLocation);
        } return flagList;
    }


    public ArrayList<Vector2> getSpawnPoints(){
        ArrayList<Vector2> spawnPoints = new ArrayList<>();
        Integer index = tiledMap.getLayers().getIndex("spawns");
        MapLayer spawnObject = tiledMap.getLayers().get(index);
        for (int i = 1; i < 7; i++) {
            Float x = Float.parseFloat(spawnObject.getObjects().get("Spawn"+i).getProperties().get("x").toString());
            Float y = Float.parseFloat(spawnObject.getObjects().get("Spawn"+i).getProperties().get("y").toString());
            Vector2 spawnLocation = new Vector2(x,y);
            spawnPoints.add(spawnLocation);
        } return spawnPoints;
    }

    public ArrayList<Vector2>get(String name){
        ArrayList<Vector2> getList = new ArrayList<>();
        Integer index = tiledMap.getLayers().getIndex(name);
        MapLayer mapObject = tiledMap.getLayers().get(index);
        for (int i = 0; i < mapObject.getObjects().getCount(); i++) {
            Float x = Float.parseFloat(mapObject.getObjects().get(i).getProperties().get("x").toString());
            Float y = Float.parseFloat(mapObject.getObjects().get(i).getProperties().get("y").toString());
            Vector2 objectLocation = new Vector2(x, y);
            getList.add(objectLocation);
        } return getList;
    }

    //to be removed in future iteration. This is just used for moving manually for testing
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
    public void sendProgramList(ArrayList<Card> cardArrayList){
        readyForProgram = false;
        networkClient.sendProgramCards(cardArrayList);
    }


    public void doTurn(TurnPacket turnPacket){
        ArrayList<Card> cards = new ArrayList<>();
        CardTranslator cardGenerator = new CardTranslator();
        for (String cardName: turnPacket.cards) {
            System.out.println(cardName);
            Card card = cardGenerator.translateFromStringToCard(cardName);
            cards.add(card);
        }
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(turnPacket.ID.get(i));
            IPlayer playerToMove = players.get(turnPacket.ID.get(i)-1);
            if(!checkOutOfBounds()){
                System.out.println("Player fell and died");
                myPlayer.changeLifeTokens(-1); //endre HP til spilleren

                if (myPlayer.getLifeTokens() <= 0){ //hvis han ikke har HP igjen avslutt spillet
                    setGameOver(true);
                }
                else {
                    setLocation(myPlayer.getLastSavePoint()); //ellers endre posisjonen til siste savepoint
                    networkClient.sendPlayer(myPlayer);
                }
            }
            cards.get(i).action(playerToMove);
        }

        if(checkWin()){
            networkClient.sendWin();
        }
        try{
            Thread.sleep(500);
        }catch (Exception e){
            System.out.println(e);
        }

        networkClient.doneTurn();
    }

    @Override
    public void nextRound() {
        readyForProgram = true;
    }

    @Override
    public boolean isReadyForNextRound() {
        return readyForProgram;
    }

    @Override
    public TiledMap getTiledMap() {
        return this.tiledMap;
    }
}
