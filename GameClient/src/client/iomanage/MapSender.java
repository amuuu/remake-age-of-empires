package client.iomanage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MapSender {

    public static void sendCommand(InetAddress clientIP, String command) throws Exception {
        Socket socket1;
        int portNumber = 1777+1;
        String sentAck;
        if(command.equals("ack")) sentAck = "ackSent"; else sentAck = "ackSent";

        socket1 = new Socket(InetAddress.getLocalHost(), portNumber);

        BufferedReader br = new BufferedReader(new InputStreamReader(socket1.getInputStream()));

        PrintWriter pw = new PrintWriter(socket1.getOutputStream(), true);

        pw.println(command);

        while ((command = br.readLine()) != null) {
            System.out.println(command);
            pw.println(sentAck);

            if (command.equals(sentAck))
                break;
        }

        br.close();
        pw.flush();

        pw.close();
        socket1.close();
    }

    public static void sendAck(InetAddress clientIP) throws Exception {
        Socket socket1;
        int portNumber = 1777+ClientInfo.id;
        String sentAck;
        String command = "ack";
        sentAck = "ackSent";

        socket1 = new Socket(InetAddress.getLocalHost(), portNumber);

        BufferedReader br = new BufferedReader(new InputStreamReader(socket1.getInputStream()));

        PrintWriter pw = new PrintWriter(socket1.getOutputStream(), true);

        pw.println(command);

        while ((command = br.readLine()) != null) {
            System.out.println(command);
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