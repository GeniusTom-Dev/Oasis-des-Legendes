package fr.tmm.modele.creature.species;
import fr.tmm.modele.creature.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Walker;

public class Unicorn extends Viviparous implements Walker {
    public Unicorn(String nomEspece, String sexe, double poids, double taille, int age) {
        super(nomEspece, sexe, poids, taille, age);
    }

}
