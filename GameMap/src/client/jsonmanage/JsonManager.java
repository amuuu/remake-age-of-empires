package client.jsonmanage;

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

    JsonArray objs;

    public void readMap() {
        convertFileToJSON ("src/json/map.json");
    }

    public void convertFileToJSON (String fileName){
        try {
            JsonParser parser = new JsonParser();
            JsonArray data = parser.parse(new FileReader(fileName))
                    .getAsJsonObject().getAsJsonArray("layers").get(0)
                    .getAsJsonObject().getAsJsonArray("data");

            this.objs = data;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Integer> objectList(){
        Type listType = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> yourList = new Gson().fromJson(objs, listType);
        return yourList;
    }

    public void setHouse(int houseNumber, int houseKind){
        houseNumber = houseNumber-2-41;
        for(int i=0;i<3*40;i+=40) {
            for(int j=0;j<3;j++) {
                objs.set(houseNumber+j+i, new JsonPrimitive(houseKind));
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
            for(int i=0; i<objs.size();i++) {
                jsonWriter.value(objs.get(i).getAsInt());
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
