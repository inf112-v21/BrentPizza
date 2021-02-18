package Server;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.util.Scanner;


public class ServerStart {

    static Integer nrOfPlayers;

    public static void main(String[] args) throws Exception{

        Scanner in = new Scanner(System.in);
        System.out.println("Please choose number of Players:");
        nrOfPlayers = in.nextInt();
        //make sure nrOfPlayers is not null
        ServerConnect connection = new ServerConnect(nrOfPlayers);


    }

}
