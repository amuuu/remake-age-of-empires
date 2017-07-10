package server.iomanage;


import server.ClientInfo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MapSender {

    private ServerSocket serverSocket = null;

    public void connectSender(Socket socket) throws IOException {
        serverSocket = new ServerSocket(15123);
        socket = serverSocket.accept();

        if((socket!=null)||(serverSocket!=null)) {
            System.out.println("Not connected");
        }

        System.out.println("Accepted connection : " + socket);
    }

    public void sendMap(String fileName, ClientInfo clientInfo) throws IOException {

        connectSender(clientInfo.socket);
        int filesize=39600; //2147483647;
        byte[] bytearray = new byte[filesize];

        FileInputStream fin = new FileInputStream(fileName);
        BufferedInputStream bin = new BufferedInputStream(fin);
        bin.read(bytearray, 0, bytearray.length);

        OutputStream os = clientInfo.socket.getOutputStream();
        System.out.println("Sending Files...");
        os.write(bytearray, 0, bytearray.length);
        os.flush();
        clientInfo.socket.close();
        System.out.println("File transfer complete");

    }
}
