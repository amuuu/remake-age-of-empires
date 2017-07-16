package client.iomanage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MapReceiver {

    public static String command;

    public static void receiveCommand() throws Exception{

        int cTosPortNumber;
        if(ClientInfo.id==0) cTosPortNumber= 1777;
        else cTosPortNumber= 1777 + ClientInfo.id;
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
                if(CommandInterpreter.getWordFromString(1,command).equals("ack")){
                    ClientInfo.id=Integer.parseInt(CommandInterpreter.getWordFromString(5,command));
                    System.out.println("my id is "+ ClientInfo.id);
//                    System.out.println("my port is "+ cTosPortNumber);
                }
                else{
//                    System.out.println("my port is "+ cTosPortNumber);

                    CommandInterpreter.interpretCommand(command);
                }

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
