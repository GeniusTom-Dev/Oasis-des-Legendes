package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.reproduction.BabySize;
import fr.tmm.modele.creature.reproduction.Oviparous;
import fr.tmm.modele.creature.methodOfMovement.Swimmer;

public class Megalodon extends Oviparous implements Swimmer {
    public Megalodon(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    @Override
    public void run() {super.run();}

    @Override
    public Creature born(double weight, double height) {
        return new Megalodon("Un mégalodon", "", weight, height, 0);
    }
}
