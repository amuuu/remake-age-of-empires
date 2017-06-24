package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientManager extends Application implements Initializable{

    static final int WINDOW_HEIGHT = 600;
    static final int WINDOW_WIDTH = 800;

    static final int TILE_WIDTH = 32;
    static final int TILE_HEIGHT = TILE_WIDTH;

    static final int TOTAL_HOUSE_NUMBER = 1600;
    static final int ROW_NUMBERS = 40;

    static final int CAMERA_PADDING = 10;

    public  int ali  =0 ;
    private boolean barracksButtonClicked = false;
    private boolean farmButtonClicked = false;
    private boolean goldmineButtonClicked = false;
    private boolean woodworkshopButtonClicked = false;
    private boolean seaportButtonClicked = false;

    static private JsonManager mapJSON;
    private static ImageView [] mapImages;
    private static List<Integer> mapTiles;

    int cordinateXTransform =0;
    int cordinateYTransform =0;

    @FXML
    private Button barracks = new Button();
    @FXML
    private Button seaport = new Button();
    @FXML
    private Button farm = new Button();
    @FXML
    private Button goldmine = new Button();
    @FXML
    private Button woodworkshop = new Button();

    static FileManager fileManager;

    private static void readJSONMap() {
        mapJSON = new JsonManager();
        mapJSON.readMap();
        mapTiles = mapJSON.objectList();
    }
    private static void makeMapOutline(){
        mapImages = new ImageView[TOTAL_HOUSE_NUMBER];
        for(int i = 0; i < TOTAL_HOUSE_NUMBER; i++) {
//            System.out.println(mapTiles.get(i));
            for(int j=0;j<58;j++) {
                if (mapTiles.get(i)==(j+1)) {
                    mapImages[i] = new ImageView(fileManager.id[j]);
//                    System.out.println("yes");
                    break;
                }
                else {
                    mapImages[i] = new ImageView(fileManager.id[29]);
                }
            }
            mapImages[i].setX(returnColumn(i+2)*TILE_WIDTH);
            mapImages[i].setY(returnRow(i+2)*TILE_HEIGHT);
        }
    }
    private static void initPrintMap() throws Exception {
        fileManager = new FileManager();
        readJSONMap();
        makeMapOutline();
    }
    private static int returnColumn (int homeNumber){
        int columnNumber=0;
        for (int i = 0; i< TOTAL_HOUSE_NUMBER; i++){
            if(i==homeNumber) break;
            else {
                if (i % (ROW_NUMBERS) == 1) {
                    columnNumber = 0;
                } else {
                    columnNumber++;
                }
            }
        }
        return columnNumber;
    }
    private static int returnRow (int homeNumber){
        int rowNumber=0;
        for (int i = 0; i< TOTAL_HOUSE_NUMBER; i++){
            if(i==homeNumber) break;
            else{
                if(i%(ROW_NUMBERS)==1) {
                    rowNumber++;
                }
                else{}
            }
        }
        return --rowNumber;
    }
    public static int returnHouseNumber (int x, int y){
        int houseNumber = -1;
        for(int i=0; i<TOTAL_HOUSE_NUMBER; i++){
            if(x>=returnColumn(i)*TILE_WIDTH && x<returnColumn(i)*TILE_WIDTH+TILE_WIDTH
            && y>=returnRow(i)*TILE_HEIGHT && y<returnRow(i)*TILE_HEIGHT+TILE_HEIGHT) {
                houseNumber = i;
                break;
            }
        }
        return houseNumber;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        barracks.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                barracksButtonClicked = true;
                ali =1 ;
//                System.out.println(ali+"ali");
//                System.out.println(barracksButtonClicked);
            }
        });
        seaport.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                seaportButtonClicked = true;
            }
        });
        farm.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                farmButtonClicked = true;
            }
        });
        goldmine.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                goldmineButtonClicked = true;
            }
        });
        woodworkshop.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                woodworkshopButtonClicked = true;
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Age of Empires");
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("BottomPanel.fxml"));
        Group window = new Group();
        Group photos = new Group();
        window.getChildren().add(photos);
        window.getChildren().add(root);
