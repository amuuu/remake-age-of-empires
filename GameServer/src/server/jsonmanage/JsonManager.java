package server.jsonmanage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


public class JsonManager {

    private static JsonArray mapObjects;

    // reads a json map file
    public void readJsonMap(String fileName){
        try {
            JsonParser parser = new JsonParser();
            JsonArray data = parser.parse(new FileReader(fileName))
                    .getAsJsonObject().getAsJsonArray("layers").get(0)
                    .getAsJsonObject().getAsJsonArray("data");

            this.mapObjects = data;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // converts jsonArray to usable arrayList
    public List<Integer> objectList(){
        Type listType = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> yourList = new Gson().fromJson(mapObjects, listType);
        return yourList;
    }

    // sets a new building in json map file
    public static void setHouse(int houseNumber, int houseKind){
        houseNumber = houseNumber-2-41;
        for(int i=0;i<3*40;i+=40) {
            for(int j=0;j<3;j++) {
                mapObjects.set(houseNumber+j+i, new JsonPrimitive(houseKind));
            }
        }

        try {
            FileWriter filewriter = new FileWriter("src/json/map.json");
            JsonWriter jsonWriter = new JsonWriter(filewriter);
            jsonWriter.beginObject();
            jsonWriter.name("layers");
            jsonWriter.beginArray();
            jsonWriter.beginObject();
            jsonWriter.name("data");
            jsonWriter.beginArray();
            for(int i = 0; i< mapObjects.size(); i++) {
                jsonWriter.value(mapObjects.get(i).getAsInt());
            }
            jsonWriter.endArray();
            jsonWriter.endObject();
            jsonWriter.endArray();
            jsonWriter.endObject();
            jsonWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}