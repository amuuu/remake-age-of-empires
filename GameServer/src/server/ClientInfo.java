package server;

import java.net.Socket;

public class ClientInfo {
    public String name;
    public String id;
    public String ip;
    public Socket socket;

    public ClientInfo(String name, String id, String ip) {
        this.name = name;
        this.id = id;
        this.ip = ip;
    }
}
