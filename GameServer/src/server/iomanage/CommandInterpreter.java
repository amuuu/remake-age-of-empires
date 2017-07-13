package server.iomanage;

import static server.iomanage.IOManager.*;

/**
 * Commands Documentation ______________________________
 * 0. IGNORE "ackSent"
 * 1. making a building:
 *      build (buildingCode) in (buildingPlace)
 * 2. destroying a building:
 *      destroy (buildingCode) in (buildingPlace)
 * 3. making a soldier:
 *      newSoldier
 * 4. making a worker:
 *      newWorker
 * 5. moving creatures:
 *      move (creature kind) from (currentPlace) to (targetPlace)
 * 6. fighting:
 *      fight in (fightPlace)
 * 7. fighting results:
 *      resultOfFight [(gameFinished), (soldiersDied), (buildingsDestroyed), (workersDied)]
 * 8. moving ships:
 *      sheepMove (currentPlace) to (targetPlace)
 *
 */


public class CommandInterpreter {
    public static void interpretCommand(String command, int playerNumber) {
        String firstWord = getWordFromString(1,command);
        switch (firstWord){
            case "build":
                handleBuild(command, playerNumber); break;
            case "destroy":
                handleDestroy(command, playerNumber); break;
            case "newSoldier":
                handleNewSoldier(playerNumber); break;
            case "newWorker":
                handleNewWorker(playerNumber); break;
            case "move":
                handleMove(command, playerNumber); break;

            case "fight": break;
            case "resultOfFight": break;
            case "shipMove": break;

            case "ack": break;
            default: System.out.println("ERROR: not a valid command for game");
        }
    }
    private static String getWordFromString(int numberOfWantedWordInString, String wholeWord){
        String[] words = wholeWord.split("\\s+");
        for (int i = 0; i < words.length; i++)
            words[i] = words[i].replaceAll("[^\\w]", "");
        return words[numberOfWantedWordInString-1];
    }


    private static void handleBuild(String buildingCommand, int playerNumber){
        // command pattern: build (buildingCode) in (buildingPlace)
        // interpreted command pattern: {player number, building code, building place}
        int buildingCode =  Integer.parseInt(getWordFromString(2,buildingCommand));
        int buildingPlace = Integer.parseInt(getWordFromString(4,buildingCommand));
        int[] interpretedBuildCommand = {playerNumber, buildingCode, buildingPlace};
        buildCommands.add(interpretedBuildCommand);
    }

    private static void handleDestroy(String destroyingCommand, int playerNumber){
        // command pattern: destroy(buildingCode) in (buildingPlace)
        // interpreted command pattern: {player number, building code, building place}
        int buildingCode =  Integer.parseInt(getWordFromString(2,destroyingCommand));
        int buildingPlace = Integer.parseInt(getWordFromString(4,destroyingCommand));
        int[] interpretedDestroyCommand = {playerNumber, buildingCode, buildingPlace};
        destroyCommands.add(interpretedDestroyCommand);
    }

    private static void handleMove(String movingCommand, int playerNumber){
        // command pattern: move (creature kind) from (currentPlace) to (targetPlace)
        // interpreted command pattern: {player number, creature kind, current place, target place}
        int craetureKind =  Integer.parseInt(getWordFromString(2,movingCommand));
        int currentPlace = Integer.parseInt(getWordFromString(4,movingCommand));
        int targetPlace = Integer.parseInt(getWordFromString(6,movingCommand));

        int[] interpretedMoveCommand = {playerNumber, craetureKind, currentPlace, targetPlace};
        moveCommands.add(interpretedMoveCommand);
    }

    private static void handleNewSoldier(int playerNumber){

        newSoldierCommands.add(playerNumber);
    }

    private static void handleNewWorker(int playerNumber){

        newWorkerCommands.add(playerNumber);
    }

    public static void printArrayList(){
        for(int i=0;i<buildCommands.size();i++){
            System.out.println("player " + buildCommands.get(i)[0]
            + " builds " + buildCommands.get(i)[1]
            + " in " + buildCommands.get(i)[2]);
        }
    }

}
