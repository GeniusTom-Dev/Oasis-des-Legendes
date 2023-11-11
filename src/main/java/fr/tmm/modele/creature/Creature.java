package fr.tmm.modele.creature;

import fr.tmm.modele.indicator.EnergyIndicator;
import fr.tmm.modele.indicator.HealthIndicator;
import fr.tmm.modele.indicator.SatietyIndicator;

public abstract class Creature {
    private String name;
    private String type;
    private String sex;
    private double weight;
    private double height;
    private int age;
    private SatietyIndicator satiety;
    private EnergyIndicator energy;
    private boolean isAsleep;
    private HealthIndicator health;

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

    public void makeNoise() {
        System.out.println("Le " + this.name + " émet un son puissant !");
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

    public void eat() {
        this.satiety.increment(80);
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
        if (this.energy.getValue() == 0) {
            this.isAsleep = true;
        }
    }

    public int getEnergy() {
        return this.energy.getValue();
    }

    public void sleep() {
        this.energy.increment(5);
        if (this.energy.getValue() == 100) {
            this.isAsleep = false;
        }
    }

    public boolean isAsleep() {
        return isAsleep;
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

    public void heal() {
        this.health.increment(100);
    }

    public void die(Creature creature) {
        if (this.equals(creature)) {
            creature = null;
        }
    }

    public void die2() {
        Creature instance = this;
        instance = null;
    }


    public void aging() {++this.age;}
}
