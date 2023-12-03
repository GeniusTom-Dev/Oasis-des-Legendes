package fr.tmm.modele.creature;

import fr.tmm.modele.creature.listener.CreatureListener;
import fr.tmm.modele.indicator.EnergyIndicator;
import fr.tmm.modele.indicator.HealthIndicator;
import fr.tmm.modele.indicator.SatietyIndicator;

public abstract class Creature implements Runnable {
    protected String name;
    protected String type;
    protected String sex;
    protected double weight; // kg
    protected double height; // cm
    protected int age;
    protected SatietyIndicator satiety;
    protected EnergyIndicator energy; // contain a method isAsleep()
    protected HealthIndicator health; // contain a method isSick and isAlive
    protected CreatureListener listener;

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
        Thread t = new Thread(this);
        t.start();
    }

    public void die() {
        if (this.listener != null) listener.onCreatureDeath(this);
    }

    public void run() {;
        int cmp = 0;
        while (isAlive()) {
            try {
                System.out.println(name + " tour : " + cmp);
                Thread.sleep(5000);
                this.energy.decrement(1);
                this.satiety.decrement(1);
                // une chance de faire du bruit
                if (this.isSick()) this.health.decrement(2);
                if (this.isStarving()) starve();
                ++cmp;
                if (cmp == 5) {
                    System.out.println(name + " age : " + age);
                    this.aging();
                    System.out.println(name + " age : " + age);
                    cmp = 0;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.die();
    }

    public String makeNoise() {
        return this.name + " émet un son puissant !";
    }

    // --- Nom, Age, Taille, Poid ---

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
        if (this.age == 100) {
            this.health.setValue(0);
        }
    }

    public boolean isAlive() {
        return this.health.isAlive();
    }

    public boolean isSick() {return this.health.isSick();}

    public void setListener(CreatureListener listener) {
        this.listener = listener;
    }

    public CreatureListener getListener() {
        return listener;
    }

}
