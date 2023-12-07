package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.creature.species.Lycanthrope;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackTest {

    Lycanthrope femaleAlpha;
    Lycanthrope maleAlpha;
    Lycanthrope lycan1;
    Pack pack;

    @BeforeEach
    void init() {
        this.femaleAlpha = new Lycanthrope("Female Alpha", "Female", 50,50,50);
        this.maleAlpha = new Lycanthrope("Male Alpha", "Male", 50,50,50);
        this.lycan1 = new Lycanthrope("Lycan 1", "Male", 50,50,50);
        this.pack = new Pack(maleAlpha, femaleAlpha);
    }

    @Test
    void addRemoveLycan() {
        assertEquals(pack.getLycanthropes().size(), 2);
        pack.addLycanthrope(lycan1);
        assertEquals(pack.getLycanthropes().size(), 3);
        pack.removeLycanthrope(lycan1);
        assertEquals(pack.getLycanthropes().size(), 2);
    }

    @Test
    void findHeightestLevelFemale() {
        Lycanthrope female1 = new Lycanthrope("Female 1", "Female", 50,50,50);
        Lycanthrope female2 = new Lycanthrope("Female 2", "Female", 50,50,50);
        pack.addLycanthrope(female1);
        pack.addLycanthrope(female2);
        female1.setLevel(10);
        female2.setLevel(15);
        this.femaleAlpha.setLevel(5);
        this.maleAlpha.setLevel(20);
        assertEquals(pack.getFemaleWithHeightestLevel(), female2);
        this.lycan1.setForce(80);
        pack.addLycanthrope(lycan1);
        assertEquals(pack.getFemaleWithHeightestLevel(), female2);
    }

    @Test
    void testNewCoupleAlpha() {
        this.femaleAlpha.setLevel(5);
        Lycanthrope female1 = new Lycanthrope("Female 1", "Female", 50,50,50);
        female1.setLevel(40);
        this.pack.addLycanthrope(female1);
        this.pack.setNewCoupleAlpha(this.maleAlpha);
        assertEquals(this.pack.getCoupleAlpha().getFemale(), female1);
        assertEquals(female1.getRank(), Rank.ALPHA);
        assertNotEquals(this.femaleAlpha.getRank(), Rank.ALPHA);
        assertEquals(this.pack.getCoupleAlpha().getMale(), this.maleAlpha);
        assertEquals(maleAlpha.getRank(), Rank.ALPHA);
    }
}