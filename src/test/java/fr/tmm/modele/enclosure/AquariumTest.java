package fr.tmm.modele.enclosure;

import fr.tmm.modele.creature.species.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class

AquariumTest {

    Aquarium aquarium;
    Dragon dragon1;

    @BeforeEach
    void init() {
        this.aquarium = new Aquarium("Aquarium", 50.0, 3);
        this.dragon1 = new Dragon("dragon", "m", 5, 5, 5);
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
        Aquarium aquarium2 = new Aquarium("Aquarium",50,5);
        aquarium2.setSalinityLevel(Aquarium.StateLevel.CRITICAL);
        aquarium2.setWaterLevel(Aquarium.StateLevel.EXCELLENT);
        aquarium2.addCreature(new Megalodon("Megalodon 1", "m",50,50,50));
        aquarium2.addCreature(new Megalodon("Dragon 2", "m",50,50,50));
        aquarium2.addCreature(new Megalodon("Dragon 3", "m",50,50,50));
        aquarium2.addCreature(new Megalodon("Dragon 4", "m",50,50,50));
        aquarium2.addCreature(new Megalodon("Dragon 5", "m",50,50,50));
        assertEquals(5, aquarium2.getCreaturesPresent().size());
        aquarium2.killCreatureDependingOfSalinityAndWaterLevel();
        assertTrue(aquarium2.creaturesPresent.size() < 5);
    }

    @Test
    void SalinityLevelIsExcellent() {
        Aquarium aquarium2 = new Aquarium("Aquarium",50,5);
        aquarium2.setSalinityLevel(Aquarium.StateLevel.EXCELLENT);
        aquarium2.setWaterLevel(Aquarium.StateLevel.EXCELLENT);
        aquarium2.addCreature(new Megalodon("Megalodon 1", "m",50,50,50));
        aquarium2.addCreature(new Megalodon("Megalodon 2", "m",50,50,50));
        aquarium2.addCreature(new Megalodon("Megalodon 3", "m",50,50,50));
        aquarium2.addCreature(new Megalodon("Megalodon 4", "m",50,50,50));
        aquarium2.addCreature(new Megalodon("Megalodon 5", "m",50,50,50));
        assertEquals(5, aquarium2.getCreaturesPresent().size());
        aquarium2.killCreatureDependingOfSalinityAndWaterLevel();
        assertEquals(5, aquarium2.creaturesPresent.size());
    }

    @Test
    void WaterLevelIsCritical() {
        Aquarium aquarium2 = new Aquarium("Aquarium",50,5);
        aquarium2.setWaterLevel(Aquarium.StateLevel.CRITICAL);
        aquarium2.setSalinityLevel(Aquarium.StateLevel.EXCELLENT);
        aquarium2.addCreature(new Megalodon("Megalodon 1","m",50,50,50));
        aquarium2.addCreature(new Megalodon("Megalodon 2", "m",50,50,50));
        aquarium2.addCreature(new Megalodon("Megalodon 3", "m",50,50,50));
        aquarium2.addCreature(new Megalodon("Megalodon 4", "m",50,50,50));
        aquarium2.addCreature(new Megalodon("Megalodon 5", "m",50,50,50));
        assertEquals(5, aquarium2.getCreaturesPresent().size());
        aquarium2.killCreatureDependingOfSalinityAndWaterLevel();
        assertTrue(aquarium2.creaturesPresent.size() < 5);
    }

    @Test
    void WaterLevelIsExcellent() {
        Aquarium aquarium2 = new Aquarium("Aquarium",50,5);
        aquarium2.setWaterLevel(Aquarium.StateLevel.EXCELLENT);
        aquarium2.setSalinityLevel(Aquarium.StateLevel.EXCELLENT);
        aquarium2.addCreature(dragon1);
        aquarium2.addCreature(new Dragon("Dragon 2", "m",50,50,50));
        aquarium2.addCreature(new Dragon("Dragon 3", "m",50,50,50));
        aquarium2.addCreature(new Dragon("Dragon 4", "m",50,50,50));
        aquarium2.addCreature(new Dragon("Dragon 5", "m",50,50,50));
        assertEquals(5, aquarium2.getCreaturesPresent().size());
        aquarium2.killCreatureDependingOfSalinityAndWaterLevel();
        assertEquals(5, aquarium2.creaturesPresent.size());
    }


    @Test
    void putIncompatibleCreatureInAquarium() {
        Unicorn unicorn = new Unicorn("mermaid", "m", 5, 5, 5);
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
        Phoenix phoenix = new Phoenix("phenix", "m", 5, 5, 5);
        aquarium.addCreature(this.dragon1);
        aquarium.addCreature(phoenix);
        assertEquals(aquarium.getCreaturesPresent().size(), 1);
        assertEquals("Dragon", aquarium.getCreaturesPresent().get(0).getType());
    }

    @Test
    void addMoreThanMaxCapacity() {
        Dragon dragon2 = new Dragon("cui", "m", 5, 5, 5);
        Dragon dragon3 = new Dragon("kje", "m", 5, 5, 5);
        aquarium.addCreature(this.dragon1);
        aquarium.addCreature(dragon2);
        aquarium.addCreature(dragon3);
        assertEquals(aquarium.getCreaturesPresent().size(), 3);
        aquarium.addCreature(new Dragon("Dragon", "male", 5, 5, 5));
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