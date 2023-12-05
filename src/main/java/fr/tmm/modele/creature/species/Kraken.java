package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.reproduction.BabySize;
import fr.tmm.modele.creature.reproduction.Oviparous;
import fr.tmm.modele.creature.methodOfMovement.Swimmer;

public class Kraken extends Oviparous implements Swimmer {
    public Kraken(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    @Override
    public void run() {super.run();}

    @Override
    public Creature born(double weight, double height) {
        return new Kraken("Un kraken", "", weight, height, 0);
    }
}
