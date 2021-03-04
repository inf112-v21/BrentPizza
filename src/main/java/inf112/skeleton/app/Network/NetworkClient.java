package inf112.skeleton.app.Network;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IPlayer;
import inf112.skeleton.app.Packets.FirstConnectPacket;
import inf112.skeleton.app.Packets.Packet;
import inf112.skeleton.app.Packets.WinPacket;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class NetworkClient implements INetworkClient {
    static Client client;
    static int udpPort = 7979, tcpPort = 7878;
    static String ip = "localhost";
    public int id;
    public int nrOfPlayers;

    public NetworkClient(IBoardLogic boardLogic) throws Exception{
        client = new Client();

        //register classes
        Kryo clientKryo = client.getKryo();
        clientKryo.register(Packet.class);
        clientKryo.register(FirstConnectPacket.class);
        clientKryo.register(WinPacket.class);


        client.start();

        System.out.println("Network client is now up and running");


        client.connect(5000, ip, tcpPort, udpPort);

        client.addListener(new Listener(){
            public void received(Connection c, Object p){
                if(p instanceof FirstConnectPacket){
                    FirstConnectPacket con = (FirstConnectPacket) p;
                    id = con.id;
                    System.out.println("I am now id: " + id);
                    nrOfPlayers = con.nrOfPlayers;
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
}
