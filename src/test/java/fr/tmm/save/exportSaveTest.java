package fr.tmm.save;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.ZooMaster;
import fr.tmm.modele.creature.species.Unicorn;
import fr.tmm.modele.enclosure.Enclosure;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class exportSaveTest {

    Zoo zoo;

    @BeforeEach
    void init() {
        this.zoo = Zoo.getInstance();
    }
    @Test
    void test1() throws IOException, JSONException {
        zoo.setZooMaster( new ZooMaster("Julot", "m", 10, 10, 19));
        zoo.setName("Zoo de Test");
        Enclosure enclos1 = new Enclosure("Enclos 1", 50, 5);
        enclos1.addCreature(new Unicorn("Licorne 1", "m", 10, 10, 10));
        enclos1.addCreature(new Unicorn("Licorne 2", "m", 10, 10, 10));
        zoo.addAnEnclosure(enclos1);
        ExportSave.exportSave("src/test/java/fr/tmm/save/actual1.json");
        byte[] file2Bytes = Files.readAllBytes(Paths.get("src/test/java/fr/tmm/save/actual1.json"));
        byte[] file1Bytes = Files.readAllBytes(Paths.get("src/test/java/fr/tmm/save/expected1.json"));

        String file1 = new String(file1Bytes, StandardCharsets.UTF_8);
        String file2 = new String(file2Bytes, StandardCharsets.UTF_8);
        assertEquals(file1, file2);
    }

    @Test
    void emptyCreature() throws IOException, JSONException {
        zoo.setZooMaster( new ZooMaster("Julot", "m", 10, 10, 19));
        zoo.setName("Zoo de Test");
        zoo.setEnclosures(new ArrayList<Enclosure>());
        Enclosure enclos1 = new Enclosure("Enclos 1", 50, 5);
        zoo.addAnEnclosure(enclos1);
        ExportSave.exportSave("src/test/java/fr/tmm/save/actual2.json");
        byte[] file2Bytes = Files.readAllBytes(Paths.get("src/test/java/fr/tmm/save/actual2.json"));
        byte[] file1Bytes = Files.readAllBytes(Paths.get("src/test/java/fr/tmm/save/expected2.json"));

        String file1 = new String(file1Bytes, StandardCharsets.UTF_8);
        String file2 = new String(file2Bytes, StandardCharsets.UTF_8);
        assertEquals(file1, file2);
    }

    @Test
    void emptyEnclosure() throws IOException, JSONException {
        zoo.setZooMaster( new ZooMaster("Julot", "m", 10, 10, 19));
        zoo.setName("Zoo de Test");
        zoo.setEnclosures(new ArrayList<Enclosure>());
        ExportSave.exportSave("src/test/java/fr/tmm/save/actual3.json");
        byte[] file2Bytes = Files.readAllBytes(Paths.get("src/test/java/fr/tmm/save/actual3.json"));
        byte[] file1Bytes = Files.readAllBytes(Paths.get("src/test/java/fr/tmm/save/expected3.json"));

        String file1 = new String(file1Bytes, StandardCharsets.UTF_8);
        String file2 = new String(file2Bytes, StandardCharsets.UTF_8);
        assertEquals(file1, file2);
    }


/*    @Test
    void test2() throws IOException, JSONException {
        zoo.setZooMaster( new ZooMaster("Julot", "m", 10, 10, 19));
        zoo.setName("Zoo de Test");
        Enclosure enclos1 = new Enclosure("Enclos 1", 50, 5);
        Aquarium aquarium1 = new Aquarium("Aquarium 1", 50, 5, 20, 4);
        Aviary voiliere1 = new Aviary("Voiliere 1", 50, 7);
        enclos1.addCreature(new Unicorn("Licorne 1", "m", 10, 10, 10));
        enclos1.addCreature(new Unicorn("Licorne 2", "m", 10, 10, 10));
        enclos1.addCreature(new Unicorn("Licorne 3", "m", 10, 10, 10));
        aquarium1.addCreature(new Megalodon("Magalodon 1", "m", 10, 10, 10));
        aquarium1.addCreature(new Megalodon("Magalodon 2", "m", 10, 10, 10));
        voiliere1.addCreature(new Phenix("Phenix 1", "m", 10, 10, 10));
        zoo.addAnEnclosure(enclos1);
        zoo.addAnEnclosure(aquarium1);
        zoo.addAnEnclosure(voiliere1);
        ExportSave.export(this.zoo, "src/test/java/fr/tmm/save/actual1.json");
        byte[] file2Bytes = Files.readAllBytes(Paths.get("src/test/java/fr/tmm/save/actual1.json"));
        byte[] file1Bytes = Files.readAllBytes(Paths.get("src/test/java/fr/tmm/save/expected1.json"));

        String file1 = new String(file1Bytes, StandardCharsets.UTF_8);
        String file2 = new String(file2Bytes, StandardCharsets.UTF_8);
        assertEquals(file1, file2);
    }*/
}