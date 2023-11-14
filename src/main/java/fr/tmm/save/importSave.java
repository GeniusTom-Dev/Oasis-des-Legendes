package fr.tmm.save;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;

public class importSave {

    public static void main(String[] args) throws FileNotFoundException {
        // Lire le fichier
        InputStream fis = new FileInputStream("src/main/java/fr/tmm/save/save.json");
        JsonReader reader = Json.createReader(fis);
        JsonObject test = reader.readObject();
        reader.close();
        System.out.println("Id : " + test.getInt("id"));
    }
}
