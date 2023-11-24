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
        this.dragon1 = new Dragon("Drag", "male", 50, 50, 50);
        this.dragon2 = new Dragon("Drag", "male", 50, 50, 50);
        this.dragon3 = new Dragon("Drag", "male", 50, 50, 50);
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
        enclosure.setCleanlinessDegree(Enclosure.cleanlinessStatus.clean);
        enclosure.getDirty();
        assertEquals(Enclosure.cleanlinessStatus.dirty, enclosure.getCleanlinessDegree());
        enclosure.setCleanlinessDegree(Enclosure.cleanlinessStatus.unsanitary);
        enclosure.getDirty();
        assertEquals(Enclosure.cleanlinessStatus.unsanitary, enclosure.getCleanlinessDegree());
    }

    @Test
    void cleanEnclosure() {
        enclosure.setCleanlinessDegree(Enclosure.cleanlinessStatus.dirty);
        enclosure.clean();
        assertEquals(Enclosure.cleanlinessStatus.spotless, enclosure.getCleanlinessDegree());
        enclosure.setCleanlinessDegree(Enclosure.cleanlinessStatus.spotless);
        enclosure.clean();
        assertEquals(Enclosure.cleanlinessStatus.spotless, enclosure.getCleanlinessDegree());
    }

    @Test
    void enclosureIsDelabred() {
        // TO DO -> animal get sick
    }

}