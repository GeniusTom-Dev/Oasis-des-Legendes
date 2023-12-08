package fr.tmm.modele.creature.species;

import fr.tmm.modele.Log;
import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Walker;
import fr.tmm.modele.lycanthropeColony.*;
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

    public void packHowl() {
        new PackHowl(this);
    }

    public void dominationHowl(Lycanthrope target) {
        new DominationHowl(this, target);
    }

    public void aggresivityHowl(Lycanthrope target) {
        new AgressivityHowl(this, target);
    }

    public void submissiveHowl(Lycanthrope lycanthrope) {
        new SubmissiveHowl(this, lycanthrope);
    }

    public void hearHowl(Howl howl) {
        if (!this.isAsleep() && !this.isSick()) {
            if (howl instanceof PackHowl) {
                Log.getInstance().addLog(this.getName() + " a repondu au hurlement de " + howl.getEmetteur().getName()+ ".");
            }
        }
    }

    // FAIT
    public void separatingFromPack() {
        Zoo.getInstance().getColony().getPackFromLycan(this).removeLycanthrope(this);
    }

    public void transform() {
        // TODO -> humain/loup -> surement hyper casse couille à faire
    }

    public void attack(Lycanthrope target){
        Zoo.getInstance().getColony().startAttack(this, target);
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

    public int getImpetuosityFactor() {
        return impetuosityFactor;
    }

    public void setImpetuosityFactor(int impetuosityFactor) {
        this.impetuosityFactor = impetuosityFactor;
    }

}
