package fr.tmm.save;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class createSave {

    public static void main(String[] args) throws FileNotFoundException {
        OutputStream fos = new FileOutputStream("src/main/java/fr/tmm/save/save.txt");
        JsonGenerator jsonGenerator = Json.createGenerator(fos);
        jsonGenerator.writeStartObject();
        jsonGenerator.write("id", 1);
        jsonGenerator.writeEnd();
        jsonGenerator.close();
    }
}
