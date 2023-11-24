package fr.tmm.modele.enclosure;

import fr.tmm.modele.creature.species.Nymph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AquariumTest {

    Nymph nymph;


    @Test
    void putIncompatibleCreatureInAquarium() {
        Aquarium aquarium = new Aquarium("Aquarium", 75.0, 5, 12.0, 5);
        aquarium.addCreature(this.nymph);
        assertEquals(aquarium.getCreaturesPresent().size(), 0);
    }

 /*   @Test
    void repareRoof() {
        bvc
    }*/

}