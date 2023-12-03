package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.reproduction.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Walker;
import fr.tmm.modele.lycanthropeColony.Pack;

public class Lycanthrope extends Viviparous implements Walker {

    private AgeCategorie ageCategorie;
    private enum AgeCategorie {
        JEUNE, ADULTE, VIEUX;
    }
    private int force;
    private int dominationFactor;
    private String rang; // alpha, beta, ..., omega
    private int level; // ageCategorie + force + dominationFactor + rang
    private int impetuosityFactor;
    private Pack pack;

    public Lycanthrope(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    public void howl() {
        // TODO
    }

    public void hearHowl() {
        // TODO -> doivent remonter jusqu'à la colony -> probablement devoir mettre un place un listener
    }

    public void separatingFromPack() {
        this.pack = null;
    }

    public void transform() {
        // TODO -> humain/loup -> surement hyper casse couille à faire
    }

    @Override
    public void run() {super.run();}
}
