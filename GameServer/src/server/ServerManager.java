package server;

import server.iomanage.IOManager;

import java.net.Socket;
import java.util.ArrayList;

public class ServerManager {

    // Users maps is as big as the number of the users.
    public static ArrayList<ClientInfo> gameClients;

    public static void main(String[] args) throws Exception{
        IOManager ioManager = new IOManager();

        gameClients.add(new ClientInfo("ali","0","192.168.100.1",new Socket("192.168.100.1",1234)));
        gameClients.add(new ClientInfo("amu","1","192.168.100.2",new Socket("192.168.100.2",1235)));

        ioManager.start();

    }
}
