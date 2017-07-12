package server.iomanage;

import server.ClientInfo;
import server.jsonmanage.JsonManager;

import java.io.IOException;
import java.util.*;

import static server.ServerManager.gameClients;
import static server.mapmanage.variablerepo.Consts.ROW_NUMBERS;
import static server.mapmanage.variablerepo.Consts.TOTAL_HOUSE_NUMBER;

public class IOManager extends Thread{

    private MapReceiver mapReceiver;
    private MapSender mapSender;
    private JsonManager clientjsonManager;
    private JsonManager serverjsonmanager;


    private List<Integer> currentMap;
    private List<Integer> tempMapList;

    private Queue<int[]> toChangeList;
    private ArrayList<Integer> movesList;

    public IOManager(){
        mapReceiver = new MapReceiver();
        mapSender = new MapSender();
        gameClients = new ArrayList<>();
        toChangeList = new PriorityQueue<>();
        serverjsonmanager = new JsonManager();
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
            serverjsonmanager.readJsonMap("src/json/map.json");
            currentMap = serverjsonmanager.objectList();

            try {
                Thread.sleep(500);
                mapHandle(gameClients.get(playerNumber));
//                mapReceiver.receiveMap(gameClients.get(playerNumber).ip, "src/json/test.json");
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            playerNumber++;
            if(playerNumber%gameClients.size()==0) playerNumber=0;
        }
    }

    private void mapHandle(ClientInfo clientInfo) throws Exception {
        mapReceiver.receiveMap(clientInfo.ip ,"src/json/tempMap.json");
        detectMapChanges("src/json/tempMap", Integer.parseInt(clientInfo.id));
        Thread.sleep(500);
        updateGameMap();
    }
    private void detectMapChanges(String tempMap, int playerNumber){
        clientjsonManager = new JsonManager();
        tempMapList = new ArrayList<>();

        clientjsonManager.readJsonMap(tempMap);
        tempMapList = clientjsonManager.objectList();
        checkNewBuildings(playerNumber);

        tempMapList = null;
        clientjsonManager = null;
    }

    private void checkNewBuildings(int playerNumber){
            for (int i = 0; i < TOTAL_HOUSE_NUMBER; i++) {
                if (checkAgent(i) != 0) {
                    int [] info = {playerNumber, checkAgent(i), i}; //player, building, where
                    toChangeList.add(info);
                }
            }
    }
    private int checkAgent(int index){

        int current = currentMap.get(index);
        int temp = tempMapList.get(index);

        // nothing changed
        if (current == temp)
            return 0;

        // check for new building // background number = 29
        else if (current == 29 &&
                temp >= 200 &&
                temp < 800)
            return (temp/100);

        return -1;
    }

    private void updateGameMap(){
        /*
        1. first, put info in server
        2. then update the json file
        3. finally send it to every client
        */
        while (!toChangeList.isEmpty()) {
            int [] info = toChangeList.poll();

            int playerNumber = info[0];
            int buildingNumber = info[1];
            int buildingPlace = info[2];

            commitChangesToServer();
            commitChangesToJson(buildingNumber, buildingPlace);
            broadcastMap();
        }

    }
    private void commitChangesToServer(){

    }
    private void commitChangesToJson(int buildingNumber, int buildingPlace){
        int buildingSize = 0;
        if(buildingNumber>=200 &&
           buildingNumber<500)
            buildingSize=3;
        else
        if(buildingNumber>=500 &&
           buildingNumber<700)
            buildingSize=5;
        else
        if(buildingNumber>=700 &&
           buildingNumber<800)
            buildingSize=7;

        int centerHouseNumber = buildingPlace + ROW_NUMBERS + buildingSize%2;

        serverjsonmanager.setHouse(centerHouseNumber,buildingNumber);

    }
    private void broadcastMap(){
        try {
            for (ClientInfo gameClient : gameClients)
                mapSender.sendMap("src/map/json", gameClient);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
