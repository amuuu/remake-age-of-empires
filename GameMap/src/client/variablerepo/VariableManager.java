package client.variablerepo;

import client.jsonmanage.JsonManager;
import client.filemanage.FileManager;
import client.iomanage.IOManager;
import javafx.scene.image.ImageView;

import java.util.List;

public class VariableManager {

    // Window Constants
    public static final int WINDOW_HEIGHT = 600;
    public static final int WINDOW_WIDTH = 800;

    // Tile Constants
    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = TILE_WIDTH;

    // Screen constants
    public static final int TOTAL_HOUSE_NUMBER = 1600;
    public static final int ROW_NUMBERS = 40;

    // Camera constants
    public static final int CAMERA_PADDING = 10;
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
