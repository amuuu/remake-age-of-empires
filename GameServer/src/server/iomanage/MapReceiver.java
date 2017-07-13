package server.iomanage;

import server.ClientInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MapReceiver {

    public static String command;

    public static void receiveCommand(ClientInfo clientInfo) throws Exception{

        int cTosPortNumber = 1777 + 1;
        String sentAck = "ackSent";

        ServerSocket servSocket = new ServerSocket(cTosPortNumber);
//        System.out.println("Waiting for a connection on " + cTosPortNumber);

        Socket fromClientSocket = servSocket.accept();

        PrintWriter pw = new PrintWriter(fromClientSocket.getOutputStream(), true);

        BufferedReader br = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()));

        while ((command = br.readLine()) != null) {
            if((!command.equals("ackSent"))) System.out.println("The message: "+ clientInfo.name+" " + command);
            if (command.equals(sentAck)) {
                pw.println(sentAck);
                break;
            } else {
                CommandInterpreter.interpretCommand(command, clientInfo.id);

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
