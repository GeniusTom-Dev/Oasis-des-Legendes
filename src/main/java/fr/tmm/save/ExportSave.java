package fr.tmm.save;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.ZooMaster;
import fr.tmm.modele.creature.reproduction.Female;
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
        Aquarium aquarium1 = new Aquarium("Aquarium 1", 50, 5);
        Aviary voiliere1 = new Aviary("Voiliere 1", 50, 7);
        enclos1.addCreature(new Unicorn("Licorne 1", "m", 10, 10, 10));
        enclos1.addCreature(new Unicorn("Licorne 2", "m", 10, 10, 10));
        enclos1.addCreature(new Unicorn("Licorne 3", "m", 10, 10, 10));
        aquarium1.addCreature(new Megalodon("Magalodon 1", "m", 10, 10, 10));
        aquarium1.addCreature(new Megalodon("Magalodon 2", "m", 10, 10, 10));
        voiliere1.addCreature(new Phoenix("Phenix 1", "m", 10, 10, 10));
        zoo.addAnEnclosure(enclos1);
        zoo.addAnEnclosure(aquarium1);
        zoo.addAnEnclosure(voiliere1);
        exportSave(null);
    }

    /**
     * Create a json file from a zoo
     * @param outputPath : path of the json, if null, the path will be the default one
     * @throws FileNotFoundException
     * @throws JSONException
     */
    public static void exportSave(String outputPath) throws FileNotFoundException, JSONException {
        Zoo zoo = Zoo.getInstance();
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

    /**
     * Return a JSONObject with the current zoo master detail
     * @param zoo : current zoo
     * @return a JSONObject with the current zoo master detail
     * @throws JSONException
     */
    private static JSONObject getZooMasterDetail(Zoo zoo) throws JSONException {
        JSONObject zooMasterDetails = new JSONObject();
        zooMasterDetails.put("height", zoo.getZooMaster().getHeight());
        zooMasterDetails.put("weight", zoo.getZooMaster().getWeight());
        zooMasterDetails.put("age", zoo.getZooMaster().getAge());
        zooMasterDetails.put("sex", zoo.getZooMaster().getSex().toString());
        zooMasterDetails.put("name", zoo.getZooMaster().getName());
        return zooMasterDetails;
    }

    /**
     * Return a JSONObject with an enclosure details
     * @param enclos : enclosure to get the details from
     * @return a JSONObject with an enclosure details
     * @throws JSONException
     */
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

    /**
     * Return a JSONObject with a creature detail
     * @param creature : creature to get the detail from
     * @return a JSONObject with a creature detail
     * @throws JSONException
     */
    private static JSONObject getCreatureDetail(Creature creature) throws JSONException {
        JSONObject creatureDetail = new JSONObject();
        creatureDetail.put("name", creature.getName());
        creatureDetail.put("type", creature.getType());
        creatureDetail.put("sex", creature.getSex().toString());
        creatureDetail.put("height", creature.getHeight());
        creatureDetail.put("weight", creature.getWeight());
        creatureDetail.put("age", creature.getAge());
        creatureDetail.put("satiety", creature.getSatiety());
        creatureDetail.put("energy", creature.getEnergy());
        creatureDetail.put("health", creature.getHealth());
        creatureDetail.put("isSick", creature.isSick());
        creatureDetail.put("isAsleep", creature.isAsleep());
        if (creature.getSex().toString() == "Female") {
            creatureDetail.put("isPregnant", ((Female) creature.getSex()).isPregnant());
            creatureDetail.put("gestationCounter", ((Female) creature.getSex()).getGestationCounter());
        }
        return creatureDetail;
    }
}