package fr.tmm.modele.creature;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.species.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {
    Creature creature;

    @BeforeEach
    void initialisation() {
        creature = new Dragon("Creature", "Female", 80, 2, 5);
    }

    // --- Satiety ---

    @Test
    @DisplayName("Eat")
    void eat() {
        creature.setSatiety(1);
        creature.eat();
        assertEquals(81, creature.getSatiety());
    }

    @Test
    @DisplayName("Get Hungry")
    void getHungry() {
        creature.setSatiety(90);
        creature.getHungrier();
        assertTrue(creature.getSatiety() < 90);
    }

    @Test
    void UnableToEatWhileSleeping() {
        creature.setEnergy(0);
        assertTrue(creature.isAsleep());
        creature.setSatiety(50);
        creature.eat();
        assertEquals(50, creature.getSatiety());
    }

    // --- Energy ---

    @Test
    @DisplayName("sleep()")
    void sleep() {
        creature.setEnergy(0);
        creature.sleep();
        assertTrue(creature.getEnergy() > 0);
    }

    @Test
    @DisplayName("Falling asleep")
    void fallingAsleep() {
        creature.setEnergy(1);
        creature.getSleepier();
        assertTrue(creature.isAsleep());
    }

    @Test
    @DisplayName("Waking Up")
    void wakeUp() {
        creature.setEnergy(0);
        assertTrue(creature.isAsleep());
        creature.setEnergy(99);
        creature.sleep();
        assertFalse(creature.isAsleep());
    }

    // --- Age ---

    @Test
    @DisplayName("aging()")
    void aging() {
        creature.setAge(5);
        creature.aging();
        assertEquals(6, creature.getAge());
    }

    @Test
    void die() {
        creature.setHealth(1);
        assertTrue(creature.isAlive());
        creature.getHealthindicator().decrement(1);
        assertFalse(creature.isAlive());
    }

    @Test
    void reincarnation() {
        Phoenix phoenix = new Phoenix("Phenix","m",100,50,50);
        Dragon dragon = new Dragon("Dragon", "m",100,50,50);
        phoenix.die();
        dragon.die();
        assertTrue(phoenix.isAlive());
        assertTrue(dragon.isAlive());
        assertEquals(0, phoenix.getAge());
        assertTrue(phoenix.getHeight() < 50);
        assertTrue(phoenix.getWeight() < 100);
        assertEquals(0, dragon.getAge());
        assertTrue(dragon.getHeight() < 50);
        assertTrue(dragon.getWeight() < 100);

    }

    // --- Health ---

    @Test
    void heal() {
        creature.getHealthindicator().setSick(true);
        creature.setHealth(50);
        creature.heal();
        assertFalse(creature.getHealthindicator().isSick());
        assertEquals(100, creature.getHealth());
    }

    // --- Emettre un son

    @Test
    void makeNoise() {
        this.creature.makeNoise();
        assertEquals("Creature émet un son puissant !", Log.getInstance().getLastLog());
    }

}