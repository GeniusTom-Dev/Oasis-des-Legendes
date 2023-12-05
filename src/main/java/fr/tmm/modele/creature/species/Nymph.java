package fr.tmm.modele.creature.species;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.Reincarnate;
import fr.tmm.modele.creature.reproduction.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Walker;
import fr.tmm.modele.creature.reproduction.BabySize;

public class Nymph extends Viviparous implements Walker, Reincarnate {
    public Nymph(String name, String sex, double weight, double height, int age) {
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
    public void run() {super.run();}

    @Override
    public Creature born(double weight, double height) {
        return new Nymph("Une nymphe", "", weight, height, 0);
    }
}
