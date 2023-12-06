package fr.tmm.save;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.reproduction.Female;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ImportSaveTest {

    static Zoo zoo;

    @BeforeAll
    static void init() {
        zoo = Zoo.getInstance();
        ImportSave.importSave("src/test/java/fr/tmm/save/expected1.json");
    }

    @Test
    void testZooMaster() throws FileNotFoundException, JSONException {

        assertEquals(zoo.getZooMaster().getSex().toString(), "Male");
        assertEquals(zoo.getZooMaster().getName(), "Julot");
        assertEquals(zoo.getZooMaster().getWeight(), 10);
        assertEquals(zoo.getZooMaster().getHeight(), 19);
        assertEquals(zoo.getZooMaster().getAge(), 10);
    }

    @Test
    void zoo() {
        assertEquals(zoo.getName(), "Zoo de Test");
    }

    @Test
    void enclosures() {
        assertEquals(zoo.getEnclosures().size(), 1);
        assertEquals(zoo.getEnclosures().get(0).getSurfaceArea(), 50);
        assertEquals(zoo.getEnclosures().get(0).getMaxCapacity(), 5);
        assertEquals(zoo.getEnclosures().get(0).getName(), "Enclos 1");
        assertEquals(zoo.getEnclosures().get(0).getClass().getSimpleName(), "Enclosure");
        assertEquals(zoo.getEnclosures().get(0).getCleanlinessDegree().toString(), "CLEAN");
    }

    @Test
    void licorne1() {
        assertEquals(zoo.getAllCreatures().get(0).getName(), "Licorne 1");
        assertEquals(zoo.getAllCreatures().get(0).getSex().toString(), "Male");
        assertEquals(zoo.getAllCreatures().get(0).getHeight(), 10);
        assertEquals(zoo.getAllCreatures().get(0).getWeight(), 10);
        assertEquals(zoo.getAllCreatures().get(0).getAge(), 10);
        assertEquals(zoo.getAllCreatures().get(0).getType(), "Unicorn");
        assertEquals(zoo.getAllCreatures().get(0).getHealth(), 100);
        assertEquals(zoo.getAllCreatures().get(0).getSatiety(), 100);
        assertEquals(zoo.getAllCreatures().get(0).getEnergy(), 100);
    }

    @Test
    void licorne2() {
        assertEquals(zoo.getAllCreatures().get(1).getName(), "Licorne 2");
        assertEquals(zoo.getAllCreatures().get(1).getSex().toString(), "Female");
        assertEquals(zoo.getAllCreatures().get(1).getHeight(), 10);
        assertEquals(zoo.getAllCreatures().get(1).getWeight(), 10);
        assertEquals(zoo.getAllCreatures().get(1).getAge(), 10);
        assertEquals(zoo.getAllCreatures().get(1).getType(), "Unicorn");
        assertEquals(zoo.getAllCreatures().get(1).getHealth(), 100);
        assertEquals(zoo.getAllCreatures().get(1).getSatiety(), 100);
        assertEquals(zoo.getAllCreatures().get(1).getEnergy(), 100);
        assertFalse(((Female) zoo.getAllCreatures().get(1).getSex()).isPregnant());
        assertEquals(((Female) zoo.getAllCreatures().get(1).getSex()).getGestationCounter(), 0);
    }

}