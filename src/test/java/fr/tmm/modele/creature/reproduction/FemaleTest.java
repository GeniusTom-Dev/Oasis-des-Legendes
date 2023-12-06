package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.reproduction.data.Gestation;
import fr.tmm.modele.creature.species.Mermaid;
import fr.tmm.modele.enclosure.Aquarium;
import fr.tmm.modele.enclosure.Enclosure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class FemaleTest {

    Enclosure enclos;
    Mermaid mermaid;

    @BeforeEach
    void init() {
        this.mermaid = new Mermaid("Une sirène", "f", 50,50,50);
        this.mermaid.setSex("Femelle");
        this.enclos = new Aquarium("Aquarium", 50,5);
        this.enclos.addCreature(mermaid);
    }

    @Test
    void startBecomePregnantThread() throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        ((Female) mermaid.getSex()).startBecomePregnantThread();

        // Female is pregnant
        assertTrue(((Female) mermaid.getSex()).isPregnant());
        // The gestation counter has the correct value
        assertEquals(((Female) mermaid.getSex()).getGestationCounter(), Gestation.MERMAID.getGestationTime());

        // Forced the end of pregnancy
        Field gestationCounter = Female.class.getDeclaredField("gestationCounter");
        gestationCounter.setAccessible(true);
        gestationCounter.set(this.mermaid.getSex(), 5);
        sleep(6);

        // Female is not pregnant anymore
        assertFalse(((Female) mermaid.getSex()).isPregnant());
        for (String s: Log.getInstance().getLogs()) {
            System.out.println(s);
        }
        // Child is born
        assertTrue(this.enclos.getCreaturesPresent().size() > 1);
        assertTrue(this.enclos.getCreaturesPresent().get(1).getAge() == 0);
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}