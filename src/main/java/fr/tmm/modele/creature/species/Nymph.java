package fr.tmm.modele.creature.species;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.Reincarnate;
import fr.tmm.modele.creature.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Walker;
import fr.tmm.modele.creature.reproduction.data.BabySize;

public class Nymph extends Viviparous implements Walker, Reincarnate {
    public Nymph(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    @Override
    public void reincarnate() {
        this.setAge(0);
        this.setWeight(BabySize.Weight.determineFromType(this.getType()));
        this.setHeight(BabySize.Height.determineFromType(this.getType()));
        this.health.setValue(100);
        this.health.setSick(false);
        Log.getInstance().addLog(this.getName() + " a ressuscité.");
    }

    @Override
    public void run() {super.run();}

}
