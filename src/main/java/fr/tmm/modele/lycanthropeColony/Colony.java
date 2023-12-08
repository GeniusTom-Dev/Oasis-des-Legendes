package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.reproduction.Female;
import fr.tmm.modele.creature.species.Human;
import fr.tmm.modele.creature.species.Lycanthrope;
import fr.tmm.modele.enclosure.Enclosure;
import fr.tmm.modele.utils.Utils;

import java.util.ArrayList;
import java.util.Random;

public class Colony implements Runnable{

    private ArrayList<Pack> packs = new ArrayList<>();
    private ArrayList<Lycanthrope> loneWolf = new ArrayList<>();
    Boolean saisonDesAmours = true;
    public Colony() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        int cmp = 0;
        while (true) {
            try {
                Thread.sleep(1000); // 1s
                determineIfNewColonyHasToBeCreated();
                launchPackHowl();
                launchDominationHowl();
                if (this.saisonDesAmours) {
                    for (Pack pack : this.getPacks()) {
                        if (Math.random() < 0.2) {
                            ((Female) pack.getCoupleAlpha().getFemale().getSex()).startBecomePregnantThread();
                        }
                        for (Lycanthrope lycanthrope : pack.getLycanthropes()) {
                            if (Math.random() < 0.2) {
                                lycanthrope.setForce(lycanthrope.getForce()-new Random().nextInt(0,3));
                            } else if (Math.random() < 0.4) {
                                lycanthrope.setForce(lycanthrope.getForce()+new Random().nextInt(0,3));
                            }
                            if (Math.random() < 0.2) {
                                lycanthrope.setImpetuosityFactor(lycanthrope.getImpetuosityFactor()-new Random().nextInt(0,3));
                            } else if (Math.random() < 0.4) {
                                lycanthrope.setImpetuosityFactor(lycanthrope.getImpetuosityFactor()+new Random().nextInt(0,3));
                            }
                        }
                    }
                }
                ++cmp;
                if (cmp == 60) {
                    cmp = 0;
                    this.saisonDesAmours = !this.saisonDesAmours;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Launches pack howls with a chance pourcentage for each pack.
     * Each lycanthrope in a randomly selected pack has a 30% chance to perform a pack howl.
     */
    private void launchPackHowl() {
        for (Pack pack : getPacks()) {
            if (Math.random() < 0.3) {
                int index = Utils.getRandomIndexInList(pack.getLycanthropes());
                pack.getLycanthropes().get(index).packHowl();
            }
        }
    }

    /**
     * Launches domination howls among lycanthropes within each pack.
     * Each lycanthrope in each pack attempts to dominate a randomly selected adversary,
     * given certain conditions such as impetuosity factor and adversary's rank.
     */
    private void launchDominationHowl() {
        for (Pack pack : getPacks()) {
            for (Lycanthrope lycan : pack.getLycanthropes()) {
                Lycanthrope adv = getRandomLycanthrope(lycan);
                if (adv != null && (adv.getLevel() <= lycan.getImpetuosityFactor() || adv.getRank() == Rank.OMEGA)) {
                    lycan.dominationHowl(adv);
                }
            }
        }
    }

    private void transformInHuman() {
        for (Pack pack : getPacks()) {
            for (Lycanthrope lycan : pack.getLycanthropes()) {
                if (Math.random() < 0.01) {

                }
            }
        }
    }

    /**
     * Checks each enclosure in the zoo to determine if a new lycanthrope colony needs to be created.
     * If an enclosure has at least two creatures and the first creature is an instance of Lycanthrope,
     * and none of the lycanthropes in the enclosure are already part of a colony,
     * a new lycanthrope colony is created using the lycanthropes with the highest levels in the enclosure.
     * The male and female lycanthropes with the highest levels are selected to form a new pack.
     */
    public void determineIfNewColonyHasToBeCreated() {
        for (Enclosure enclos : Zoo.getInstance().getEnclosures()) {
            if (enclos.getCreaturesPresent().size() >= 2 && enclos.getCreaturesPresent().get(0) instanceof Lycanthrope) {
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

    /**
     * Retrieves a randomly selected lycanthrope from the same pack as the given lycanthrope,
     * excluding the given lycanthrope, and excluding the female alpha of the pack.
     *
     * @param actualLycanthrope the lycanthrope for which a random adversary is sought.
     * @return a randomly selected lycanthrope from the same pack, excluding the given lycanthrope,
     *         and excluding the female alpha of the pack; or null if no suitable adversary is found.
     */
    public Lycanthrope getRandomLycanthrope(Lycanthrope actualLycanthrope) {
        Lycanthrope adversaryLycanthrope = null;
        Pack lycanPack = this.getPackFromLycan(actualLycanthrope);
        if (lycanPack.getLycanthropes().size() > 2) {
            do {
                int index = Utils.getRandomIndexInList(lycanPack.getLycanthropes());
                adversaryLycanthrope = lycanPack.getLycanthropes().get(index);
                if (adversaryLycanthrope.equals(actualLycanthrope)) continue;
                if (lycanPack.getCoupleAlpha().getFemale().equals(adversaryLycanthrope)) continue;
            } while (true);
        }
        return adversaryLycanthrope;
    }

    /**
     * Initiates an attack between two lycanthropes: the attacker and the target.
     *
     * @param attackerLycanthrope the lycanthrope initiating the attack.
     * @param targetLycanthrope   the lycanthrope being targeted in the attack.
     */
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

    /**
     * Finds and returns the female lycanthrope with the highest level from the given list of creatures.
     *
     * @param lycans a list of creatures to search for the male lycanthrope with the highest level.
     * @return the female lycanthrope with the highest level, or null if no female lycanthrope is present.
     */
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

    /**
     * Finds and returns the male lycanthrope with the highest level from the given list of creatures.
     *
     * @param lycans a list of creatures to search for the male lycanthrope with the highest level.
     * @return the male lycanthrope with the highest level, or null if no male lycanthrope is present.
     */
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

    public ArrayList<Pack> getPacks() {
        return packs;
    }
}