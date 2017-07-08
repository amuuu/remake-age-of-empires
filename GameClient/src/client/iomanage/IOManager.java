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
        System.out.println("we are in thread");
//        while (true){
            System.out.println("we are in while");
            try {
                mapSender.sendMap("src/json/map.json");
//                Thread.sleep(20000);
//                mapReciver.receiveMap("src/json/map.json");
//                Thread.sleep(250);
//                System.out.println(c);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
//        }
    }
}