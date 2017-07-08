package server.iomanage;

import server.jsonmanage.JsonManager;

import java.util.ArrayList;
import java.util.List;

public class IOManager extends Thread{

    MapReceiver mapReciver;
    MapSender mapSender;
    JsonManager jsonManager;

    ArrayList<String> gameClients;

    List<Integer> currentMap;
    List<Integer> tempMapList;

    ArrayList<int[]> toChangeList;
    ArrayList<Integer> movesList;
    // Users maps is as big as the number of the users.

    public IOManager(String ip){
        mapReciver = new MapReceiver();
        mapSender = new MapSender();
        gameClients = new ArrayList<>();
        toChangeList = new ArrayList<>();
    }

    /**
     *
     * Logical algorithm to handle multiple client's maps being sent and received
     * 1. receive the map from client (called temp map);
     * 2. compare changes and overwrite map with new changes
     * 3. broadcast the map
     *
     * How to compare map changes and generate the new map between multiple clients?
     * As listed below, different parameters are considered:
     * 1. Making a new building in empty ground.
     * 2. A soldier moving. ----> (not sure yet)
     * 3.
     *
     */
    @Override
    public void run(){
        int playerNumber = 0;
        while (true) {
            try {
                mapHandle(playerNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }
            playerNumber++;
            if(playerNumber%gameClients.size()==0) playerNumber=0;
        }
    }

    private void mapHandle(int playerNumber) throws Exception {
        mapReciver.receiveMap("192.168.100."+playerNumber+"","tempMap.json");
        adaptMap("tempMap", playerNumber);
        broadcastMap("map");
    }

    private void adaptMap(String tempMap, int playerNumber){
        jsonManager = new JsonManager();
        tempMapList = new ArrayList<>();

        jsonManager.readJsonMap(tempMap);
        tempMapList = jsonManager.objectList();
        new checkNewBuildings(playerNumber).start();

        tempMapList = null;
        jsonManager = null;
    }

    private void broadcastMap(String map) {
    }

    private class checkNewBuildings extends Thread {
        int playerNumber = 0;
        checkNewBuildings(int playerNumber){
            this.playerNumber=playerNumber;
        }
        @Override
        public void run(){
            for (int i = 0; i < currentMap.size(); i++) {
                if (check(i) != 0) {
                    int [] info = {playerNumber, check(i), i}; //player, building, where
                    toChangeList.add(info);
                }
            }
        }
        private int check(int index){
            int current = currentMap.get(index);
            int temp = tempMapList.get(index);

            // nothing changed
            if (current == temp)
                return 0;

            // check for new building
            else if (current == 29 &&
                     temp >= 200 &&
                     temp < 900)
                return (temp/100);

            return -1;
        }
    }


}

// background number = 29

