package fr.tmm.modele.creature;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.reproduction.Egg;
import fr.tmm.modele.creature.reproduction.data.Gestation;
import fr.tmm.modele.creature.reproduction.data.NbChildren;

public abstract class Oviparous extends Creature {

    public Oviparous(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }
}
