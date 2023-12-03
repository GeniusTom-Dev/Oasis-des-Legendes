package fr.tmm.modele.creature.species;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.reproduction.Oviparous;
import fr.tmm.modele.creature.Reincarnate;
import fr.tmm.modele.creature.methodOfMovement.Flyer;
import fr.tmm.modele.creature.reproduction.BabySize;

public class Phoenix extends Oviparous implements Flyer, Reincarnate {

    public Phoenix(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    @Override
    public void reincarnate() {
        this.age = 0;
        this.weight = BabySize.Weight.getMin(this.type);
        this.height = BabySize.Height.getMin(this.type);
        this.health.setValue(100);
        this.health.setSick(false);
        Log.getInstance().addLog(name + " a ressuscité.");
    }

    @Override
    public void die() {
        this.reincarnate();
    }

    @Override
    public void run() {super.run();}
}
