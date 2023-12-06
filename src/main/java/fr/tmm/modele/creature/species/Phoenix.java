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
        this.age = 0;
        this.weight = BabySize.Weight.determineFromType(this.type);
        this.height = BabySize.Height.determineFromType(this.type);
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

    @Override
    public Creature born(double weight, double height) {
        return new Phoenix("Un phénix", "", weight, height, 0);
    }
}
