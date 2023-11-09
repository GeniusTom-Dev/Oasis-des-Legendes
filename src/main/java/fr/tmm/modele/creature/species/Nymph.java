package fr.tmm.modele.creature.species;
import fr.tmm.modele.creature.Reincarnate;
import fr.tmm.modele.creature.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Walker;

public class Nymph extends Viviparous implements Walker, Reincarnate {
    public Nymph(String nomEspece, String sexe, double poids, double taille, int age) {
        super(nomEspece, sexe, poids, taille, age);
    }

    @Override
    public void reincarnate() {

    }
}
