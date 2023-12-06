package fr.tmm.modele.creature;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.reproduction.data.BabySize;
import fr.tmm.modele.creature.reproduction.data.Gestation;
import fr.tmm.modele.creature.reproduction.data.NbChildren;

public abstract class Viviparous extends Creature {

    public Viviparous(String nomEspece, String sexe, double poids, double taille, int age) {
        super(nomEspece, sexe, poids, taille, age);
    }

}
