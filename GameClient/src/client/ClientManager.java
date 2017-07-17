package client;

import client.iomanage.MapSender;
import client.jsonmanage.JsonManager;
import client.mapmanage.MoveCamera;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;

import static client.mapmanage.MapMethodManager.*;
import static client.mapmanage.variablerepo.Consts.*;
import static client.mapmanage.variablerepo.Variables.*;

public class ClientManager extends Application{

    private static Group window;
    public static Group photos;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mapmanage/fxmlmanage/BottomPanel.fxml"));
        window = new Group();
        photos = new Group();
        window.getChildren().add(photos);
        window.getChildren().add(root);
        MoveCamera move_c = new MoveCamera(photos);
        move_c.start();
        Scene scene = new Scene(window, WINDOW_WIDTH, WINDOW_HEIGHT);

        makeManagers();
        initPrintMap();

        for(int i=0; i<TOTAL_HOUSE_NUMBER;i++) photos.getChildren().add(mapImages[i]);

        ioManager.start();

        scene.setOnMouseMoved(this::mouseMovement);
        scene.setOnMouseClicked(this::mouseClick);
        window.setOnScroll(this::mouseScroll);

        primaryStage.setTitle("Age of Empires");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void mouseScroll(ScrollEvent scrollEvent) {
        double zoomFactor = 1;
            double deltaY = scrollEvent.getDeltaY();
            if (deltaY > 0)
                zoomFactor+=0.15;
            if (deltaY<0 && zoomFactor>1)
                zoomFactor-=0.15;
            photos.setScaleY(zoomFactor);
            photos.setScaleX(zoomFactor);

    }
    private void mouseMovement(MouseEvent mouseEvent){
            int mouseMovePace = 5;

            int mouseX = (int)mouseEvent.getSceneX();
            int mouseY = (int)mouseEvent.getSceneY();

            window.getScaleX();
            int end_x = -432;
            int end_y = -766;
            int end_xy = 0;
            if (mouseX > WINDOW_WIDTH - CAMERA_PADDING)
            {
                if (cordinateXTransform > end_x)
                {
                    MoveCamera.right= true;
                }

            }
            else
                MoveCamera.right = false;

            if (mouseX < CAMERA_PADDING) {
                if (cordinateXTransform < end_xy){
                    MoveCamera.left = true;
                }
            }
            else
                MoveCamera.left = false;
                /*  .. up and down ..  */
            if (mouseY > WINDOW_HEIGHT - 110 && mouseY < WINDOW_HEIGHT - 101) {
                if (cordinateYTransform > end_y)
                    MoveCamera.down = true;
            }
            else
                MoveCamera.down = false;
            if (mouseY < CAMERA_PADDING) {
                if (cordinateYTransform < end_xy)
                    MoveCamera.up = true;
            }
            else
                MoveCamera.up = false;
                /*  ....  */
            if (mouseX > WINDOW_WIDTH - CAMERA_PADDING && mouseY > WINDOW_HEIGHT - 110 && mouseY < WINDOW_HEIGHT - 100) {
                if (cordinateXTransform > end_x) {
                    if (cordinateYTransform > end_y) MoveCamera.Southeast = true;
                }
            }
            else
                MoveCamera.Southeast = false;
            if (mouseX < CAMERA_PADDING && mouseY > WINDOW_HEIGHT - 110 && mouseY < WINDOW_HEIGHT - 100) {
                if (cordinateXTransform < end_xy) {
                    if (cordinateYTransform > end_y) MoveCamera.Southwest = true;
                }
            }
            else
                MoveCamera.Southwest =false;

            if (mouseX < CAMERA_PADDING && mouseY < CAMERA_PADDING) {
                if (cordinateXTransform < end_xy) {
                    if (cordinateYTransform < end_xy) MoveCamera.Northwest = true;
                }
            }
            else
                MoveCamera.Northwest = false;
            if (mouseX > WINDOW_WIDTH - CAMERA_PADDING && mouseY < CAMERA_PADDING) {
                if (cordinateXTransform > end_x)
                    if (cordinateYTransform < end_xy) MoveCamera.Northeast =true;
            }

    }
    private void mouseClick(MouseEvent mouseEvent) {
        int house_number;
        int mouseX = (int)mouseEvent.getSceneX() - cordinateXTransform;
        int mouseY = (int)mouseEvent.getSceneY() - cordinateYTransform;

        if (mouseY+cordinateYTransform < WINDOW_HEIGHT - 100) {
//                    if(barracksButtonClicked) {
            house_number = returnHouseNumber(mouseX, mouseY);
            System.out.println("house number is " + house_number);
            JsonManager.setHouse(house_number, 38);

//            try {
//                MapSender.sendCommand("127.0.0.1","build "+38+" in "+house_number+"");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

//
        }
    }




    public static void main(String[] args) {
        launch(args);
    }
}
 