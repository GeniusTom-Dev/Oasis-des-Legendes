package fr.tmm.modele.creature;

import fr.tmm.modele.creature.species.Dragon;
import fr.tmm.modele.creature.species.Megalodon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class CreatureThreadTest {

   /* Dragon creature;
    Megalodon mega;

    @BeforeEach
    void initialisation() {
        creature = new Dragon("Dragon", "femelle", 80, 2, 5);
        mega = new Megalodon("Megalodon", "femelle", 80, 2, 5);
    }*/

    @Test
    public void basicAction() throws Exception {
        Dragon creature = new Dragon("Dragon", "femelle", 80, 2, 5);
        Megalodon mega = new Megalodon("Megalodon", "femelle", 80, 2, 5);
        creature.setSatiety(1);
        creature.getHealthindicator().setSick(true);
        sleep(7);
        assertEquals(creature.getEnergy(), 99);
        assertEquals(creature.getSatiety(), 0);
        assertEquals(creature.getHealth(), 88); // 96 because -2 for starving and -2 for being sick
    }

    @Test
    public void die() throws Exception {
        Dragon creature = new Dragon("Dragon", "femelle", 80, 5, 5);
        Megalodon mega = new Megalodon("Megalodon", "femelle", 80, 5, 5);
        creature.setAge(99);
        mega.setAge(99);
        sleep(35);
        assertTrue(creature.isAlive() && creature.getAge() < 2 && creature.getHeight() < 2);
        assertFalse(mega.isAlive());
    }



    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
