package fr.tmm.modele.creature.species;

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
        this.weight = BabySize.Weight.getMin(this.type);
        this.height = BabySize.Height.getMin(this.type);
        this.health.setValue(100);
        this.health.setSick(false);
    }

    @Override
    public void run() {super.run();}
}
