package inf112.skeleton.app.Server;

import java.util.Scanner;


public class ServerStart {

    static Integer nrOfPlayers;

    public static void main(String[] args) throws Exception{

        Scanner in = new Scanner(System.in);
        System.out.println("Please choose number of Players:");
        nrOfPlayers = in.nextInt();
        /**System.out.println("Please define TCP port:");
        int tcpPort = in.nextInt();
        System.out.println("Please define UDP port");
        int udpPort = in.nextInt();
        **/
        //make sure nrOfPlayers is not null
        ServerConnect connection = new ServerConnect(nrOfPlayers, 7979, 7878);


    }

}
