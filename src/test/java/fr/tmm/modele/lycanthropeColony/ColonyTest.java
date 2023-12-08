package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.Log;
import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.reproduction.Female;
import fr.tmm.modele.creature.species.Lycanthrope;
import fr.tmm.modele.enclosure.Enclosure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ColonyTest {

    Lycanthrope femaleAlpha;
    Lycanthrope maleAlpha;
    Lycanthrope lycan1;
    Enclosure enclos;

    @BeforeEach
    void init() {
        this.femaleAlpha = new Lycanthrope("Female Alpha", "Female", 50,50,50);
        this.maleAlpha = new Lycanthrope("Male Alpha", "Male", 50,50,50);
        this.lycan1 = new Lycanthrope("Lycan 1", "Male", 50,50,50);
        this.enclos = new Enclosure("Enclos", 50,5);
        this.enclos.addCreature(femaleAlpha);
        this.enclos.addCreature(maleAlpha);
        this.enclos.addCreature(lycan1);
        this.maleAlpha.setLevel(20);
        this.femaleAlpha.setLevel(20);
        this.lycan1.setLevel(10);
        Zoo.getInstance().addAnEnclosure(this.enclos);
    }

    @Test
    void addRemoveLycan() {
        assertNull(Zoo.getInstance().getColony().getPackFromLycan(this.maleAlpha));
        assertNull(Zoo.getInstance().getColony().getPackFromLycan(this.femaleAlpha));
        assertNull(Zoo.getInstance().getColony().getPackFromLycan(this.lycan1));
        Zoo.getInstance().getColony().determineIfNewColonyHasToBeCreated();
        assertNotNull(Zoo.getInstance().getColony().getPackFromLycan(this.maleAlpha));
        assertNotNull(Zoo.getInstance().getColony().getPackFromLycan(this.femaleAlpha));
        assertNull(Zoo.getInstance().getColony().getPackFromLycan(this.lycan1));
    }

    @Test
    void reproduction() {
        Zoo.getInstance().getColony().determineIfNewColonyHasToBeCreated();
        sleep(20);
        assertTrue(((Female)this.femaleAlpha.getSex()).isPregnant());
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}