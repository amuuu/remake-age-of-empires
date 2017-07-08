package client.iomanage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MapSender {

    private ServerSocket serverSocket = null;
    private Socket socket = null;

    public void connectSender() throws IOException {
        System.out.println("hereeeee");

        serverSocket = new ServerSocket(15123);
        System.out.println("hereeeee");
        socket = serverSocket.accept();
        if((socket!=null)||(serverSocket!=null)) {
            System.out.println("Not connected");
        }

        System.out.println("Accepted connection : " + socket);
    }
    public void sendMap(String fileName) throws IOException {
        System.out.println("we are in map sender");
        connectSender();
        int filesize=102238600; //2147483647;
        byte[] bytearray = new byte[filesize];
        System.out.println("here 1");
        FileInputStream fin = new FileInputStream(fileName);
        BufferedInputStream bin = new BufferedInputStream(fin);
        bin.read(bytearray, 0, bytearray.length);
        System.out.println("here 2");

        OutputStream os = socket.getOutputStream();
        System.out.println("Sending Files...");
        os.write(bytearray, 0, bytearray.length);
        os.flush();
        socket.close();
        System.out.println("File transfer complete");

    }
}
