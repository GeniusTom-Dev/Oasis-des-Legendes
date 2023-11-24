package fr.tmm.modele.enclosure;

import fr.tmm.modele.creature.species.Dragon;
import fr.tmm.modele.creature.species.Mermaid;
import fr.tmm.modele.creature.species.Phenix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AviaryTest {

    Aviary aviary;
    Dragon dragon1;

    @BeforeEach
    void init() {
        this.aviary = new Aviary("Aviary", 50.0, 3);
        this.dragon1 = new Dragon("dragon", "m", 5, 5, 5);
    }

    @Test
    void roofGetDamaged() {
        this.aviary.setRoofState(Aviary.RoofState.GOOD);
        this.aviary.damageRoof();
        assertEquals(Aviary.RoofState.DAMAGED.toString(), this.aviary.getRoofState().toString());
        this.aviary.setRoofState(Aviary.RoofState.BROKEN);
        this.aviary.damageRoof();
        assertEquals(Aviary.RoofState.BROKEN, this.aviary.getRoofState());
    }


    @Test
    void putIncompatibleCreatureInAviary() {
        Mermaid mermaid = new Mermaid("mermaid", "m", 5, 5, 5);
        this.aviary.addCreature(mermaid);
        assertEquals(aviary.getCreaturesPresent().size(), 0);
    }

    @Test
    void addCreature() {
        aviary.addCreature(this.dragon1);
        assertEquals(aviary.getCreaturesPresent().size(), 1);
    }

    @Test
    void addIncompatibleCreature() {
        Phenix phenix = new Phenix("phenix", "m", 5, 5, 5);
        aviary.addCreature(this.dragon1);
        aviary.addCreature(phenix);
        assertEquals(aviary.getCreaturesPresent().size(), 1);
        assertEquals("Dragon", aviary.getCreaturesPresent().get(0).getType());
    }

    @Test
    void addMoreThanMaxCapacity() {
        Dragon dragon2 = new Dragon("cui", "m", 5, 5, 5);
        Dragon dragon3 = new Dragon("kje", "m", 5, 5, 5);
        aviary.addCreature(this.dragon1);
        aviary.addCreature(dragon2);
        aviary.addCreature(dragon3);
        assertEquals(aviary.getCreaturesPresent().size(), 3);
        aviary.addCreature(new Dragon("Dragon", "male", 5, 5, 5));
        assertEquals(aviary.getCreaturesPresent().size(), 3);
    }

    @Test
    void removeCreature() {
        aviary.addCreature(dragon1);
        assertEquals(1, aviary.getCreaturesPresent().size());
        aviary.removeCreature(dragon1);
        assertEquals(0, aviary.getCreaturesPresent().size());
    }


}