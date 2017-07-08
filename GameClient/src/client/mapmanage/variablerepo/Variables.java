package client.mapmanage.variablerepo;

import client.filemanage.FileManager;
import client.iomanage.IOManager;
import client.jsonmanage.JsonManager;
import javafx.scene.image.ImageView;

import java.util.List;

public class Variables {

    public static int cordinateXTransform =0;
    public static int cordinateYTransform =0;

    // Map variables
    public static JsonManager mapJSON;
    public static ImageView[] mapImages;
    public static List<Integer> mapTiles;

    // Other classes objects
    public static FileManager fileManager;
    public static IOManager ioManager;

}
