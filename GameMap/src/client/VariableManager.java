package client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.List;

public class VariableManager {

    // Window Constants
    static final int WINDOW_HEIGHT = 600;
    static final int WINDOW_WIDTH = 800;

    // Tile Constants
    static final int TILE_WIDTH = 32;
    static final int TILE_HEIGHT = TILE_WIDTH;

    // Screen constants
    static final int TOTAL_HOUSE_NUMBER = 1600;
    static final int ROW_NUMBERS = 40;

    // Camera constants
    static final int CAMERA_PADDING = 10;
    static int cordinateXTransform =0;
    static int cordinateYTransform =0;


    // Map variables
    static JsonManager mapJSON;
    static ImageView[] mapImages;
    static List<Integer> mapTiles;

    // Other classes objects
    static FileManager fileManager;


}
