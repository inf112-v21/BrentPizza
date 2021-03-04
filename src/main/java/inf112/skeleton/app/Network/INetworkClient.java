package inf112.skeleton.app.Network;

import inf112.skeleton.app.GameLogic.IPlayer;

public interface INetworkClient {

    /**
     * Returns the ID of the connection to the server, this also serves as player ID
     * @return Connection ID
     */
    int getId();

    /**
     * Sends a players new location and rotation to the server
     * @param player
     */
    void sendPlayer(IPlayer player);

    /**
     * Sends if player has won
     */
    void sendWin();
}
