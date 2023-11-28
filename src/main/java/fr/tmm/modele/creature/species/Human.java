package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.Viviparous;
import fr.tmm.modele.creature.listener.CreatureDeathListener;
import fr.tmm.modele.creature.methodOfMovement.Walker;

public class Human extends Viviparous implements Walker {
    public Human(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    @Override
    public void run() {

    }
}
