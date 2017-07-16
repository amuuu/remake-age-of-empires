package client.iomanage;


import static client.ClientManager.photos;
import static client.iomanage.IOManager.buildCommands;
import static client.jsonmanage.JsonManager.setHouse;
import static client.mapmanage.MapMethodManager.initPrintMap;
import static client.mapmanage.variablerepo.Consts.TOTAL_HOUSE_NUMBER;
import static client.mapmanage.variablerepo.Variables.mapImages;

public class CommandApplier{


    void applyBuildCommand(int index) throws Exception{
        setHouse(buildCommands.get(index)[1],buildCommands.get(index)[0]); //where, what

    }

    void applyDestroyCommand(int index) throws Exception{
//        setHouse(buildCommands.get(index)[2],buildCommands.get(index)[1]); //where, what
    }

    void applyMoveCommand(int index) throws Exception{

    }

}
