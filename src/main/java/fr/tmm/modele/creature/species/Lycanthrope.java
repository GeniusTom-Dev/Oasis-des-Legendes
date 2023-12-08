package fr.tmm.modele.creature.species;

import fr.tmm.modele.Log;
import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Walker;
import fr.tmm.modele.creature.reproduction.Sex;
import fr.tmm.modele.lycanthropeColony.*;
import fr.tmm.modele.utils.Utils;

import java.util.Objects;
import java.util.Random;

public class Lycanthrope extends Human implements Walker {

    private AgeCategorie ageCategorie;

    private enum AgeCategorie {
        JEUNE, ADULTE, VIEUX
    }
    private int force; // nb de 0 à 10
    private int dominationFactor; // nb de gens dominé sur nb de gens qui domine
    private Rank rank; // alpha, beta, ..., omega
    private int level; // ageCategorie + force + dominationFactor + rang
    private int impetuosityFactor; // nb de 0 à 15

    public Lycanthrope(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
        Random rand = new Random();
        updateAgeCategorie();
        this.force = rand.nextInt(0,11);
        this.impetuosityFactor = rand.nextInt(0,16);
        if (Zoo.getInstance().getColony().getPackFromLycan(this) == null) {
            this.dominationFactor = 0;
            this.rank = null;
            this.level = 0;
        } else {
            updateDominationFactor();
            this.rank = Rank.getRankByIndex(new Random().nextInt(1,Rank.values().length));
            calculLevel();
        }
    }

    public void updateHierarchie() {
        updateDominationFactor();
        calculLevel();
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


    public void separatingFromPack() {
        Zoo.getInstance().getColony().getPackFromLycan(this).removeLycanthrope(this);
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

    public void updateDominationFactor() {
        setDominationFactor(this.getNbLycanWithInferiorRank()/this.getNbLycanWithSuperiorRank());
    }

    private int getNbLycanWithInferiorRank() {
        Pack pack = Zoo.getInstance().getColony().getPackFromLycan(this);
        int cmp = 0;
        for (Lycanthrope lycan : pack.getLycanthropes()) {
            if (lycan.getRank().ordinal() > this.getRank().ordinal()) {
                ++cmp;
            }
        }
        return cmp;
    }

    private int getNbLycanWithSuperiorRank() {
        Pack pack = Zoo.getInstance().getColony().getPackFromLycan(this);
        int cmp = 0;
        for (Lycanthrope lycan : pack.getLycanthropes()) {
            if (lycan.getRank().ordinal() < this.getRank().ordinal()) {
                ++cmp;
            }
        }
        return cmp;
    }

    @Override
    public void setAge(int age) {
        super.setAge(age);
        updateAgeCategorie();
    }

    @Override
    public void aging() {
        super.aging();
        updateAgeCategorie();
    }

    private void updateAgeCategorie() {
        if (this.getAge() < 25) {
            this.ageCategorie = AgeCategorie.JEUNE;
        } else if (this.getAge() < 75) {
            this.ageCategorie = AgeCategorie.ADULTE;
        } else {
            this.ageCategorie = AgeCategorie.VIEUX;
        }
    }

}
