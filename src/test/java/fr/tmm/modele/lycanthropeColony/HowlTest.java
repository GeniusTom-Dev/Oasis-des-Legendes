package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.Log;
import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.species.Lycanthrope;
import fr.tmm.modele.enclosure.Enclosure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HowlTest {

    Lycanthrope alpha;
    Lycanthrope female;
    Lycanthrope omega;
    Lycanthrope strongLycan;
    Lycanthrope weakLycan;
    @BeforeEach
    void init() {
        Zoo.getInstance().clear();
        this.alpha = new Lycanthrope("Alpha", "Male",50,50,50);
        this.omega = new Lycanthrope("Omega", "Male",50,50,50);
        this.female = new Lycanthrope("Female", "Female",50,50,50);
        this.strongLycan = new Lycanthrope("StrongLycan", "Male",50,50,50);
        this.weakLycan = new Lycanthrope("WeakLycan", "Male",50,50,50);
        Enclosure enclo1 = new Enclosure("Enclos 1", 50,5);
        enclo1.addCreature(alpha);
        enclo1.addCreature(omega);
        enclo1.addCreature(female);
        enclo1.addCreature(strongLycan);
        enclo1.addCreature(weakLycan);
        Pack pack = new Pack(alpha, omega);
        pack.addLycanthrope(omega);
        pack.addLycanthrope(strongLycan);
        pack.addLycanthrope(weakLycan);
        Zoo.getInstance().getColony().addPack(pack);
        Log.getInstance().clear();
        this.omega.setRank(Rank.OMEGA);
        this.strongLycan.setLevel(50);
        this.weakLycan.setLevel(20);
        this.weakLycan.setRank(Rank.BETA);
        this.strongLycan.setRank(Rank.GAMMA);
    }

    @Test
    void omegaIsSubmissive() {
        this.strongLycan.dominationHowl(this.omega);
        Log.getInstance().showLogs();
        assertTrue(Log.getInstance().getLogs().contains("Omega se soumet à StrongLycan."));
        assertTrue(Log.getInstance().getLogs().contains("StrongLycan émet un hurlement de domination à l'encontre de Omega."));
    }

    @Test
    void exchangeRank() {
        this.strongLycan.dominationHowl(this.weakLycan);
        assertEquals(this.weakLycan.getRank(), Rank.GAMMA);
        assertEquals(this.strongLycan.getRank(), Rank.BETA);
        Log.getInstance().showLogs();
        assertTrue(Log.getInstance().getLogs().contains(weakLycan.getName() + " se soumet à " + strongLycan.getName()+"."));
    }

    @Test
    void loose() {
        this.weakLycan.dominationHowl(this.strongLycan);
        assertEquals(this.weakLycan.getRank(), Rank.GAMMA);
        assertEquals(this.strongLycan.getRank(), Rank.BETA);
        Log.getInstance().showLogs();
        assertTrue(Log.getInstance().getLogs().contains(weakLycan.getName() + " se soumet à " + strongLycan.getName()+"."));
    }

}