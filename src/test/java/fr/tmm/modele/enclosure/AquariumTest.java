package fr.tmm.modele.enclosure;

import fr.tmm.modele.creature.species.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AquariumTest {

    Aquarium aquarium;
    Dragon dragon1;

    @BeforeEach
    void init() {
        this.aquarium = new Aquarium("Aquarium", 50.0, 3, 50, 5);
        this.dragon1 = new Dragon("dragon", "m", 5, 5, 5, null);
    }

    @Test
    void salinityGetLower() {
        this.aquarium.setSalinityLevel(Aquarium.StateLevel.GOOD);
        this.aquarium.lowerSalinityLevel();
        assertEquals(Aquarium.StateLevel.BAD, this.aquarium.getSalinityLevel());
        this.aquarium.setSalinityLevel(Aquarium.StateLevel.CRITICAL);
        this.aquarium.lowerSalinityLevel();
        assertEquals(Aquarium.StateLevel.CRITICAL, this.aquarium.getSalinityLevel());
    }

    @Test
    void waterGetLower() {
        this.aquarium.setWaterLevel(Aquarium.StateLevel.GOOD);
        this.aquarium.lowerWaterLevel();
        assertEquals(Aquarium.StateLevel.BAD, this.aquarium.getWaterLevel());
        this.aquarium.setWaterLevel(Aquarium.StateLevel.CRITICAL);
        this.aquarium.lowerWaterLevel();
        assertEquals(Aquarium.StateLevel.CRITICAL, this.aquarium.getWaterLevel());
    }

    @Test
    void adjustSalinityLevel() {
        this.aquarium.setSalinityLevel(Aquarium.StateLevel.BAD);
        this.aquarium.adjustSalinityLevel();
        assertEquals(Aquarium.StateLevel.EXCELLENT, this.aquarium.getSalinityLevel());
        this.aquarium.setSalinityLevel(Aquarium.StateLevel.EXCELLENT);
        this.aquarium.adjustSalinityLevel();
        assertEquals(Aquarium.StateLevel.EXCELLENT, this.aquarium.getSalinityLevel());
    }

    @Test
    void refillWater() {
        this.aquarium.setWaterLevel(Aquarium.StateLevel.BAD);
        this.aquarium.refillWater();
        assertEquals(Aquarium.StateLevel.EXCELLENT, this.aquarium.getWaterLevel());
        this.aquarium.setWaterLevel(Aquarium.StateLevel.EXCELLENT);
        this.aquarium.refillWater();
        assertEquals(Aquarium.StateLevel.EXCELLENT, this.aquarium.getWaterLevel());
    }

    @Test
    void SalinityLevelIsCritical() {
        // TO DO
    }

    @Test
    void WaterLevelIsCritical() {
        // TO DO
    }


    @Test
    void putIncompatibleCreatureInAquarium() {
        Unicorn unicorn = new Unicorn("mermaid", "m", 5, 5, 5, null);
        this.aquarium.addCreature(unicorn);
        assertEquals(aquarium.getCreaturesPresent().size(), 0);
    }

    @Test
    void addCreature() {
        aquarium.addCreature(this.dragon1);
        assertEquals(aquarium.getCreaturesPresent().size(), 1);
    }

    @Test
    void addIncompatibleCreature() {
        Phenix phenix = new Phenix("phenix", "m", 5, 5, 5, null);
        aquarium.addCreature(this.dragon1);
        aquarium.addCreature(phenix);
        assertEquals(aquarium.getCreaturesPresent().size(), 1);
        assertEquals("Dragon", aquarium.getCreaturesPresent().get(0).getType());
    }

    @Test
    void addMoreThanMaxCapacity() {
        Dragon dragon2 = new Dragon("cui", "m", 5, 5, 5, null);
        Dragon dragon3 = new Dragon("kje", "m", 5, 5, 5, null);
        aquarium.addCreature(this.dragon1);
        aquarium.addCreature(dragon2);
        aquarium.addCreature(dragon3);
        assertEquals(aquarium.getCreaturesPresent().size(), 3);
        aquarium.addCreature(new Dragon("Dragon", "male", 5, 5, 5, null));
        assertEquals(aquarium.getCreaturesPresent().size(), 3);
    }

    @Test
    void removeCreature() {
        aquarium.addCreature(dragon1);
        assertEquals(1, aquarium.getCreaturesPresent().size());
        aquarium.removeCreature(dragon1);
        assertEquals(0, aquarium.getCreaturesPresent().size());
    }

}