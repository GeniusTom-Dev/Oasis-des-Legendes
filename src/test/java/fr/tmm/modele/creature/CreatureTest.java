package fr.tmm.modele.creature;

import fr.tmm.modele.creature.species.Dragon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        creature.setSatiety(1);
        creature.eat();
        assertEquals(81, creature.getSatiety());
    }

    @Test
    @DisplayName("sleep()")
    void sleep() {
        creature.setEnergy(0);
        creature.sleep();
        assertEquals(5, creature.getEnergy());
    }

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
    @DisplayName("die()")
    void die() {
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(creature);
        creature.die(creature);
        assertEquals(0, creatures.size());
    }

}