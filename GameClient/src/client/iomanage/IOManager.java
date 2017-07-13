package client.iomanage;

import java.net.InetAddress;

public class IOManager extends Thread {

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
                MapSender.sendCommand(InetAddress.getLocalHost(), "build 1 in 231 ");
                MapSender.sendCommand(InetAddress.getLocalHost(), "build 2 in 476 ");
                MapSender.sendCommand(InetAddress.getLocalHost(), "build 2 in 132 ");
                MapSender.sendCommand(InetAddress.getLocalHost(), "build 1 in 10 ");



            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}