package client.filemanage;

import javafx.scene.image.Image;

public class FileManager {

    public Image []id = new Image[58];

    public FileManager() throws Exception{
        loadImages();
    }

    public void loadImages() throws Exception{
        for(int i=0;i<58;i++) {
            String address = "file:src/json/icons/images/"+(i+49)+".gif";
            id[i] = new Image(address, 32, 32, false, false);
        }
    }
    //
}
