package fr.tmm.save;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.*;

public class exportSave {

    public static void main(String[] args) throws FileNotFoundException {
        // écrire dans le fichier
        OutputStream fos = new FileOutputStream("src/main/java/fr/tmm/save/save.json");
        JsonGenerator jsonGenerator = Json.createGenerator(fos);
        jsonGenerator.writeStartObject();
        jsonGenerator.write("id", 1);
        jsonGenerator.writeEnd();
        jsonGenerator.close();
    }
}
