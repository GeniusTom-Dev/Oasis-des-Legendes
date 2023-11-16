package fr.tmm.modele.creature;

import fr.tmm.modele.creature.species.Dragon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

/*    @Test
    @DisplayName("die()")
    void die() {
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(creature);
        creature.die(creature);
        assertEquals(0, creatures.size());
    }*/

}