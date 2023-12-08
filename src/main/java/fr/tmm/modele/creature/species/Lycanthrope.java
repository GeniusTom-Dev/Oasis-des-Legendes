package fr.tmm.modele.creature.species;

import fr.tmm.modele.Log;
import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Walker;
import fr.tmm.modele.lycanthropeColony.Colony;
import fr.tmm.modele.lycanthropeColony.Pack;
import fr.tmm.modele.lycanthropeColony.Rank;
import fr.tmm.modele.utils.Utils;

import java.util.Objects;

public class Lycanthrope extends Viviparous implements Walker {

    private AgeCategorie ageCategorie;
    private enum AgeCategorie {
        JEUNE, ADULTE, VIEUX
    }
    private int force; // nb de 0 à 10
    private int dominationFactor; // nb de gens dominé sur nb de gens qui domine
    private Rank rank; // alpha, beta, ..., omega
    private int level; // ageCategorie + force + dominationFactor + rang
    private int impetuosityFactor;

    public Lycanthrope(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    public void howl(String type) {
        Zoo.getInstance().getColony().startHowl(this, type);
        Log.getInstance().addLog(name + " émet un hurlement de " + type + ".");
    }

    public void hearHowl(String type) {
        if(Objects.equals(type, "startFromPack")) {
            this.howl("packResponse");
        }
    }

    // FAIT
    public void separatingFromPack() {
        Zoo.getInstance().getColony().getPackFromLycan(this).removeLycanthrope(this);
    }

    public void transform() {
        // TODO -> humain/loup -> surement hyper casse couille à faire
    }

    public void attack(){
        Zoo.getInstance().getColony().startAttack(this);
    }

    public void calculLevel() {
        int tmpLevel = 0;
        if (this.ageCategorie == AgeCategorie.JEUNE) tmpLevel += 2;
        else if (this.ageCategorie == AgeCategorie.ADULTE) tmpLevel += 7;
        else if (this.ageCategorie == AgeCategorie.VIEUX) tmpLevel += 5;
        tmpLevel += this.force;
        tmpLevel += Rank.values().length - Utils.getIndex(this.rank);
        tmpLevel += this.dominationFactor;
        this.level = tmpLevel;
    }

    @Override
    public void run() {super.run();}

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getDominationFactor() {
        return dominationFactor;
    }

    public void setDominationFactor(int dominationFactor) {
        this.dominationFactor = dominationFactor;
    }

    public Rank getRank() {
        return this.rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


}
