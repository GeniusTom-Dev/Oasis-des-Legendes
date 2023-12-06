package fr.tmm.save;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.ZooMaster;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.reproduction.Female;
import fr.tmm.modele.creature.species.*;
import fr.tmm.modele.enclosure.Aquarium;
import fr.tmm.modele.enclosure.Aviary;
import fr.tmm.modele.enclosure.Enclosure;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;

public class ImportSave {

    public static void main(String[] args) {
        importSave(null);
    }

    /**
     * Import data from a JSON file and update the application state
     * @param inputPath : path of the JSON file, if null, the path will be the default one
     */
    public static void importSave(String inputPath) {
        String path = inputPath;
        if (inputPath == null) {
            path = "src/main/java/fr/tmm/save/save.json";
        }
        try (FileInputStream fis = new FileInputStream(path)) {
            byte[] data = new byte[fis.available()];
            fis.read(data);
            String jsonString = new String(data);
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject zooMasterDetails = jsonObject.getJSONObject("zooMaster");
            JSONObject zooDetails = jsonObject.getJSONObject("zoo");

            updateZoo(zooDetails);
            updateZooMaster(zooMasterDetails);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private static void updateZooMaster(JSONObject zooMasterDetails) throws JSONException {
        String sex = zooMasterDetails.getString("sex");
        String name = zooMasterDetails.getString("name");
        int weight = zooMasterDetails.getInt("weight");
        int age = zooMasterDetails.getInt("age");
        int height = zooMasterDetails.getInt("height");

        Zoo.getInstance().setZooMaster(new ZooMaster(name, sex, weight, age, height));
    }

    private static void updateZoo(JSONObject zooDetails) throws JSONException {
        Zoo.getInstance().setName(zooDetails.getString("name"));

        JSONArray enclosArray = zooDetails.getJSONArray("enclos");
        for (int i = 0; i < enclosArray.length(); i++) {
            JSONObject encloDetails = enclosArray.getJSONObject(i);
            updateEnclosure(encloDetails);
        }
    }

    private static void updateEnclosure(JSONObject encloDetails) throws JSONException {
        // Extract details and update an Enclosure
        String type = encloDetails.getString("type");
        String name = encloDetails.getString("nom");
        int surfaceArea = encloDetails.getInt("superficie");
        int maxCapacity = encloDetails.getInt("maxCapacity");
        String cleanliness = encloDetails.getString("cleanliness");

        // Create an Enclosure based on the type
        Enclosure enclosure = switch (type) {
            case "Enclosure" -> new Enclosure(name, surfaceArea, maxCapacity);
            case "Aquarium" -> new Aquarium(name, surfaceArea, maxCapacity);
            case "Aviary" -> new Aviary(name, surfaceArea, maxCapacity);
            default ->
                // Handle unknown types or throw an exception
                    throw new IllegalArgumentException("Unknown enclosure type: " + type);
        };

        enclosure.setCleanlinessDegree(Enclosure.CleanlinessStatus.valueOf(encloDetails.getString("cleanliness")));

        // Update Creatures
        JSONArray creaturesArray = encloDetails.getJSONArray("creatures");
        for (int i = 0; i < creaturesArray.length(); i++) {
            JSONObject creatureDetail = creaturesArray.getJSONObject(i);
            updateCreature(creatureDetail, enclosure);
        }

        // Add the enclosure to the Zoo
        Zoo.getInstance().addAnEnclosure(enclosure);
    }

    private static void updateCreature(JSONObject creatureDetail, Enclosure enclosure) throws JSONException {
        // Extract details and update a Creature
        String type = creatureDetail.getString("type");
        String name = creatureDetail.getString("name");
        String sex = creatureDetail.getString("sex");
        int weight = creatureDetail.getInt("weight");
        int age = creatureDetail.getInt("age");
        int height = creatureDetail.getInt("height");
        int satiety = creatureDetail.getInt("satiety");
        int energy = creatureDetail.getInt("energy");
        int health = creatureDetail.getInt("health");
        boolean isSick = creatureDetail.getBoolean("isSick");
        boolean isAsleep = creatureDetail.getBoolean("isAsleep");

        // Create a Creature based on the type
        Creature creature = switch (type) {
            case "Dragon" -> new Dragon(name, sex, weight, age, height);
            case "Kraken" -> new Kraken(name, sex, weight, age, height);
            case "Lycanthrope" -> new Lycanthrope(name, sex, weight, age, height);
            case "Megalodon" -> new Megalodon(name, sex, weight, age, height);
            case "Mermaid" -> new Mermaid(name, sex, weight, age, height);
            case "Nymph" -> new Nymph(name, sex, weight, age, height);
            case "Phoenix" -> new Phoenix(name, sex, weight, age, height);
            case "Unicorn" -> new Unicorn(name, sex, weight, age, height);
            default ->
                // Handle unknown types or throw an exception
                    throw new IllegalArgumentException("Unknown creature type: " + type);
        };

        // Update additional details
        creature.setSatiety(satiety);
        creature.setEnergy(energy);
        creature.setHealth(health);
        creature.getHealthindicator().setSick(isSick);
        if (creatureDetail.getBoolean("isAsleep")) creature.getEnergyindicator().setAsleep(true);

        // If the creature is Female, update additional details
        if (sex.equals("Female")) {
            ((Female) creature.getSex()).startBecomePregnantThread();
            ((Female) creature.getSex()).setGestationCounter(creatureDetail.getInt("gestationCounter"));
        }

        // Add the creature to the enclosure
        enclosure.addCreature(creature);
    }
}

