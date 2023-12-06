package fr.tmm.modele.enclosure;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.reproduction.Female;
import fr.tmm.modele.creature.species.Dragon;
import fr.tmm.modele.creature.species.Megalodon;
import fr.tmm.modele.creature.species.Nymph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class EnclosureTest {

    Enclosure enclosure;
    Dragon dragon1;
    Dragon dragon2;
    Dragon dragon3;
    Nymph nymph;

    @BeforeEach
    void initialisation() {
        this.enclosure = new Enclosure("Un Enclos", 80.5, 3);
        this.dragon1 = new Dragon("Dragon1", "male", 50, 50, 50);
        this.dragon2 = new Dragon("Dragon2", "male", 50, 50, 50);
        this.dragon3 = new Dragon("Dragon3", "male", 50, 50, 50);
        this.nymph = new Nymph("Nymph", "male", 50, 50, 50);
    }
    @Test
    void creatureGetBySex() {
        enclosure.addCreature(dragon1);
        enclosure.addCreature(dragon2);
        enclosure.addCreature(dragon3);
        dragon1.setSex("Female");
        dragon2.setSex("Female");
        dragon2.setSex("Male");
        assertEquals(enclosure.getCreaturesBySex("Female").size(), 2);
        assertEquals(enclosure.getCreaturesBySex("Male").size(), 1);
    }

    @Test
    void checkIfReproductionWork() throws NoSuchFieldException, IllegalAccessException {
        enclosure.addCreature(dragon1);
        enclosure.addCreature(dragon2);
        dragon1.setSex("Female");
        dragon2.setSex("Male");

        // reproduction normally is launched and complete
        sleep(6); // launch reproduction

        assertTrue(((Female) dragon1.getSex()).isPregnant());

        Field gestationCounter = Female.class.getDeclaredField("gestationCounter");
        gestationCounter.setAccessible(true);
        gestationCounter.set(this.dragon1.getSex(), 0); // gestation is completed

        Log.getInstance().showLogs();
        // check if birth was successful
        assertFalse(enclosure.getEggWaitingToHatch().isEmpty());
        //assertEquals(0, enclosure.getCreaturesPresent().get(2).getAge());
    }

    @Test
    void addCreature() {
        enclosure.addCreature(this.dragon1);
        assertEquals(enclosure.getCreaturesPresent().size(), 1);
    }

    @Test
    void addTwoDifferentCreature() {
        enclosure.addCreature(this.dragon1);
        enclosure.addCreature(this.nymph);
        assertEquals(enclosure.getCreaturesPresent().size(), 1);
        assertEquals("Dragon", enclosure.getCreaturesPresent().get(0).getType());
    }

    @Test
    void addIncompatibleCreature() {
        enclosure.addCreature(new Megalodon("Mega", "m",50,50,50));
        assertEquals(this.enclosure.getCreaturesPresent().size(), 0);
    }

    @Test
    void addMoreThanMaxCapacity() {
        enclosure.addCreature(this.dragon1);
        enclosure.addCreature(this.dragon2);
        enclosure.addCreature(this.dragon3);
        assertEquals(enclosure.getCreaturesPresent().size(), 3);
        enclosure.addCreature(new Dragon("Dragon", "male", 5, 5, 5));
        assertEquals(enclosure.getCreaturesPresent().size(), 3);
    }

    @Test
    void removeCreature() {
        enclosure.addCreature(dragon1);
        assertEquals(1, enclosure.getCreaturesPresent().size());
        enclosure.removeCreature(dragon1);
        assertEquals(0, enclosure.getCreaturesPresent().size());
    }

    @Test
    void removeDeadCreature() {
        Nymph nymph2 = new Nymph("Nymph2", "f",50,50,50);
        enclosure.addCreature(nymph);
        enclosure.addCreature(nymph2);
        assertEquals(2, enclosure.getCreaturesPresent().size());
        this.nymph.die();
        assertEquals(1, enclosure.getCreaturesPresent().size());
        assertEquals("Nymph2", enclosure.getCreaturesPresent().get(0).getName());
    }

    @Test
    void feedCreatures() {
        enclosure.addCreature(dragon1);
        enclosure.addCreature(dragon2);
        enclosure.addCreature(dragon3);
        dragon1.setSatiety(10);
        dragon2.setSatiety(10);
        dragon3.setSatiety(10);
        enclosure.feedCreatures();
        assertTrue(dragon1.getSatiety() > 10);
        assertTrue(dragon2.getSatiety() > 10);
        assertTrue(dragon3.getSatiety() > 10);
    }

    @Test
    void enclosureGetDirty() {
        enclosure.setCleanlinessDegree(Enclosure.CleanlinessStatus.CLEAN);
        enclosure.getDirtier();
        assertEquals(Enclosure.CleanlinessStatus.DIRTY, enclosure.getCleanlinessDegree());
        enclosure.setCleanlinessDegree(Enclosure.CleanlinessStatus.UNSANITARY);
        enclosure.getDirtier();
        assertEquals(Enclosure.CleanlinessStatus.UNSANITARY, enclosure.getCleanlinessDegree());
    }

    @Test
    void cleanEnclosure() {
        enclosure.setCleanlinessDegree(Enclosure.CleanlinessStatus.DIRTY);
        enclosure.clean();
        assertEquals(Enclosure.CleanlinessStatus.SPOTLESS, enclosure.getCleanlinessDegree());
        enclosure.setCleanlinessDegree(Enclosure.CleanlinessStatus.SPOTLESS);
        enclosure.clean();
        assertEquals(Enclosure.CleanlinessStatus.SPOTLESS, enclosure.getCleanlinessDegree());
    }

    @Test
    void enclosureIsDelabred() {
        Enclosure enclos2 = new Enclosure("Enclos",50,5);
        enclos2.setCleanlinessDegree(Enclosure.CleanlinessStatus.UNSANITARY);
        enclos2.addCreature(this.dragon1);
        enclos2.addCreature(this.dragon2);
        enclos2.addCreature(this.dragon3);
        enclos2.addCreature(new Dragon("Dragon4", "male", 50, 50, 50));
        enclos2.addCreature(new Dragon("Dragon5", "male", 50, 50, 50));
        int cmp = 0;
        enclos2.makeCreatureSickDependingOfCleanliness();
        for (Creature creature:enclos2.getCreaturesPresent()) {
            if (creature.isSick()) ++cmp;
        }
        assertTrue(cmp > 0);
    }

    @Test
    void enclosureIsSpotless() {
        enclosure.setCleanlinessDegree(Enclosure.CleanlinessStatus.SPOTLESS);
        enclosure.addCreature(this.dragon1);
        enclosure.addCreature(this.dragon2);
        enclosure.addCreature(this.dragon3);
        int cmp = 0;
        enclosure.makeCreatureSickDependingOfCleanliness();
        for (Creature creature:enclosure.getCreaturesPresent()) {
            if (creature.isSick()) ++cmp;
        }
        assertEquals(0, cmp);
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}