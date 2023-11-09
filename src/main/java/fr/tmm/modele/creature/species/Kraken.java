package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.Oviparous;
import fr.tmm.modele.creature.methodOfMovement.Swimmer;

public class Kraken extends Oviparous implements Swimmer {
    public Kraken(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }
}
