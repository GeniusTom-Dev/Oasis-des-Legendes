package fr.tmm.modele.enclosure;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.species.Dragon;
import fr.tmm.modele.creature.species.Nymph;
import org.junit.jupiter.api.BeforeAll;
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
        enclosure.ajouterCreature(this.dragon1);
        assertEquals(enclosure.getCreaturesPresent().size(), 1);
    }

    @Test
    void addIncompatibleCreature() {
        enclosure.ajouterCreature(this.dragon1);
        enclosure.ajouterCreature(this.nymph);
        assertEquals(enclosure.getCreaturesPresent().size(), 1);
        assertTrue(enclosure.getCreaturesPresent().get(0).getType() == "Dragon");
    }

    @Test
    void addMoreThanMaxCapacity() {
        enclosure.ajouterCreature(this.dragon1);
        enclosure.ajouterCreature(this.dragon2);
        enclosure.ajouterCreature(this.dragon3);
        assertEquals(enclosure.getCreaturesPresent().size(), 3);
        enclosure.ajouterCreature(new Dragon("effe", "firhf", 5, 5, 5));
        assertEquals(enclosure.getCreaturesPresent().size(), 3);
    }

    @Test
    void putIncompatibleCreatureInAquarium() {
        Aquarium aquarium = new Aquarium("klrv", 75, 5);
        aquarium.ajouterCreature(this.nymph);
        assertEquals(aquarium.getCreaturesPresent().size(), 0);
    }

    @Test
    void removeCreature() {
        enclosure.removeCreature(dragon1);
        assertEquals(enclosure.getCreaturesPresent().size(), 1);
    }

    @Test
    void feedCreatures() {
        // TO DO
    }

    @Test
    void enclosureGetDirty() {
        // TO DO
    }

    @Test
    void cleanEnclosure() {

    }

    @Test
    void enclosureIsDelabred() {
        // TO DO -> animal get sick
    }

}