package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.Oviparous;
import fr.tmm.modele.creature.listener.CreatureDeathListener;
import fr.tmm.modele.creature.methodOfMovement.Swimmer;

public class Megalodon extends Oviparous implements Swimmer {
    public Megalodon(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    @Override
    public void run() {

    }
}
