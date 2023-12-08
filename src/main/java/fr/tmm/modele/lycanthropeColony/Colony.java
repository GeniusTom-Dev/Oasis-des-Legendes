package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.species.Lycanthrope;
import fr.tmm.modele.enclosure.Enclosure;

import java.util.ArrayList;
import java.util.Random;

public class Colony {
    public ArrayList<Pack> getPacks() {
        return packs;
    }

    private ArrayList<Pack> packs = new ArrayList<>();
    private ArrayList<Lycanthrope> loneWolf = new ArrayList<>();

    public void determineIfNewColonyHasToBeCreated() {
        for (Enclosure enclos : Zoo.getInstance().getEnclosures()) {
            if (enclos.getCreaturesPresent().size() > 2 && enclos.getCreaturesPresent().get(0) instanceof Lycanthrope) {
                for (Creature lycan : enclos.getCreaturesPresent()) {
                    if (Zoo.getInstance().getColony().getPackFromLycan((Lycanthrope) lycan) != null) {
                        return;
                    }
                }
                // meute doit être créer
                Lycanthrope male = getMaleWithHeightestLevel(enclos.getCreaturesPresent());
                Lycanthrope female = getFemaleWithHeightestLevel(enclos.getCreaturesPresent());
                this.addPack(new Pack(male, female));
            }
        }
    }

    /**
     * Add a pack to the colony
     *
     * @param pack : the pack to add
     */
    public void addPack(Pack pack) {
        this.packs.add(pack);
        for (Lycanthrope lycan : pack.getLycanthropes()) {
            this.loneWolf.remove(lycan);
        }
    }

    /**
     * Find the pack of a lycanthrope
     *
     * @param targetLycan : the lycan
     * @return the pack of the lycan
     */
    public Pack getPackFromLycan(Lycanthrope targetLycan) {
        for (Pack pack : this.packs) {
            for (Lycanthrope lycan : pack.getLycanthropes()) {
                if (lycan.equals(targetLycan)) {
                    return pack;
                }
            }
        }
        return null;
    }

    public Lycanthrope getRandomLycanthrope(Lycanthrope actualLycanthrope) {
        Lycanthrope adversaryLycanthrope = null;
        Pack lycanPack = this.getPackFromLycan(actualLycanthrope);
        do {
            adversaryLycanthrope = lycanPack.getLycanthropes().get(new Random().nextInt(lycanPack.getLycanthropes().size()));
        } while (adversaryLycanthrope == actualLycanthrope && lycanPack.getCoupleAlpha().getFemale() != adversaryLycanthrope);
        return adversaryLycanthrope;
    }

    public void startAttack(Lycanthrope attackerLycanthrope, Lycanthrope targetLycanthrope) {
        //Lycanthrope targetLycanthrope = getRandomLycanthrope(attackerLycanthrope);
        // est ce que domination réussie
        if (attackerLycanthrope.getLevel() > targetLycanthrope.getLevel() || (targetLycanthrope.getRank() == Rank.OMEGA && attackerLycanthrope.getRank() != Rank.OMEGA)) {
            attackerLycanthrope.setDominationFactor(attackerLycanthrope.getDominationFactor() + 1);
            // si l'attaquant à un rang inférieur au dominé, il inverse de rang
            if (attackerLycanthrope.getRank().ordinal() > targetLycanthrope.getRank().ordinal()) {
                Rank saveRank = attackerLycanthrope.getRank();
                attackerLycanthrope.setRank(targetLycanthrope.getRank());
                targetLycanthrope.setRank(saveRank);
            }
            // possibilité de quitter la meute si la cible est omega
            if (targetLycanthrope.getRank() == Rank.OMEGA) {
                if (Math.random() > 0.5) {
                    this.getPackFromLycan(targetLycanthrope).getLycanthropes().remove(targetLycanthrope);
                }

            }
            //attackerLycanthrope.dominationHowl(targetLycanthrope);
            // le soumis émet un cris de soumissions
            targetLycanthrope.submissiveHowl(attackerLycanthrope);
        } else {
            attackerLycanthrope.setDominationFactor(attackerLycanthrope.getDominationFactor() - 1);
            // si l'attaquant à un rang inférieur au dominé, il inverse de rang
            if (attackerLycanthrope.getRank().ordinal() < targetLycanthrope.getRank().ordinal()) {
                Rank saveRank = attackerLycanthrope.getRank();
                attackerLycanthrope.setRank(targetLycanthrope.getRank());
                targetLycanthrope.setRank(saveRank);
            }
            if (attackerLycanthrope.getRank() == Rank.OMEGA) {
                if (Math.random() > 0.5) {
                    this.getPackFromLycan(attackerLycanthrope).getLycanthropes().remove(attackerLycanthrope);
                }
            }
            //attackerLycanthrope.submissiveHowl(targetLycanthrope);
            targetLycanthrope.aggresivityHowl(attackerLycanthrope);
            attackerLycanthrope.submissiveHowl(targetLycanthrope);
        }
    }

    public Lycanthrope getFemaleWithHeightestLevel(ArrayList<Creature> lycans) {
        Lycanthrope heightestLevelFemale = null;
        for (Creature lycan : lycans) {
            if (lycan.getSex().toString() == "Female") {
                if (heightestLevelFemale == null || ((Lycanthrope) lycan).getLevel() > heightestLevelFemale.getLevel()) {
                    heightestLevelFemale = (Lycanthrope) lycan;
                }
            }
        }
        return heightestLevelFemale;
    }

    public Lycanthrope getMaleWithHeightestLevel(ArrayList<Creature> lycans) {
        Lycanthrope heightestLevelMale = null;
        for (Creature lycan : lycans) {
            if (lycan.getSex().toString() == "Male") {
                if (heightestLevelMale == null || ((Lycanthrope) lycan).getLevel() > heightestLevelMale.getLevel()) {
                    heightestLevelMale = (Lycanthrope) lycan;
                }
            }
        }
        return heightestLevelMale;
    }
}