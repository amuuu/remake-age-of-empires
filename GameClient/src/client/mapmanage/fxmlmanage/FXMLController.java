package client.mapmanage.fxmlmanage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

    // FXML Buttons
    @FXML static Button barracks = new Button();
    @FXML static Button seaport = new Button();
    @FXML static Button farm = new Button();
    @FXML static Button goldmine = new Button();
    @FXML static Button woodworkshop = new Button();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        barracks.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
//                barracksButtonClicked = true;
//                System.out.println(ali+"ali");
//                System.out.println(barracksButtonClicked);
            }
        });
        seaport.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
//                seaportButtonClicked = true;
            }
        });
        farm.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
//                farmButtonClicked = true;
            }
        });
        goldmine.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
//                goldmineButtonClicked = true;
            }
        });
        woodworkshop.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
//                woodworkshopButtonClicked = true;
            }
        });
    }
}