//        System.out.println(ali);
        Scene scene = new Scene(window,WINDOW_WIDTH,WINDOW_HEIGHT);

        initPrintMap();
        for(int i=0; i<TOTAL_HOUSE_NUMBER;i++){
            photos.getChildren().add(mapImages[i]);
        }

        scene.addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>(){

            int mouseMovePace = 5;
            @Override
            public void handle(MouseEvent mouseEvent) {

                int mouseX = (int)mouseEvent.getSceneX();
                int mouseY = (int)mouseEvent.getSceneY();

                mouseMover(mouseX, mouseY);
            }

            public void mouseMover(int mouseX, int mouseY) {
                window.getScaleX();
                int end_x = -432;
                int end_y = -766;
                int end_xy = 0;
                /* ...  left and right ...  */
                if (mouseX > WINDOW_WIDTH - CAMERA_PADDING) {
                    if (cordinateXTransform > end_x) cordinateXTransform -= mouseMovePace;
                }
                if (mouseX < CAMERA_PADDING) {
                    if (cordinateXTransform < end_xy) cordinateXTransform += mouseMovePace;
                }
                /*  .. up and down ..  */
                if (mouseY > WINDOW_HEIGHT - 110 && mouseY < WINDOW_HEIGHT - 101) {
                    if (cordinateYTransform > end_y) cordinateYTransform -= mouseMovePace;
                }
                if (mouseY < CAMERA_PADDING) {
                    if (cordinateYTransform < end_xy) cordinateYTransform += mouseMovePace;
                }
                /*  ....  */
                if (mouseX > WINDOW_WIDTH - CAMERA_PADDING && mouseY > WINDOW_HEIGHT - 110 && mouseY < WINDOW_HEIGHT - 100) {
                    if (cordinateXTransform > end_x) {
                        cordinateXTransform -= mouseMovePace;
                        if (cordinateYTransform > end_y) cordinateYTransform -= mouseMovePace;
                    }
                }
                if (mouseX < CAMERA_PADDING && mouseY > WINDOW_HEIGHT - 110 && mouseY < WINDOW_HEIGHT - 100) {
                    if (cordinateXTransform < end_xy) {
                        cordinateXTransform += mouseMovePace;
                        if (cordinateYTransform > end_y) cordinateYTransform -= mouseMovePace;
                    }
                }
                if (mouseX < CAMERA_PADDING && mouseY < CAMERA_PADDING) {
                    if (cordinateXTransform < end_xy) {
                        cordinateXTransform += mouseMovePace;
                        if (cordinateYTransform < end_xy) cordinateYTransform += mouseMovePace;
                    }
                }
                if (mouseX > WINDOW_WIDTH - CAMERA_PADDING && mouseY < CAMERA_PADDING) {
                    if (cordinateXTransform > end_x)
                        cordinateXTransform -= mouseMovePace;
                    if (cordinateYTransform < end_xy) cordinateYTransform += mouseMovePace;
                }
                /*  ....  */
                photos.setLayoutX(cordinateXTransform);
                photos.setLayoutY(cordinateYTransform);
            }

        });

        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                int house_number;
                System.out.println("cordinateXTransform is " + cordinateXTransform);
                System.out.println("cordinateYTransform is " + cordinateYTransform);

                int mouseX = (int)mouseEvent.getSceneX() - cordinateXTransform;
                int mouseY = (int)mouseEvent.getSceneY() - cordinateYTransform;

                if (mouseY+cordinateYTransform < WINDOW_HEIGHT - 100) {
//                    if(barracksButtonClicked) {
                        house_number = returnHouseNumber(mouseX, mouseY);
                        System.out.println("house number is " + house_number);
                        mapJSON.setHouse(house_number, 38);

                        try {
                            initPrintMap();
                            for (int i = 0; i < TOTAL_HOUSE_NUMBER; i++)
                                photos.getChildren().add(mapImages[i]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        barracksButtonClicked = false;
//                    }
                }
            }
        });

        window.setOnScroll(new EventHandler<ScrollEvent>() {
            double zoomFactor = 1;

            public double getZoomFactor() {
                return zoomFactor;
            }

            @Override
            public void handle(ScrollEvent event) {
                double deltaY = event.getDeltaY();
                if (deltaY>0 && zoomFactor<2)
                    zoomFactor+=0.15;
                if (deltaY<0 && zoomFactor>1)
                    zoomFactor-=0.15;
                photos.setScaleY(zoomFactor);
                photos.setScaleX(zoomFactor);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
 