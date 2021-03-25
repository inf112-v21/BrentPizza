package inf112.skeleton.app.Server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.Packets.*;


public class ServerListener extends Listener {

    private final int nrOfPlayers;
    private final Server server;

    private PriorityHandler priorityHandler;

    public ServerListener(int nrOfPlayers, Server server){
        this.nrOfPlayers = nrOfPlayers;
        this.server = server;

        priorityHandler = new PriorityHandler(this.server);
    }

    public void connected(Connection c){
        System.out.println("Client: " + c.getID() + " Just connected");
        FirstConnectPacket initialConnect = new FirstConnectPacket();
        initialConnect.id = c.getID();
        initialConnect.nrOfPlayers = nrOfPlayers;
        server.sendToTCP(c.getID(), initialConnect);

    }
    public void received (Connection c, Object p){
        if(p instanceof Packet){
            Packet packet = (Packet) p;
            System.out.println("Player " + packet.playerThatMovedID + " moved to x = " + packet.x + " y = " + packet.y);
            server.sendToAllExceptTCP(c.getID(), packet);
        }
        if(p instanceof WinPacket){
            WinPacket win = (WinPacket) p;
            System.out.println("Player with ID " + win.ID + " has won!");
            server.sendToAllTCP(win);
        }
        if(p instanceof ProgramCardsPacket){
            ProgramCardsPacket programCardsPacket = (ProgramCardsPacket) p;
            priorityHandler.addCards(programCardsPacket.programCards, programCardsPacket.programCardsPriority, c.getID());
            if(priorityHandler.recievedAllCards()){
                priorityHandler.createTurnList();
            }
        }
        if(p instanceof PlayerReady){
            priorityHandler.playerReady();
            if(priorityHandler.allPlayersReady()){
                priorityHandler.sendNextTurn();
                System.out.println("Next turn sent");
            }
        }


    }

    public void disconnected(Connection c){
        System.out.println("Client " + c.getID() + " disconnected");

    }

}
