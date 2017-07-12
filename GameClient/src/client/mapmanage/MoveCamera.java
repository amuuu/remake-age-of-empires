package client.mapmanage;

import client.ClientManager;
import client.mapmanage.variablerepo.Variables;
import javafx.scene.Group;

public class MoveCamera extends Thread
{

    public static boolean up = false;
    public static boolean left = false;
    public static boolean down = false;
    public static boolean right = false;
    public static boolean Southeast = false;
    public static boolean Southwest = false;
    public static boolean Northeast = false;
    public static boolean Northwest = false;

    Group get ;
    public MoveCamera(Group photo)
    {
        this.get = photo;
    }

    @Override
    public void run ()
    {
        while (true)
        {
            if(MoveCamera.right)
            {
                Variables.cordinateXTransform -= 5;
                get.setTranslateX(Variables.cordinateXTransform);
                if(Variables.cordinateXTransform < -432){MoveCamera.right = false;}
            }
            if(MoveCamera.left)
            {
                Variables.cordinateXTransform += 5;
                get.setTranslateX(Variables.cordinateXTransform);
                if(Variables.cordinateXTransform > -5){MoveCamera.left = false;}
            }
            if(MoveCamera.down)
            {
                Variables.cordinateYTransform -= 5;
                get.setTranslateY(Variables.cordinateYTransform);
                if(Variables.cordinateYTransform < -766){MoveCamera.down = false;}
            }
            if(MoveCamera.up)
            {
                Variables.cordinateYTransform += 5;
                get.setTranslateY(Variables.cordinateYTransform);
                if(Variables.cordinateYTransform > -5){MoveCamera.up = false;}
            }
            if(MoveCamera.Southeast)
            {
                Variables.cordinateYTransform -= 5;
                get.setTranslateY(Variables.cordinateYTransform);
                Variables.cordinateXTransform -= 5;
                get.setTranslateX(Variables.cordinateXTransform);
                if(Variables.cordinateYTransform < -766 || Variables.cordinateXTransform < -432){MoveCamera.Southeast = false;}
            }
            if(MoveCamera.Southwest)
            {
                Variables.cordinateYTransform -= 5;
                get.setTranslateY(Variables.cordinateYTransform);
                Variables.cordinateXTransform += 5;
                get.setTranslateX(Variables.cordinateXTransform);
                if(Variables.cordinateYTransform < -766 || Variables.cordinateXTransform > -15){MoveCamera.Southwest = false;}
            }
            if(MoveCamera.Northwest)
            {
                Variables.cordinateYTransform += 5;
                get.setTranslateY(Variables.cordinateYTransform);
                Variables.cordinateXTransform += 5;
                get.setTranslateX(Variables.cordinateXTransform);
                if(Variables.cordinateYTransform > -5 || Variables.cordinateXTransform > -15){MoveCamera.Northwest = false;}
            }
            if(MoveCamera.Northeast)
            {
                Variables.cordinateYTransform += 5;
                get.setTranslateY(Variables.cordinateYTransform);
                Variables.cordinateXTransform -= 5;
                get.setTranslateX(Variables.cordinateXTransform);
                if(Variables.cordinateYTransform > -5 || Variables.cordinateXTransform < -433){MoveCamera.Northeast = false;}
            }
            try
            {
                Thread.sleep(30);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
