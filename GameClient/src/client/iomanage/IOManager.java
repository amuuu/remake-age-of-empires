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
                MapSender.sendAck("127.0.0.1");

                sendCommands();
                Thread.sleep(100);
                receiveCommands();
                Thread.sleep(100);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendCommands() throws Exception{
        for (int[] buildCommand : buildCommands) {
            MapSender.sendCommand(InetAddress.getLocalHost().toString(), "build " + buildCommand[0] + " in " + buildCommand[1] + "");
        }
    }

    private void receiveCommands() throws Exception{
        int m=1;
        while(!MapReceiver.m.equals("end")) {
            Thread.sleep(10);
            MapReceiver.receiveCommand();
            System.out.println(m++);
            Thread.sleep(10);
        }
    }
}