package fr.tmm.modele.creature;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.listener.CreatureListener;
import fr.tmm.modele.creature.reproduction.*;
import fr.tmm.modele.indicator.EnergyIndicator;
import fr.tmm.modele.indicator.HealthIndicator;
import fr.tmm.modele.indicator.SatietyIndicator;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.lang.reflect.Constructor;
import java.util.Objects;
import java.util.Random;

public abstract class Creature implements Runnable {
    protected StringProperty name;
    protected StringProperty type;
    protected SimpleDoubleProperty weight; // kg
    protected SimpleDoubleProperty height; // cm
    protected SimpleIntegerProperty age;
    private Sex sex;
    protected SatietyIndicator satiety;
    protected EnergyIndicator energy; // contain a method isAsleep()
    protected HealthIndicator health; // contain a method isSick and isAlive
    protected CreatureListener listener;

    public Creature(String name, String sex, double weight, double height, int age) {
        this.name = new SimpleStringProperty(name);
        this.weight = new SimpleDoubleProperty(weight);
        this.height = new SimpleDoubleProperty(height);
        this.age = new SimpleIntegerProperty(age);
        this.satiety = new SatietyIndicator();
        this.energy = new EnergyIndicator();
        this.health = new HealthIndicator();
        this.type = new SimpleStringProperty(this.getClass().getSimpleName());
        this.sex = determineSex(sex);
        Thread t = new Thread(this);
        t.start();
    }

    public void die() {
        if (this.age.get() == 0) {
            Log.getInstance().addLog("Un bébé " + this.getType() + " est mort-né car il est né dans un enclos plein.");
        } else {
            Log.getInstance().addLog(name + " est mort.");
        }
        if (this.listener != null) listener.onCreatureDeath(this);
    }

    public void run() {
        int cmp = 0;
        while (isAlive()) {
            try {
                Thread.sleep(1000);
                this.energy.decrement(1);
                this.satiety.decrement(1);
                // une chance de faire du bruit
                if (this.isSick()) this.health.decrement(2);
                if (this.isStarving()) starve();
                ++cmp;
                if (cmp == 5) {
                    this.aging();
                    cmp = 0;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.die();
    }

    private Sex determineSex(String sex) {
        switch (Objects.requireNonNull(sex)) {
            case "Female":
                return new Female(this);
            case "Male":
                return  new Male();
        }
        Random rand = new Random();
        int index = rand.nextInt(2);
        return (index == 0) ? new Female(this) : new Male();
    }

    public Creature born(double weight, double height) {
        try {
            Class<? extends Creature> clazz = this.getClass();
            Constructor<? extends Creature> constructor = clazz.getConstructor(String.class, String.class, double.class, double.class, int.class);
            return constructor.newInstance("Nom par défaut", "", weight, height, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void makeNoise(){
        Log.getInstance().addLog(this.getName() + " émet un son puissant !");
    }

    // --- Nom, Age, Taille, Poid ---

    public String getName() {
        return this.name.get();
    }

    public String getType() {
        return this.type.get();
    }

    public Sex getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        if (sex == "Female") {
            this.sex = new Female(this);
        } else {
            this.sex = new Male();
        }
    }

    public double getWeight() {
        return this.weight.get();
    }

    public void setWeight(double weight) {
        this.weight.set(weight);
    }

    public double getHeight() {
        return this.height.get();
    }

    public void setHeight(double height) {
        this.height.set(height);
    }

    public int getAge() {
        return this.age.get();
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    // --- Satiety ---

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
        Log.getInstance().addLog(name + " est entrain de mourir de faim.");
        this.health.decrement(10);
    }

    // --- Energy ---

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
        this.setAge(this.age.get() + 1);
        if (this.getAge() == 100) {
            this.health.setValue(0);
        }
        if (this.getAge() < 20) {
            this.setWeight(this.getWeight()*1.1);
            this.setHeight(this.getHeight()*1.1);
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

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public SimpleDoubleProperty weightProperty() {
        return weight;
    }

    public SimpleDoubleProperty heightProperty() {
        return height;
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public SimpleIntegerProperty satietyProperty() {
        return satiety.valueProperty();
    }

    public SimpleIntegerProperty energyProperty() {
        return energy.valueProperty();
    }

    public SimpleIntegerProperty healthProperty() {
        return health.valueProperty();
    }
}
