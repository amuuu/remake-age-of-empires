package client.iomanage;


import static client.iomanage.IOManager.buildCommands;
import static client.iomanage.IOManager.destroyCommands;
import static client.iomanage.IOManager.moveCommands;

/**
 * Commands Documentation
 * 0. IGNORE "ackSent"
 * 1. making a building:
 *      build (buildingCode) in (buildingPlace)
 * 2. destroying a building:
 *      destroy (buildingCode) in (buildingPlace)
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
    public static void interpretCommand(String command) {

        switch (getWordFromString(1,command)){
            case "build":
                handleBuild(command); break;
            case "destroy":
                handleDestroy(command); break;
            case "move":
                handleMove(command); break;

            case "fight": break;
            case "resultOfFight": break;
            case "shipMove": break;

            case "ack": break;
            default: System.out.println("ERROR: not a valid command for game");
        }
    }
    public static String getWordFromString(int numberOfWantedWordInString, String wholeWord){
        String[] words = wholeWord.split("\\s+");
        for (int i = 0; i < words.length; i++)
            words[i] = words[i].replaceAll("[^\\w]", "");
        return words[numberOfWantedWordInString-1];
    }


    private static void handleBuild(String buildingCommand){
        // command pattern: build (buildingCode) in (buildingPlace)
        // interpreted command pattern: {player number, building code, building place}
        int buildingCode =  Integer.parseInt(getWordFromString(2,buildingCommand));
        int buildingPlace = Integer.parseInt(getWordFromString(4,buildingCommand));
        int[] interpretedBuildCommand = {buildingCode, buildingPlace};
        buildCommands.add(interpretedBuildCommand);

        try {
            new CommandApplier().applyBuildCommand(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleDestroy(String destroyingCommand){
        // command pattern: destroy(buildingCode) in (buildingPlace)
        // interpreted command pattern: {player number, building code, building place}
        int buildingCode =  Integer.parseInt(getWordFromString(2,destroyingCommand));
        int buildingPlace = Integer.parseInt(getWordFromString(4,destroyingCommand));
        int[] interpretedDestroyCommand = {buildingCode, buildingPlace};
        destroyCommands.add(interpretedDestroyCommand);
    }

    private static void handleMove(String movingCommand){
        // command pattern: move (creature kind) from (currentPlace) to (targetPlace)
        // interpreted command pattern: {player number, creature kind, current place, target place}
        int craetureKind =  Integer.parseInt(getWordFromString(2,movingCommand));
        int currentPlace = Integer.parseInt(getWordFromString(4,movingCommand));
        int targetPlace = Integer.parseInt(getWordFromString(6,movingCommand));

        int[] interpretedMoveCommand = {craetureKind, currentPlace, targetPlace};
        moveCommands.add(interpretedMoveCommand);
    }



}
