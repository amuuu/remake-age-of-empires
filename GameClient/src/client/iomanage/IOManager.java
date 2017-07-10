package client.iomanage;

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
                mapSender.sendMap("src/json/map.json");
                Thread.sleep(500);
                mapReciver.receiveMap("src/json/map.json");
                Thread.sleep(500);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void handleMap(){

    }
}