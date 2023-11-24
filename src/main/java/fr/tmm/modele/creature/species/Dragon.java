package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.Oviparous;
import fr.tmm.modele.creature.Reincarnate;
import fr.tmm.modele.creature.listener.CreatureDeathListener;
import fr.tmm.modele.creature.methodOfMovement.Flyer;
import fr.tmm.modele.creature.methodOfMovement.Swimmer;
import fr.tmm.modele.creature.methodOfMovement.Walker;

public class Dragon extends Oviparous implements Walker, Swimmer, Flyer, Reincarnate {

    public Dragon(String name, String sex, double weight, double height, int age, CreatureDeathListener listener) {
        super(name, sex, weight, height, age, listener);
    }

    @Override
    public void reincarnate() {
    }

    @Override
    public void run() {

    }
}
