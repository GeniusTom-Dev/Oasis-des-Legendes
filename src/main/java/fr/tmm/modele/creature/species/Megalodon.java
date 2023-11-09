package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.Oviparous;
import fr.tmm.modele.creature.methodOfMovement.Swimmer;

public class Megalodon extends Oviparous implements Swimmer {
    public Megalodon(String nomEspece, String sexe, double poids, double taille, int age) {
        super(nomEspece, sexe, poids, taille, age);
    }
}
