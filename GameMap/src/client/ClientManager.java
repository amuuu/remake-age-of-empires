package client;

import client.iomanage.IOManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;

import static client.mapmanage.MapMethodManager.initPrintMap;
import static client.mapmanage.MapMethodManager.returnHouseNumber;
import static client.variablerepo.VariableManager.*;

public class ClientManager extends Application{

    static Parent root;
    static Group window;
    static Group photos;
    static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("fxmlmanage/BottomPanel.fxml"));
        window = new Group();
        photos = new Group();
        window.getChildren().add(photos);
        window.getChildren().add(root);
        scene = new Scene(window,WINDOW_WIDTH,WINDOW_HEIGHT);

        initPrintMap();
        for(int i=0; i<TOTAL_HOUSE_NUMBER;i++)
            photos.getChildren().add(mapImages[i]);


        ioManager = new IOManager("192.168.100.6");
        if(ioManager!=null) System.out.println("yes");
        ioManager.start();
        System.out.println(ioManager.isAlive());

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
//                        barracksButtonClicked = false;
//                    }
                }
            }
        });
        window.setOnScroll(new EventHandler<ScrollEvent>() {
            double zoomFactor = 1;
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

        primaryStage.setTitle("Age of Empires AMUKIALI");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
 