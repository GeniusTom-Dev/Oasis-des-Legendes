package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Swimmer;

public class Mermaid extends Viviparous implements Swimmer {
    public Mermaid(String nomEspece, String sexe, double poids, double taille, int age) {
        super(nomEspece, sexe, poids, taille, age);
    }
}
