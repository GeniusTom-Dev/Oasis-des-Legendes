package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Swimmer;

public class Mermaid extends Viviparous implements Swimmer {
    public Mermaid(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    @Override
    public void run() {super.run();}

    @Override
    public Creature born(double weight, double height) {
        return new Mermaid("Une sirène", "", weight, height, 0);
    }
}
