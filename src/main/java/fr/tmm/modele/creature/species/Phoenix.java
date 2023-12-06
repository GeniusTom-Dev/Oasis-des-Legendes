package fr.tmm.modele.creature.species;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.Oviparous;
import fr.tmm.modele.creature.Reincarnate;
import fr.tmm.modele.creature.methodOfMovement.Flyer;
import fr.tmm.modele.creature.reproduction.data.BabySize;

public class Phoenix extends Oviparous implements Flyer, Reincarnate {

    public Phoenix(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    @Override
    public void reincarnate() {
        this.setAge(0);
        this.setWeight(BabySize.Weight.determineFromType(this.getType()));
        this.setHeight(BabySize.Height.determineFromType(this.getType()));
        this.health.setValue(100);
        this.satiety.setValue(100);
        this.energy.setValue(100);
        this.health.setSick(false);
        Thread t = new Thread(this);
        t.start();
        Log.getInstance().addLog(name + " a ressuscité.");
    }

    @Override
    public void die() {
        this.reincarnate();
    }

    @Override
    public void run() {super.run();}

}
