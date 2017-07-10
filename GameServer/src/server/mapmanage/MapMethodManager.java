package server.mapmanage;

import static server.mapmanage.variablerepo.Consts.*;

public class MapMethodManager {

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
    public static int returnHouseNumber(int x, int y){
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


}
