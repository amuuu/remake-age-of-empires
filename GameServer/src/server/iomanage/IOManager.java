package server.iomanage;

import server.jsonmanage.JsonManager;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import static server.ServerManager.gameClients;
import static server.iomanage.CommandInterpreter.printArrayList;

public class IOManager extends Thread{

    private JsonManager serverjsonmanager;

    public static ArrayList<int[]> buildCommands; // {who, what, where}
    public static ArrayList<int[]> destroyCommands; // {who, what, where}
    public static ArrayList<int[]> moveCommands;
    public static ArrayList<Integer> newSoldierCommands;
    public static ArrayList<Integer> newWorkerCommands;
    public static ArrayList<String> fightCommands;

    public IOManager(){
        gameClients = new ArrayList<>();
        buildCommands = new ArrayList<>();
        destroyCommands = new ArrayList<>();
        newSoldierCommands = new ArrayList<>();
        newWorkerCommands = new ArrayList<>();
        moveCommands = new ArrayList<>();
        fightCommands = new ArrayList<>();
        serverjsonmanager = new JsonManager();
    }

    @Override
    public void run(){
        try {
            Thread.sleep(5000);
            for(int i=0;i<gameClients.size();i++) {
                // send ack to client
                MapSender.sendAck(gameClients.get(i).ip);
                // after client knows you, receive his ack
                MapReceiver.receiveCommand(gameClients.get(i));
                // if the message was received right,
                if(MapReceiver.command.equals("ackSent")) {
                    // now receive his command
                    MapReceiver.receiveCommand(gameClients.get(i));
                    MapReceiver.receiveCommand(gameClients.get(i));
                    MapReceiver.receiveCommand(gameClients.get(i));
                    MapReceiver.receiveCommand(gameClients.get(i));

                }
            }
            printArrayList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleCommand(String command) {

    }

}
