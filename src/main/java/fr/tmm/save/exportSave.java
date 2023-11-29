package fr.tmm.save;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.species.Dragon;
import fr.tmm.modele.creature.species.Kraken;
import fr.tmm.modele.enclosure.Enclosure;
import fr.tmm.modele.creature.Creature;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class exportSave {

    public static void main(String[] args) throws FileNotFoundException, JSONException {
        Zoo zoo = Zoo.getInstance();
        zoo.temporaryInit();
        Enclosure enclo = new Enclosure("enclo", 45, 5);
        Enclosure enclo2 = new Enclosure("enclo2", 60, 4);
        Kraken kraken1 = new Kraken("krak", "male", 20, 20, 5);
        Kraken kraken2 = new Kraken("kraken2", "femelle", 20, 20, 7);
        Dragon dragon = new Dragon("kllfh", "male", 20, 20, 20);
        enclo.addCreature(dragon);
        enclo2.addCreature(kraken1);
        enclo2.addCreature(kraken2);
        ArrayList<Enclosure> enclos = new ArrayList<>();
        enclos.add(enclo);
        enclos.add(enclo2);
        zoo.setEnclosures(enclos);
        export(zoo);
    }

    public static void export(Zoo zoo) throws FileNotFoundException, JSONException {
        try (OutputStream fos = new FileOutputStream("src/main/java/fr/tmm/save/save.json")) {
            JSONObject fichier = new JSONObject();

            // Zoo Master Details
            JSONObject zooMasterDetails = new JSONObject();
            zooMasterDetails.put("name", zoo.getZooMaster().getName());
            zooMasterDetails.put("sex", zoo.getZooMaster().getSex());
            fichier.put("zooMaster", zooMasterDetails);

            // Zoo Details
            JSONObject zooDetails = new JSONObject();
            zooDetails.put("name", zoo.getName());
            zooDetails.put("nombre max d'enclos", zoo.getNbMaxEnclosure());

            // Enclos Array
            JSONArray enclosArray = new JSONArray();
            for (Enclosure enclos : zoo.getEnclosures()) {
                JSONObject encloDetails = new JSONObject();
                encloDetails.put("type", enclos.getClass().getSimpleName());
                encloDetails.put("nom", enclos.getName());
                encloDetails.put("superficie", enclos.getSurfaceArea());

                // Creatures Array
                JSONArray creaturesArray = new JSONArray();
                for (Creature creature : enclos.getCreaturesPresent()) {
                    JSONObject creatureDetail = new JSONObject();
                    creatureDetail.put("type", creature.getType());
                    creatureDetail.put("nom", creature.getName());
                    creatureDetail.put("satiete", creature.getSatiety());
                    creatureDetail.put("energie", creature.getEnergy());
                    creaturesArray.put(creatureDetail);
                }

                encloDetails.put("creatures", creaturesArray);
                enclosArray.put(encloDetails);
            }

            zooDetails.put("enclos", enclosArray);
            fichier.put("zoo", zooDetails);

            fos.write(fichier.toString(2).getBytes()); // Utilisez toString(2) pour une indentation lisible
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}