package client.iomanage;

import java.net.InetAddress;
import java.util.ArrayList;

public class IOManager extends Thread {

    static ArrayList<int[]> buildCommands; // {who, what, where}
    static ArrayList<int[]> destroyCommands; // {who, what, where}
    static ArrayList<int[]> moveCommands;
    static ArrayList<String> fightCommands;

    public IOManager() {
        buildCommands = new ArrayList<>();
        destroyCommands = new ArrayList<>();
        moveCommands = new ArrayList<>();
        fightCommands = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            // wait until you receive an ack from server
            MapReceiver.receiveCommand();
            // after you got one,
            if (MapReceiver.command.equals("ackSent")) {
                // let server know you are willing to send a command
                MapSender.sendAck(InetAddress.getLocalHost());
                // send your command
//                MapSender.sendCommand(InetAddress.getLocalHost(), "build 1 in 231 ");
//                MapSender.sendCommand(InetAddress.getLocalHost(), "build 1 in 10 ");

                MapReceiver.receiveCommand();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}