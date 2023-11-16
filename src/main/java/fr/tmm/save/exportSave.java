package fr.tmm.save;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.species.Dragon;
import fr.tmm.modele.enclosure.Enclosure;
import fr.tmm.modele.creature.Creature;

import javax.json.Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.json.stream.JsonGenerator;
import java.io.*;
import java.util.ArrayList;

public class exportSave {

    public static void main(String[] args) throws FileNotFoundException, JSONException {
        // écrire dans le fichier
        /*OutputStream fos = new FileOutputStream("src/main/java/fr/tmm/save/save.json");
        JsonGenerator jsonGenerator = Json.createGenerator(fos);
        jsonGenerator.writeStartObject();
        jsonGenerator.write("id", 1);
        jsonGenerator.writeEnd();
        jsonGenerator.close();*/
        Zoo zoo = new Zoo();
        Enclosure enclo = new Enclosure("enclo", 45, 5);
        Dragon dragon = new Dragon("kllfh", "male", 20, 20, 20);
        enclo.ajouterCreature(dragon);
        ArrayList<Enclosure> enclos = new ArrayList<>();
        enclos.add(enclo);
        zoo.setEnclosure(enclos);
        export(zoo);
    }

    public static void export(Zoo zoo) throws FileNotFoundException, JSONException {
        OutputStream fos = new FileOutputStream("src/main/java/fr/tmm/save/save.json");
        JsonGenerator jsonGenerator = Json.createGenerator(fos);
        JSONObject fichier = new JSONObject();
        JSONArray encloss = new JSONArray();
        for (Enclosure enclos : zoo.getEnclosure()) {
            JSONObject encloDetails = new JSONObject();
            encloDetails.put("name", enclos.getName());
            JSONArray creatures = new JSONArray();
            for (Creature creature : enclos.getCreaturesPresent()) {
                JSONObject creatureDetail = new JSONObject();
                creatureDetail.put("name", creature.getName());
                //creatures.add(creatureDetail);
            }
            encloDetails.put("creatures", creatures);
            //encloss.add(encloDetails);
        }
        fichier.put("enclos", encloss);
    }
}
