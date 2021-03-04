package inf112.skeleton.app.Server;

import java.util.Scanner;


public class ServerStart {

    static Integer nrOfPlayers;

    public static void main(String[] args) throws Exception{

        Scanner in = new Scanner(System.in);
        System.out.println("Please choose number of Players:");
        nrOfPlayers = in.nextInt();
        ServerConnect connection = new ServerConnect(nrOfPlayers, 7979, 7878);


    }

}
