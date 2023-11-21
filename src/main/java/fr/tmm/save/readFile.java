package fr.tmm.save;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class readFile {

    public static void readJSON(String path) throws FileNotFoundException {
        JsonObject json = null;
        try {
            InputStream fis = new FileInputStream(path);
            JsonReader reader = Json.createReader(fis);
            json = reader.readObject();
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(json.getJsonObject("dragon").getInt("energy"));
    }
}
