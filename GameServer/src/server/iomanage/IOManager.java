package server.iomanage;

import server.jsonmanage.JsonManager;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import static server.ServerManager.gameClients;
import static server.iomanage.CommandInterpreter.printArrayList;

public class IOManager extends Thread{

    private JsonManager serverjsonmanager;

    static ArrayList<int[]> buildCommands; // {who, what, where}
    static ArrayList<int[]> destroyCommands; // {who, what, where}
    static ArrayList<int[]> moveCommands;
    static ArrayList<Integer> newSoldierCommands;
    static ArrayList<Integer> newWorkerCommands;
    static ArrayList<String> fightCommands;

    public IOManager(){
        gameClients = new ArrayList<>();
        buildCommands = new ArrayList<>();
        destroyCommands = new ArrayList<>();
        newSoldierCommands = new ArrayList<>();
        newWorkerCommands = new ArrayList<>();
        moveCommands = new ArrayList<>();
        fightCommands = new ArrayList<>();
        serverjsonmanager = new JsonManager();

        int[]m = {1,38,45}; buildCommands.add(m);
        int[]m2 = {1,38,105}; buildCommands.add(m2);
        int[]m3 = {1,38,155}; buildCommands.add(m3);

    }

    int index=0;

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Thread.sleep(100);

                for (int i = 0; i < gameClients.size(); i++) {
                    // send ack to client
                    MapSender.sendAck(gameClients.get(i));
                    // after client knows you, receive his ack
                    MapReceiver.receiveCommand(gameClients.get(i));
                    // if the message was received right,
                    if (MapReceiver.command.equals("ackSent")) {

                        receiveCommands(i);
                        Thread.sleep(100);
                        broadcastMap(i);
                        Thread.sleep(100);


                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void receiveCommands(int i) throws Exception{
        int m=1;
        while(!MapReceiver.m.equals("end")) {
            Thread.sleep(10);
            MapReceiver.receiveCommand(gameClients.get(i));
            System.out.println(m++);
            Thread.sleep(10);
        }
    }

    private void broadcastMap(int i) throws Exception{
        for (int[] buildCommand : buildCommands) {
            if (buildCommand[1] != 1) {
                MapSender.sendCommand(gameClients.get(i), "build " + buildCommand[1] + " in " + buildCommand[2] + "");
                System.out.println("done");
                buildCommand[1] = 1;
                Thread.sleep(10);
            }
        }
        MapSender.sendCommand(gameClients.get(i),"end");
    }

    private void listenToCommands(){

    }

}
