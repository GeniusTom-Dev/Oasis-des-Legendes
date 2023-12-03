package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.reproduction.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Walker;

public class Unicorn extends Viviparous implements Walker {
    public Unicorn(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    @Override
    public void run() {super.run();}
}
