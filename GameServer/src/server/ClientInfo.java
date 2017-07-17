package server;

import java.net.InetAddress;
import java.net.Socket;

public class ClientInfo {
    public String name;
    public int id;
    public String ip;

    public ClientInfo(String name, int id, String ip) {
        this.name = name;
        this.id = id;
        this.ip = ip;
    }
}
