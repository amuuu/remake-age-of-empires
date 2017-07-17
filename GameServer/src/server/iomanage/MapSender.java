package server.iomanage;

import server.ClientInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MapSender {

    static void sendCommand(ClientInfo clientInfo, String command) throws Exception {
        Socket socket1;
        int portNumber = 1777+clientInfo.id+1;
        String sentAck;
        if(command.equals("ack")) sentAck = "ackSent"; else sentAck = "ackSent";

        socket1 = new Socket(clientInfo.ip, portNumber);

        BufferedReader br = new BufferedReader(new InputStreamReader(socket1.getInputStream()));

        PrintWriter pw = new PrintWriter(socket1.getOutputStream(), true);

        pw.println(command);

        while ((command = br.readLine()) != null) {
//            System.out.println(command);
            pw.println(sentAck);

            if (command.equals(sentAck))
                break;
        }

        br.close();
        pw.flush();

        pw.close();
        socket1.close();

    }

    static void sendAck(ClientInfo clientInfo) throws Exception {
        Socket socket1;
        int portNumber = 1777;
        String sentAck;
        String command = "ack your id is "+(clientInfo.id+1)+" server ip is 192.168.100.1";
        sentAck = "ackSent";

        System.out.println(clientInfo.ip);
        socket1 = new Socket(clientInfo.ip, portNumber);
        System.out.println("socket is made fine");


        BufferedReader br = new BufferedReader(new InputStreamReader(socket1.getInputStream()));

        PrintWriter pw = new PrintWriter(socket1.getOutputStream(), true);

        pw.println(command);

        while ((command = br.readLine()) != null) {
//            System.out.println(command);
            pw.println(sentAck);

            if (command.equals(sentAck))
                break;
        }

        br.close();
        pw.flush();

        pw.close();
        socket1.close();
    }

}