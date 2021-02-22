package Server;

import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class ServerConnect {

    static Server server;

    public ServerConnect(int nrOfPlayers, int udpPort, int tcpPort) throws IOException {


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
