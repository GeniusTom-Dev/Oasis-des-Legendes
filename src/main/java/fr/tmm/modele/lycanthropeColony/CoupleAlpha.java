package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.creature.species.Lycanthrope;

public class CoupleAlpha {

    private Lycanthrope male;

    private Lycanthrope female;

    public CoupleAlpha(Lycanthrope male, Lycanthrope female) {
        this.male = male;
        this.female = female;
        this.male.setRank(Rank.ALPHA);
        this.female.setRank(Rank.ALPHA);
    }

    public void setMale(Lycanthrope male) {
        this.male.setRank(Rank.BETA);
        this.male = male;
        this.male.setRank(Rank.ALPHA);
    }

    public void setFemale(Lycanthrope female) {
        this.female.setRank(Rank.BETA);
        this.female = female;
        this.female.setRank(Rank.ALPHA);
    }

    public Lycanthrope getMale() {
        return male;
    }

    public Lycanthrope getFemale() {
        return female;
    }

}
