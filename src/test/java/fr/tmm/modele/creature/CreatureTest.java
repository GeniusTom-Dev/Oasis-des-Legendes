package fr.tmm.modele.creature;

import fr.tmm.modele.creature.methodOfMovement.Walker;
import fr.tmm.modele.creature.species.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {
    Creature creature;

    @BeforeEach
    void initialisation() {
        creature = new Dragon("Creature", "femelle", 80, 2, 5);
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
        //Field field = Creature.class.getDeclaredField("age");
        //field.setAccessible(true);
        //field.set(creature, new int(10));
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

    // --- Emettre un son

    @Test
    void makeNoise() {
        assertEquals("Creature émet un son puissant !", this.creature.makeNoise());
    }

    // Implementation

    @Test
    void checkImplementation() {
        Dragon dragon = new Dragon("", "", 5, 5, 5);
        Human human = new Human("", "", 5, 5, 5);
        Kraken kraken = new Kraken("", "", 5, 5, 5);
        Lycanthrope lycan = new Lycanthrope("", "", 5, 5, 5);
        Megalodon megalodon = new Megalodon("", "", 5, 5, 5);
        Mermaid mermaid = new Mermaid("", "", 5, 5, 5);
        Nymph nymph = new Nymph("", "", 5, 5, 5);
        Phenix phenix = new Phenix("", "", 5, 5, 5);
        Unicorn unicorn = new Unicorn("", "", 5, 5, 5);
        assertTrue(dragon instanceof Walker);
    }
}