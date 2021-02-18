package Server;

import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class ServerConnect {

    static Server server;
    static int udpPort = 7969, tcpPort = 7878;

    public ServerConnect(int nrOfPlayers) throws IOException {
        Integer nrOfPlayers1 = nrOfPlayers;
        server = new Server();

        server.bind(tcpPort, udpPort);

        //register packets that will be sent over the network
        server.getKryo().register(Packet.class);
        server.getKryo().register(FirstConnect.class);
        server.getKryo().register(WinPacket.class);

        server.start();
        System.out.println("Server is up and running");

        server.addListener(new ServerListener(nrOfPlayers, server));

    }

}
