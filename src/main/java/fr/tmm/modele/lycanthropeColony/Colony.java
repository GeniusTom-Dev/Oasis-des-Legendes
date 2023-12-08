package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.creature.species.Lycanthrope;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Colony {
    public ArrayList<Pack> getPacks() {
        return packs;
    }

    private ArrayList<Pack> packs = new ArrayList<>();
    private ArrayList<Lycanthrope> loneWolf = new ArrayList<>();

    public void hearPackHowl(PackHowl packHowl) {
        for (Pack pack : this.packs) {
            for (Lycanthrope lycanthrope : pack.getLycanthropes()) {

            }
        }
    }

    /**
     * Add a pack to the colony
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
        do{
            adversaryLycanthrope = lycanPack.getLycanthropes().get(new Random().nextInt(lycanPack.getLycanthropes().size()));
        }while(adversaryLycanthrope == actualLycanthrope && lycanPack.getCoupleAlpha().getFemale() != adversaryLycanthrope);
        return adversaryLycanthrope;
    }

    public void startAttack(Lycanthrope attackerLycanthrope) {
        Lycanthrope targetLycanthrope = getRandomLycanthrope(attackerLycanthrope);
        if(attackerLycanthrope.getLevel() > targetLycanthrope.getLevel() || (targetLycanthrope.getRank() == Rank.OMEGA && attackerLycanthrope.getRank() != Rank.OMEGA)){
            attackerLycanthrope.setDominationFactor(attackerLycanthrope.getDominationFactor() + 1);
            if(attackerLycanthrope.getRank().ordinal() < targetLycanthrope.getRank().ordinal()) {
                Rank saveRank = attackerLycanthrope.getRank();
                attackerLycanthrope.setRank(targetLycanthrope.getRank());
                targetLycanthrope.setRank(saveRank);
            }
            if(targetLycanthrope.getRank() == Rank.OMEGA) {
                if(Math.random() > 0.5) {
                    this.getPackFromLycan(targetLycanthrope).getLycanthropes().remove(targetLycanthrope);
                }

            }
            attackerLycanthrope.howl("domination");
            targetLycanthrope.howl("soumission");
        }else{
            attackerLycanthrope.setDominationFactor(attackerLycanthrope.getDominationFactor() - 1);
            if(attackerLycanthrope.getRank() == Rank.OMEGA) {
                if(Math.random() > 0.5) {
                    this.getPackFromLycan(attackerLycanthrope).getLycanthropes().remove(attackerLycanthrope);
                }

            }
            attackerLycanthrope.howl("soumission");
            targetLycanthrope.howl("domination");
        }
    }

    public void startHowl(Lycanthrope lycanthrope, String type) {
        if(Objects.equals(type, "pack")) {
            if(Math.random() > 0.5) {
                for(Pack packs : this.packs) {
                    for(Lycanthrope lycan : packs.getLycanthropes()) {
                        lycan.hearHowl("startFromPack");
                    }
                }
            }else{
                for(Lycanthrope lycan : this.getPackFromLycan(lycanthrope).getLycanthropes()) {
                    lycan.hearHowl("startFromPack");
                }
            }
        }
    }
}


/*
Appartenance : -> loup.packHowl() -> doit remonter de loup à pack
    - Log
    - Possibilité de répondre

Domination : -> dominant.dominationHowl(Lycanthrope soumis) -> lancer depuis meute
    - possibilité de répondre

Soumission : -> si a reçu domination
    -Log

Aggression : -> si à reçu un hurlement de domination ou envers un oméga
    - Log

////////////////////////////////////////////////////////////////////////////////

Howl {
    Lycanthrope source
    Lycanthrop dest
    type
}

packHowl() {
    this.listener.packHowl(Lycan source)
}

dominationHowl(Lycan soumis) {
    soumis.hear()
}






 */