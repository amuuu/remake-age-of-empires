package client;

public class IOManager extends Thread{

    MapReceiver mapReciver;
    MapSender mapSender;

    public IOManager(String ip){
        mapReciver = new MapReceiver();
        mapReciver.ip=ip;
        mapSender = new MapSender();
    }

    @Override
    public void run(){
        while (true){
            try {
                mapSender.sendMap("src/json/testmap.json");
                Thread.sleep(250);
                mapReciver.receiveMap("src/json/testmap.json");
                Thread.sleep(250);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
