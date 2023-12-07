package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.species.Lycanthrope;
import fr.tmm.modele.utils.Utils;

import java.util.ArrayList;

public class Pack {

    private ArrayList<Lycanthrope> lycanthropes = new ArrayList<>();

    private CoupleAlpha coupleAlpha;

    public Pack(Lycanthrope maleAlpha, Lycanthrope femaleAlpha) {
        maleAlpha.setRank(Rank.ALPHA);
        femaleAlpha.setRank(Rank.ALPHA);
        this.lycanthropes.add(maleAlpha);
        this.lycanthropes.add(femaleAlpha);
        this.coupleAlpha = new CoupleAlpha(maleAlpha, femaleAlpha);
    }

    public ArrayList<Lycanthrope> getLycanthropes() {
        return this.lycanthropes;
    }

    public void addLycanthrope(Lycanthrope lycanthrope) {
        this.getLycanthropes().add(lycanthrope);
    }

    public void removeLycanthrope(Lycanthrope lycanthrope) {
        this.getLycanthropes().remove(lycanthrope);
    }

    public void setNewCoupleAlpha(Lycanthrope newMaleAlpha) {
        this.coupleAlpha.setMale(newMaleAlpha);
        this.coupleAlpha.setFemale(this.getFemaleWithHeightestLevel());
    }

    public Lycanthrope getFemaleWithHeightestLevel() {
        Lycanthrope heightestLevelFemale = null;
        for (Lycanthrope lycan : this.getLycanthropes()) {
            if (lycan.getSex().toString() == "Female") {
                if (heightestLevelFemale == null || lycan.getLevel() > heightestLevelFemale.getLevel()) {
                    heightestLevelFemale = lycan;
                }
            }
        }
        return heightestLevelFemale;
    }

    public CoupleAlpha getCoupleAlpha() {
        return coupleAlpha;
    }

}
