package inf112.skeleton.app.Server;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.GameLogic.Player;
import inf112.skeleton.app.Packets.*;

import java.io.IOException;
import java.util.ArrayList;

public class ServerConnect {

    static Server server;

    public void start(int nrOfPlayers, int udpPort, int tcpPort) throws IOException {


        server = new Server();

        server.bind(tcpPort, udpPort);

        //register packets that will be sent over the network
        Kryo serverKryo = server.getKryo();
        serverKryo.register(Packet.class);
        serverKryo.register(FirstConnectPacket.class);
        serverKryo.register(WinPacket.class);
        serverKryo.register(ArrayList.class);
        serverKryo.register(ProgramCardsPacket.class);
        serverKryo.register(String.class);
        serverKryo.register(Integer.class);
        serverKryo.register(PlayerReady.class);
        serverKryo.register(CardNoTexture.class);
        serverKryo.register(TurnPacket.class);
        serverKryo.register(NextRound.class);

        server.start();
        System.out.println("inf112.skeleton.app.Server is up and running");

        server.addListener(new ServerListener(nrOfPlayers, server));



    }

}
