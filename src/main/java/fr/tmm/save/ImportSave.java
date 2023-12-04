package fr.tmm.save;

import fr.tmm.modele.Zoo;
import org.json.JSONException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;

public class ImportSave {

    public static void main(String[] args) throws FileNotFoundException {
        // Lire le fichier
        InputStream fis = new FileInputStream("src/main/java/fr/tmm/save/save.json");
        JsonReader reader = Json.createReader(fis);
        JsonObject test = reader.readObject();
        reader.close();
        System.out.println("Id : " + test.getInt("id"));
    }

    /**
     * Create a zoo from a json file
     * @param inputPath : path of the json file, if null, the path will be the default one
     * @throws FileNotFoundException
     * @throws JSONException
     */
    public static void importSave(String inputPath) throws FileNotFoundException, JSONException {
        String path = inputPath;
        if (inputPath == null) {
            path = "src/main/java/fr/tmm/save/save.json";
        }
        // TODO
    }
}
