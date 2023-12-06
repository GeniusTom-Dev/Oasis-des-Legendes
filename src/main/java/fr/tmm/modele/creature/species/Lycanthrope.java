package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Walker;
import fr.tmm.modele.lycanthropeColony.Colony;
import fr.tmm.modele.lycanthropeColony.Pack;

public class Lycanthrope extends Viviparous implements Walker {

    private AgeCategorie ageCategorie;
    private enum AgeCategorie {
        JEUNE, ADULTE, VIEUX
    }
    private int force; // nb de 0 à 10
    private int dominationFactor; // nb de gens dominé sur nb de gens qui domine
    private Character rank; // alpha, beta, ..., omega
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

    public void calculLevel() {
        int tmpLevel = 0;
        if (this.ageCategorie == AgeCategorie.JEUNE) tmpLevel += 2;
        else if (this.ageCategorie == AgeCategorie.ADULTE) tmpLevel += 7;
        else if (this.ageCategorie == AgeCategorie.VIEUX) tmpLevel += 5;
        tmpLevel += this.force;
        tmpLevel += Colony.allRank.size() - Colony.allRank.indexOf(this.rank);
        tmpLevel += this.dominationFactor;
        this.level = tmpLevel;
    }

    @Override
    public void run() {super.run();}

    @Override
    public Creature born(double weight, double height) {
        return new Lycanthrope("Un lycan", "", weight, height, 0);
    }
}
