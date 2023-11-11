package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.Oviparous;
import fr.tmm.modele.creature.Reincarnate;
import fr.tmm.modele.creature.methodOfMovement.Flyer;

public class Phenix extends Oviparous implements Flyer, Reincarnate {
    public Phenix(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }


    @Override
    public void reincarnate() {

    }

}
