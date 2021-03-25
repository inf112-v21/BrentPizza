package inf112.skeleton.app.Server;

import com.esotericsoftware.kryonet.Server;

import inf112.skeleton.app.Packets.NextRound;
import inf112.skeleton.app.Packets.TurnPacket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class PriorityHandler {
    ArrayList<ArrayList<CardNoTexture>> playerCards;
    Server server;
    ArrayList<ArrayList<CardNoTexture>> sortedCards;
    Integer playersReady = 0;
    Integer turnsDone = 0;

    public PriorityHandler(Server server){
        this.server = server;
        playerCards = new ArrayList<>();
        sortedCards = new ArrayList<>();

    }

    public void addCards(ArrayList<String> cardsString, ArrayList<Integer> priorities, Integer id){
        ArrayList<CardNoTexture> playerCard = new ArrayList<>();
        for (int i = 0; i < cardsString.size(); i++) {
            CardNoTexture card = new CardNoTexture(cardsString.get(i), priorities.get(i), id);
            playerCard.add(card);
        }
        playerCards.add(playerCard);
    }

    public void createTurnList(){
        ArrayList<ArrayList<CardNoTexture>> turns = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            turns.add(new ArrayList<>());
        }
        for (ArrayList<CardNoTexture> card: playerCards) {
            for (int i = 0; i < 5; i++) {
                turns.get(i).add(card.get(i));
            }
        }
        sortedCards = sortTurnList(turns);
        sendNextTurn();
    }

    public ArrayList<ArrayList<CardNoTexture>> sortTurnList(ArrayList<ArrayList<CardNoTexture>> turns){
        Comparator<CardNoTexture> comparator = new Comparator<CardNoTexture>() {
            @Override
            public int compare(CardNoTexture o1, CardNoTexture o2) {
                return o1.cardPriority - o2.getCardPriority();
            }
        };

        for (ArrayList<CardNoTexture> turn: turns) {
            Collections.sort(turn, comparator);
        }
        return turns;

    }
    public void sendNextTurn(){
        if(sortedCards.size() <= 0 && turnsDone == 5){
            turnsDone = 0;
            NextRound nextRound = new NextRound();
            server.sendToAllTCP(nextRound);
            playerCards = new ArrayList<>();
        }
        if(sortedCards.size() <= 0){
            return;
        }
        ArrayList<CardNoTexture> turn = sortedCards.get(0);
        sortedCards.remove(0);
        TurnPacket turnPacket = new TurnPacket();
        turnPacket.cards = new ArrayList<>();
        turnPacket.ID = new ArrayList<>();
        for (CardNoTexture card: turn) {
            turnPacket.cards.add(card.getCardName());
            turnPacket.ID.add(card.getId());
        }
        server.sendToAllTCP(turnPacket);
        turnsDone += 1;
    }

    public boolean recievedAllCards(){
        return server.getConnections().length == playerCards.size();
    }

    public boolean allPlayersReady(){
        if(server.getConnections().length == playersReady){
            playersReady = 0;
            return true;
        }
        return false;
    }
    public void playerReady(){
        playersReady += 1;
    }

}
