package inf112.skeleton.app;

import Server.FirstConnect;
import Server.Packet;
import Server.WinPacket;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class NetworkClient  {
    static Client client;
    static int udpPort = 55403, tcpPort = 55404;
    static String ip = "localhost";
    public int id;
    public int nrOfPlayers;

    public NetworkClient(RoboRally roboRally) throws Exception{
        client = new Client();

        //register classes
        client.getKryo().register(Packet.class);
        client.getKryo().register(FirstConnect.class);
        client.getKryo().register(WinPacket.class);


        client.start();

        System.out.println("Network client is now up and running");


        client.connect(5000, ip, tcpPort, udpPort);

        client.addListener(new Listener(){
            public void received(Connection c, Object p){
                if(p instanceof FirstConnect){
                    FirstConnect con = (FirstConnect) p;
                    id = con.id;
                    System.out.println("I am now id: " + id);
                    nrOfPlayers = con.nrOfPlayers;
                    roboRally.setNrOfPlayers(con.nrOfPlayers);
                }
                if(p instanceof Packet){
                    Packet packet = (Packet) p;
                    System.out.println("Player " + packet.playerThatMovedID + " moved");
                    roboRally.movePlayer(packet.x, packet.y, packet.playerThatMovedID);
                }
                if(p instanceof WinPacket){
                    WinPacket win = (WinPacket) p;
                    roboRally.gameOver(win.ID);
                }
            }
            public void disconnected(Connection c){
                System.out.println("I disconnected");
            }
        });

    }
    public int getId(){
        return this.id;
    }

    public void sendCords(Vector2 v){
        Packet p = new Packet();
        p.playerThatMovedID = this.id;
        p.x = v.x;
        p.y = v.y;
        client.sendUDP(p);
    }
    public void sendWin(){
        WinPacket win = new WinPacket();
        win.ID = this.id;
        client.sendUDP(win);
    }
}
