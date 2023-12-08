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

    public void determineHierarchy() {
        selectionSort(this.getLycanthropes());
        int rank = 1;
        for (int i = 0; i < this.getLycanthropes().size(); i++) {
            if (this.getLycanthropes().get(i).getRank() != Rank.ALPHA) {
                this.getLycanthropes().get(i).setRank(Rank.getRankByIndex(rank));
                ++rank;
            }
        }
//        for (int i = this.getLycanthropes().size()-1; i > 0 ; i--) {
//            if (this.getLycanthropes().get(i).getRank() == Rank.ALPHA)
//
//        }
    }

    public ArrayList<Lycanthrope> getLycanthropes() {
        return this.lycanthropes;
    }

    public void addLycanthrope(Lycanthrope lycanthrope) {
        this.getLycanthropes().add(lycanthrope);
        this.determineHierarchy();
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

    public Lycanthrope getRancomLycanthrpe() {
        return this.getLycanthropes().get(Utils.getRandomIndexInList(this.getLycanthropes()));
    }

    public CoupleAlpha getCoupleAlpha() {
        return coupleAlpha;
    }

    /**
     * Sorts an ArrayList of integers using the selection sort algorithm.
     * @param list the ArrayList to be sorted.
     */
    private void selectionSort(ArrayList<Lycanthrope> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            // Find the maximum element in the unsorted part
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j).getLevel() > list.get(maxIndex).getLevel()) {
                    maxIndex = j;
                }
            }

            // Swap the maximum element with the first element in the unsorted part
            Lycanthrope temp = list.get(maxIndex);
            list.set(maxIndex, list.get(i));
            list.set(i, temp);
        }
    }

}
