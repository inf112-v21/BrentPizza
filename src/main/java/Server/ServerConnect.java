package Server;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.Player;

import java.io.IOException;

public class ServerConnect {

    static Server server;

    public ServerConnect(int nrOfPlayers, int udpPort, int tcpPort) throws IOException {


        server = new Server();

        server.bind(tcpPort, udpPort);

        //register packets that will be sent over the network
        Kryo serverKryo = server.getKryo();
        serverKryo.register(Packet.class);
        serverKryo.register(FirstConnect.class);
        serverKryo.register(WinPacket.class);
        serverKryo.register(Player.class);
        serverKryo.register(Sprite.class);
        serverKryo.register(Vector2.class);

        server.start();
        System.out.println("Server is up and running");

        server.addListener(new ServerListener(nrOfPlayers, server));



    }

}
