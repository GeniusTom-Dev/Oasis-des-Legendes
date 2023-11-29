package fr.tmm.modele.creature;

import fr.tmm.modele.creature.listener.CreatureDeathListener;
import fr.tmm.modele.indicator.EnergyIndicator;
import fr.tmm.modele.indicator.HealthIndicator;
import fr.tmm.modele.indicator.SatietyIndicator;

public abstract class Creature implements Runnable {
    protected String name;
    protected String type;
    protected String sex;
    protected double weight;
    protected double height;
    protected int age;
    protected SatietyIndicator satiety;
    protected EnergyIndicator energy; // contain a method isAsleep()
    protected HealthIndicator health; // contain a method isSick and isAlive

    public void setListener(CreatureDeathListener listener) {
        this.listener = listener;
    }

    protected CreatureDeathListener listener;

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

    public void die() {
        listener.onCreatureDeath(this);
    }

    /*public void run() {
        int cmp = 0;
        try {
            Thread.sleep(5000);
            this.energy.decrement(1);
            this.satiety.decrement(1);
            // une chance de faire du bruit
            // perdre de la vie si il est malade
            // perdre de la vie si il est entrain de mourir de faim
            if (cmp == 5) {
                ++this.age;
                cmp = 0;
            } else {
                ++cmp;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }*/

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

    public boolean isSick() {return this.health.isSick();}
}
