package fr.tmm.modele.creature.species;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.enclosure.Enclosure;
import fr.tmm.modele.lycanthropeColony.Pack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LycanthropeTest {

    Lycanthrope lycan1;
    Lycanthrope lycan2;
    Lycanthrope lycan3;
    Pack pack;
    Enclosure enclos;

    @BeforeEach
    void init() {
        lycan1 = new Lycanthrope("Lycan 1", "Male", 50, 50,50);
        lycan2 = new Lycanthrope("Lycan 2", "Female", 50, 50,50);
        lycan3 = new Lycanthrope("Lycan 3", "Female", 50, 50,50);
        pack = new Pack(lycan1, lycan2);
        pack.addLycanthrope(lycan3);
        Zoo.getInstance().getColony().addPack(pack);
        enclos = new Enclosure("Un enclos",50,5);
        enclos.addCreature(lycan1);
        enclos.addCreature(lycan2);
    }

    @Test
    void removeFromPack() {
        assertEquals(pack.getLycanthropes().size(), 3);
        lycan3.separatingFromPack();
        assertEquals(pack.getLycanthropes().size(),2);
    }

}