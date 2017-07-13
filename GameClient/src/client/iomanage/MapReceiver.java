package client.iomanage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MapReceiver {

    public static String command;

    public static void receiveCommand() throws Exception{

        int cTosPortNumber = 1777 ;//+ ClientInfo.id;
        String sentAck = "ackSent";

        ServerSocket servSocket = new ServerSocket(cTosPortNumber);
        System.out.println("Waiting for a connection on " + cTosPortNumber);

        Socket fromClientSocket = servSocket.accept();

        PrintWriter pw = new PrintWriter(fromClientSocket.getOutputStream(), true);

        BufferedReader br = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()));

        while ((command = br.readLine()) != null) {
            System.out.println("The message: " + command);

            if (command.equals(sentAck)) {
                pw.println(sentAck);
                break;
            } else {
                command = "Server returns " + command;
                pw.println(command);
            }
        }
        pw.flush();
        pw.close();
        br.close();
        fromClientSocket.close();
        servSocket.close();

    }

}
