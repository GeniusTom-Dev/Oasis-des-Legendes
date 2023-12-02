package fr.tmm.save;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.ZooMaster;
import fr.tmm.modele.creature.species.*;
import fr.tmm.modele.enclosure.Aquarium;
import fr.tmm.modele.enclosure.Aviary;
import fr.tmm.modele.enclosure.Enclosure;
import fr.tmm.modele.creature.Creature;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class ExportSave {

    public static void main(String[] args) throws FileNotFoundException, JSONException {
        Zoo zoo = Zoo.getInstance();
        zoo.setZooMaster( new ZooMaster("Julo", "m", 10, 10, 19));
        zoo.setName("Zoo de Test");
        Enclosure enclos1 = new Enclosure("Enclos 1", 50, 5);
        Aquarium aquarium1 = new Aquarium("Aquarium 1", 50, 5, 20, 4);
        Aviary voiliere1 = new Aviary("Voiliere 1", 50, 7);
        enclos1.addCreature(new Unicorn("Licorne 1", "m", 10, 10, 10));
        enclos1.addCreature(new Unicorn("Licorne 2", "m", 10, 10, 10));
        enclos1.addCreature(new Unicorn("Licorne 3", "m", 10, 10, 10));
        aquarium1.addCreature(new Megalodon("Magalodon 1", "m", 10, 10, 10));
        aquarium1.addCreature(new Megalodon("Magalodon 2", "m", 10, 10, 10));
        voiliere1.addCreature(new Phenix("Phenix 1", "m", 10, 10, 10));
        zoo.addAnEnclosure(enclos1);
        zoo.addAnEnclosure(aquarium1);
        zoo.addAnEnclosure(voiliere1);
        exportSave(zoo,null);
    }

    public static void exportSave(Zoo zoo, String outputPath) throws FileNotFoundException, JSONException {
        String path = outputPath;
        if (outputPath == null) {
            path = "src/main/java/fr/tmm/save/save.json";
        }
        try (OutputStream fos = new FileOutputStream(path)) {
            JSONObject fichier = new JSONObject();

            // Zoo Master Details
            fichier.put("zooMaster", getZooMasterDetail(zoo));

            // Zoo Details
            JSONObject zooDetails = new JSONObject();
            zooDetails.put("name", zoo.getName());
            zooDetails.put("nombre max d'enclos", zoo.getNbMaxEnclosure());

            // Enclos Array
            JSONArray enclosArray = new JSONArray();
            for (Enclosure enclos : zoo.getEnclosures()) {
                enclosArray.put(getEnclosureDetail(enclos));
            }
            zooDetails.put("enclos", enclosArray);
            fichier.put("zoo", zooDetails);

            fos.write(fichier.toString(2).getBytes()); // Utilisez toString(2) pour une indentation lisible
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getZooMasterDetail(Zoo zoo) throws JSONException {
        JSONObject zooMasterDetails = new JSONObject();
        zooMasterDetails.put("height", zoo.getZooMaster().getHeight());
        zooMasterDetails.put("weight", zoo.getZooMaster().getWeight());
        zooMasterDetails.put("age", zoo.getZooMaster().getAge());
        zooMasterDetails.put("sex", zoo.getZooMaster().getSex());
        zooMasterDetails.put("name", zoo.getZooMaster().getName());
        return zooMasterDetails;
    }

    private static JSONObject getEnclosureDetail(Enclosure enclos) throws JSONException {
        JSONObject encloDetails = new JSONObject();
        encloDetails.put("superficie", enclos.getSurfaceArea());
        encloDetails.put("type", enclos.getClass().getSimpleName());
        encloDetails.put("nom", enclos.getName());
        encloDetails.put("maxCapacity", enclos.getMaxCapacity());
        encloDetails.put("cleanliness", enclos.getCleanlinessDegree());
        JSONArray creaturesArray = new JSONArray();
        for (Creature creature : enclos.getCreaturesPresent()) {
            creaturesArray.put(getCreatureDetail(creature));
        }
        encloDetails.put("creatures", creaturesArray);
        return encloDetails;
    }

    private static JSONObject getCreatureDetail(Creature creature) throws JSONException {
        JSONObject creatureDetail = new JSONObject();
        creatureDetail.put("name", creature.getName());
        creatureDetail.put("type", creature.getType());
        creatureDetail.put("sex", creature.getSex());
        creatureDetail.put("height", creature.getHeight());
        creatureDetail.put("weight", creature.getWeight());
        creatureDetail.put("age", creature.getAge());
        creatureDetail.put("satiety", creature.getSatiety());
        creatureDetail.put("energy", creature.getEnergy());
        creatureDetail.put("health", creature.getHealth());
        return creatureDetail;
    }
}