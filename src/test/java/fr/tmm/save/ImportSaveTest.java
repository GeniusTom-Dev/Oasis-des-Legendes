package fr.tmm.save;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ImportSaveTest {

    Zoo zoo;

    @BeforeEach
    void init() {
        this.zoo = Zoo.getInstance();
    }

    @Test
    void test1() throws FileNotFoundException, JSONException {
        ImportSave.importSave("src/test/java/fr/tmm/save/expected1.json");
        assertEquals(zoo.getZooMaster().getSex(), "m");
        assertEquals(zoo.getZooMaster().getName(), "Julot");
        assertEquals(zoo.getZooMaster().getWeight(), 10);
        assertEquals(zoo.getZooMaster().getHeight(), 19);
        assertEquals(zoo.getZooMaster().getAge(), 10);
        assertEquals(zoo.getNbMaxEnclosure(), 10);
        assertEquals(zoo.getName(), "Zoo de Test");
        assertEquals(zoo.getEnclosures().size(), 1);
        assertEquals(zoo.getEnclosures().get(0).getSurfaceArea(), 50);
        assertEquals(zoo.getEnclosures().get(0).getMaxCapacity(), 5);
        assertEquals(zoo.getEnclosures().get(0).getName(), "Enclos 1");
        assertEquals(zoo.getEnclosures().get(0).getClass().getSimpleName(), "Enclosure");
        assertEquals(zoo.getEnclosures().get(0).getCleanlinessDegree().toString(), "SPOTLESS");
        assertEquals(zoo.getAllCreatures().get(0).getName(), "Licorne 1");
        assertEquals(zoo.getAllCreatures().get(0).getSex(), "m");
        assertEquals(zoo.getAllCreatures().get(0).getHeight(), 10);
        assertEquals(zoo.getAllCreatures().get(0).getWeight(), 10);
        assertEquals(zoo.getAllCreatures().get(0).getAge(), 10);
        assertEquals(zoo.getAllCreatures().get(0).getType(), "Unicorn");
        assertEquals(zoo.getAllCreatures().get(0).getHealth(), 100);
        assertEquals(zoo.getAllCreatures().get(0).getSatiety(), 100);
        assertEquals(zoo.getAllCreatures().get(0).getEnergy(), 100);
        assertEquals(zoo.getAllCreatures().get(0).getName(), "Licorne 2");
        assertEquals(zoo.getAllCreatures().get(0).getSex(), "m");
        assertEquals(zoo.getAllCreatures().get(0).getHeight(), 10);
        assertEquals(zoo.getAllCreatures().get(0).getWeight(), 10);
        assertEquals(zoo.getAllCreatures().get(0).getAge(), 10);
        assertEquals(zoo.getAllCreatures().get(0).getType(), "Unicorn");
        assertEquals(zoo.getAllCreatures().get(0).getHealth(), 100);
        assertEquals(zoo.getAllCreatures().get(0).getSatiety(), 100);
        assertEquals(zoo.getAllCreatures().get(0).getEnergy(), 100);
    }

}