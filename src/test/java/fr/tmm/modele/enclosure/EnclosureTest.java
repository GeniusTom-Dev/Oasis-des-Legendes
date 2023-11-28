package fr.tmm.modele.enclosure;

import fr.tmm.modele.creature.species.Dragon;
import fr.tmm.modele.creature.species.Nymph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnclosureTest {

    Enclosure enclosure;
    Dragon dragon1;
    Dragon dragon2;
    Dragon dragon3;
    Nymph nymph;

    @BeforeEach
    void initialisation() {
        this.enclosure = new Enclosure("Un Enclos", 80.5, 3);
        this.dragon1 = new Dragon("Dragon1", "male", 50, 50, 50);
        this.dragon2 = new Dragon("Dragon2", "male", 50, 50, 50);
        this.dragon3 = new Dragon("Dragon3", "male", 50, 50, 50);
        this.nymph = new Nymph("Nymph", "male", 50, 50, 50);
    }

    @Test
    void addCreature() {
        enclosure.addCreature(this.dragon1);
        assertEquals(enclosure.getCreaturesPresent().size(), 1);
    }

    @Test
    void addIncompatibleCreature() {
        enclosure.addCreature(this.dragon1);
        enclosure.addCreature(this.nymph);
        assertEquals(enclosure.getCreaturesPresent().size(), 1);
        assertEquals("Dragon", enclosure.getCreaturesPresent().get(0).getType());
    }

    @Test
    void addMoreThanMaxCapacity() {
        enclosure.addCreature(this.dragon1);
        enclosure.addCreature(this.dragon2);
        enclosure.addCreature(this.dragon3);
        assertEquals(enclosure.getCreaturesPresent().size(), 3);
        enclosure.addCreature(new Dragon("Dragon", "male", 5, 5, 5));
        assertEquals(enclosure.getCreaturesPresent().size(), 3);
    }

    @Test
    void removeCreature() {
        enclosure.addCreature(dragon1);
        assertEquals(1, enclosure.getCreaturesPresent().size());
        enclosure.removeCreature(dragon1);
        assertEquals(0, enclosure.getCreaturesPresent().size());
    }

    @Test
    void removeDeadCreature() {
        enclosure.addCreature(dragon1);
        enclosure.addCreature(dragon2);
        assertEquals(2, enclosure.getCreaturesPresent().size());
        this.dragon1.die();
        assertEquals(1, enclosure.getCreaturesPresent().size());
        assertEquals("Dragon2", enclosure.getCreaturesPresent().get(0).getName());
    }

    @Test
    void feedCreatures() {
        enclosure.addCreature(dragon1);
        enclosure.addCreature(dragon2);
        enclosure.addCreature(dragon3);
        dragon1.setSatiety(10);
        dragon2.setSatiety(10);
        dragon3.setSatiety(10);
        enclosure.feedCreatures();
        assertTrue(dragon1.getSatiety() > 10);
        assertTrue(dragon2.getSatiety() > 10);
        assertTrue(dragon3.getSatiety() > 10);
    }

    @Test
    void enclosureGetDirty() {
        enclosure.setCleanlinessDegree(Enclosure.CleanlinessStatus.CLEAN);
        enclosure.getDirtier();
        assertEquals(Enclosure.CleanlinessStatus.DIRTY, enclosure.getCleanlinessDegree());
        enclosure.setCleanlinessDegree(Enclosure.CleanlinessStatus.UNSANITARY);
        enclosure.getDirtier();
        assertEquals(Enclosure.CleanlinessStatus.UNSANITARY, enclosure.getCleanlinessDegree());
    }

    @Test
    void cleanEnclosure() {
        enclosure.setCleanlinessDegree(Enclosure.CleanlinessStatus.DIRTY);
        enclosure.clean();
        assertEquals(Enclosure.CleanlinessStatus.SPOTLESS, enclosure.getCleanlinessDegree());
        enclosure.setCleanlinessDegree(Enclosure.CleanlinessStatus.SPOTLESS);
        enclosure.clean();
        assertEquals(Enclosure.CleanlinessStatus.SPOTLESS, enclosure.getCleanlinessDegree());
    }

    @Test
    void enclosureIsDelabred() {
        // TO DO -> animal get sick
    }

}