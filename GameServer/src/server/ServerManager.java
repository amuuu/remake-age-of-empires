package server;

import server.iomanage.IOManager;

import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ServerManager {

    // Users maps is as big as the number of the users.
    public static ArrayList<ClientInfo> gameClients;
    
    public static void main(String[] args) throws Exception{
        IOManager ioManager = new IOManager();

        gameClients.add(new ClientInfo("Kia",0,InetAddress.getLocalHost()));
        gameClients.add(new ClientInfo("Ali",1,InetAddress.getLocalHost()));
        gameClients.add(new ClientInfo("Amirreza",2,InetAddress.getLocalHost()));



        ioManager.start();

    }
}
