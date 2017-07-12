package server;

import server.iomanage.IOManager;

import java.net.Socket;
import java.util.ArrayList;

public class ServerManager {

    // Users maps is as big as the number of the users.
    public static ArrayList<ClientInfo> gameClients;

    public static void main(String[] args) throws Exception{
        IOManager ioManager = new IOManager();

        gameClients.add(new ClientInfo("kia","0","192.168.100.1"));
//        gameClients.add(new ClientInfo("ali","1","192.168.100.2"));

        ioManager.start();

    }
}
