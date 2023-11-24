package fr.tmm.modele.creature;

import fr.tmm.modele.indicator.EnergyIndicator;
import fr.tmm.modele.indicator.HealthIndicator;
import fr.tmm.modele.indicator.SatietyIndicator;

import javax.json.JsonObject;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static fr.tmm.save.readFile.readJSON;

public abstract class Creature implements Runnable {
    private String name;
    private String type;
    private String sex;
    private double weight;
    private double height;
    private int age;
    private SatietyIndicator satiety;
    private EnergyIndicator energy; // contain a method isAsleep()
    private HealthIndicator health; // contain a method isSick and isAlive

    public Creature(String name, String sex, double weight, double height, int age) {
        this.name = name;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.satiety = new SatietyIndicator();
        this.energy = new EnergyIndicator();
        this.health = new HealthIndicator();
        this.type = this.getClass().getSimpleName();
    }

    public void run() {
        int counter = 0;
        try {
            Thread.sleep(5000);
            this.energy.decrement(1);
            this.satiety.decrement(1);
            // une chance de faire du bruit
            if (counter == 5) {
                ++this.age;
                counter = 0;
            } else {
                ++counter;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Integer> getParameters() {
        Map<String, Integer> creatureParameters = new HashMap<>();

        try{
            JsonObject jsonParameters = readJSON("src/main/resources/config/decrement.json");
            JsonObject parameters = jsonParameters.getJsonObject(this.type.toLowerCase());
            for (String key : parameters.keySet()) {
                creatureParameters.put(key, parameters.getInt(key));
            }
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }

        creatureParameters = creatureParameters.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));

        return creatureParameters;
    }

    public String makeNoise() {
        return this.name + " émet un son puissant !";
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSex() {
        return sex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // --- Satiety ---

    public String displaySatietyLevel() {
        return satiety.toString();
    }

    public void setSatiety(int satiety) {
        this.satiety.setValue(satiety);
    }

    public int getSatiety() {
        return this.satiety.getValue();
    }

    public void getHungrier() {
        this.satiety.decrement(2); // valeur arbitraire
    }

    public void eat() {
        if (!this.isAsleep()) this.satiety.increment(80);
    }

    public boolean isStarving() {
        return this.satiety.getValue() == 0;
    }

    public void starve() {
        this.health.decrement(10);
    }

    // --- Energy ---

    public String displayEnergyLevel() {
        return this.energy.toString();
    }

    public void setEnergy(int energy) {
        this.energy.setValue(energy);
    }

    public int getEnergy() {
        return this.energy.getValue();
    }

    public void getSleepier() {
        this.energy.decrement(1); // valeur arbitraire
    }

    public void sleep() {
        this.energy.increment(5);
    }

    public boolean isAsleep() {
        return this.energy.isAsleep();
    }

    // --- Health ---

    public String displayHealthLevel() {
        return this.health.toString();
    }

    public void setHealth(int health) {
        this.health.setValue(health);
    }

    public int getHealth() {
        return this.health.getValue();
    }

    public HealthIndicator getHealthindicator() {
        return this.health;
    }

    public void heal() {
        this.health.heal();
    }

    public void aging() {
        ++this.age;
    }

    public boolean isAlive() {
        return this.health.isAlive();
    }
}
