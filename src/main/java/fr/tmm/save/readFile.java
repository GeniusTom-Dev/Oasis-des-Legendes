package fr.tmm.save;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class readFile {

    public static JsonObject readJSON(String path) throws FileNotFoundException {
        try {
            InputStream fis = new FileInputStream(path);
            JsonReader reader = Json.createReader(fis);
            JsonObject json = reader.readObject();
            reader.close();
            return json;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }
    }
}
