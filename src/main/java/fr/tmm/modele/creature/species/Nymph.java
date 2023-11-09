package fr.tmm.modele.creature.species;
import fr.tmm.modele.creature.Reincarnate;
import fr.tmm.modele.creature.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Walker;

public class Nymph extends Viviparous implements Walker, Reincarnate {
    public Nymph(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    @Override
    public void reincarnate() {

    }
}
