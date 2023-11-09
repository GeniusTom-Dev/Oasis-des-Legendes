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
        creature = new Dragon("Creature", "femelle", 80,2,5);
    }

    @Test
    @DisplayName("eat()")
    void eat() {
        creature.setHunger(1);
        creature.eat();
        assertEquals(100, creature.getHunger());
    }

    @Test
    @DisplayName("sleep()")
    void sleep() {
        creature.setSleepiness(100);
        creature.sleep();
        creature.wakeUp();
        assertEquals(0, creature.getSleepiness());
    }

    @Test
    @DisplayName("vieillir()")
    void vieillir() {
        //Field field = Creature.class.getDeclaredField("age");
        //field.setAccessible(true);
        //field.set(creature, new int(10));
        creature.setAge(5);
        creature.aging();
        assertEquals(6, creature.getAge());
    }

}