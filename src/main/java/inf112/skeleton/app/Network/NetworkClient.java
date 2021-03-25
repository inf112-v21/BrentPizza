package inf112.skeleton.app.Network;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardTranslator;
import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IPlayer;
import inf112.skeleton.app.Packets.*;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.Server.CardNoTexture;

import java.util.ArrayList;

public class NetworkClient implements INetworkClient {
    static Client client;
    static int udpPort = 7979, tcpPort = 7878;
    static String ip = "localhost";
    public int id;

    private CardTranslator cardTranslator;

    public NetworkClient(IBoardLogic boardLogic) throws Exception{
        client = new Client();
        cardTranslator = new CardTranslator();
        //register classes
        Kryo clientKryo = client.getKryo();
        clientKryo.register(Packet.class);
        clientKryo.register(FirstConnectPacket.class);
        clientKryo.register(WinPacket.class);
        clientKryo.register(ArrayList.class);
        clientKryo.register(ProgramCardsPacket.class);
        clientKryo.register(String.class);
        clientKryo.register(Integer.class);
        clientKryo.register(PlayerReady.class);
        clientKryo.register(CardNoTexture.class);
        clientKryo.register(TurnPacket.class);
        clientKryo.register(NextRound.class);

        client.start();

        System.out.println("Network client is now up and running");


        client.connect(5000, ip, tcpPort, udpPort);

        client.addListener(new Listener(){
            public void received(Connection c, Object p){
                if(p instanceof FirstConnectPacket){
                    FirstConnectPacket con = (FirstConnectPacket) p;
                    id = con.id;
                    System.out.println("I am now id: " + id);
                    boardLogic.setNrOfPlayers(con.nrOfPlayers);
                }
                if(p instanceof Packet){
                    Packet packet = (Packet) p;
                    System.out.println("Player " + packet.playerThatMovedID + " moved");
                    boardLogic.changePlayer(packet.x, packet.y, packet.playerThatMovedID, packet.rotation);
                }
                if(p instanceof WinPacket){
                    WinPacket win = (WinPacket) p;
                    boardLogic.gameOver(win.ID);
                }
                if(p instanceof TurnPacket){
                    TurnPacket turnPacket = (TurnPacket) p;
                    boardLogic.doTurn(turnPacket);
                }
                if(p instanceof NextRound){
                    boardLogic.nextRound();
                    System.out.println("Ready for next round");
                }
            }
            public void disconnected(Connection c){
                System.out.println("I disconnected");
            }
        });

    }
    @Override
    public int getId(){
        return this.id;
    }

    @Override
    public void sendPlayer(IPlayer player){
        Packet p = new Packet();
        p.rotation = player.getRotation();
        Vector2 playerLoc = player.getLocation();
        p.x = playerLoc.x;
        p.y = playerLoc.y;
        p.playerThatMovedID = player.getID();
        client.sendTCP(p);
    }
    @Override
    public void sendWin(){
        WinPacket win = new WinPacket();
        win.ID = this.id;
        client.sendTCP(win);
    }

    public void sendProgramCards(ArrayList<Card> programCards){
        ArrayList<String> programCardsStringed = new ArrayList<>();
        ArrayList<Integer> priorityNumbers = new ArrayList<>();
        for (Card card: programCards) {
            programCardsStringed.add(cardTranslator.translateFromCardToString(card));
            priorityNumbers.add(card.getPriorityNumber());
        }

        ProgramCardsPacket programCardsPacket = new ProgramCardsPacket();
        programCardsPacket.programCards = programCardsStringed;
        programCardsPacket.programCardsPriority = priorityNumbers;
        client.sendTCP(programCardsPacket);
    }
    public void doneTurn(){
        PlayerReady playerReady = new PlayerReady();
        client.sendTCP(playerReady);
    }
}
