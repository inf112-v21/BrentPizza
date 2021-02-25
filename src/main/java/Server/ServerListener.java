package Server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;


public class ServerListener extends Listener {

    private final int nrOfPlayers;
    private final Server server;

    public ServerListener(int nrOfPlayers, Server server){
        this.nrOfPlayers = nrOfPlayers;
        this.server = server;

    }

    public void connected(Connection c){
        System.out.println("Client: " + c.getID() + " Just connected");
        FirstConnect initialConnect = new FirstConnect();
        initialConnect.id = c.getID();
        initialConnect.nrOfPlayers = nrOfPlayers;
        server.sendToTCP(c.getID(), initialConnect);

    }
    public void received (Connection c, Object p){
        if(p instanceof Packet){
            Packet packet = (Packet) p;
            System.out.println("Player " + packet.playerThatMovedID + " moved");
            server.sendToAllExceptTCP(c.getID(), packet);
        }
        if(p instanceof WinPacket){
            WinPacket win = (WinPacket) p;
            server.sendToAllTCP(win);

        }
    }

    public void disconnected(Connection c){
        System.out.println("Client " + c.getID() + " disconnected");
    }

}
