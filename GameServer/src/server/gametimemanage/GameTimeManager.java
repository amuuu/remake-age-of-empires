package server.gametimemanage;

public class GameTimeManager extends Thread{

    // indexes array it's as big as the number of the arrayLists
    // that we have for game commands.
    private int[] index;

    @Override
    public void run(){

    }

    GameTimeManager(){

        index = new int[6];
    }
}
