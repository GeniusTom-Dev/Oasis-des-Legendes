package fr.tmm.modele;
import fr.tmm.modele.creature.species.Mermaid;
import fr.tmm.modele.enclosure.Aquarium;
import fr.tmm.modele.enclosure.Aviary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZooMasterTest {

    Aquarium source;
    Aquarium dest;
    Mermaid mermaid;
    Zoo zoo;
    ZooMaster zooMaster;

    @BeforeEach
    void init() {
        this.mermaid = new Mermaid("Mermaid", "f",50,50,10);
        this.dest = new Aquarium("Dest", 50,50);
        this.source = new Aquarium("Source",50,50);
        this.source.addCreature(mermaid);
        this.zoo = Zoo.getInstance();
        this.zooMaster = new ZooMaster("Mathieu", "m", 75,1.76,19);
        zoo.addAnEnclosure(source);
        zoo.addAnEnclosure(dest);
        zoo.setZooMaster(zooMaster);
    }

    @Test
    void feedCreature() {
        this.mermaid.setSatiety(50);
        this.zooMaster.feedCreature(source);
        assertTrue(this.mermaid.getSatiety() > 50);
    }

    @Test
    void cleanEnclosure() {
        this.dest.getDirtier();
        assertEquals(this.dest.getCleanlinessDegree().toString(), "CLEAN");
        this.zooMaster.cleanEnclosure(dest);
        assertEquals(this.dest.getCleanlinessDegree().toString(), "SPOTLESS");
    }

    @Test
    void normalTransfert() {
        assertEquals(this.source.getCreaturesPresent().size(),1);
        assertEquals(this.dest.getCreaturesPresent().size(), 0);
        this.zooMaster.transferer(mermaid, source, dest);
        assertEquals(this.source.getCreaturesPresent().size(),0);
        assertEquals(this.dest.getCreaturesPresent().size(), 1);
    }

    @Test
    void incompatibleTransfert() {
        Aviary aviary = new Aviary("Aviary",50,50);
        assertEquals(this.source.getCreaturesPresent().size(),1);
        assertEquals(aviary.getCreaturesPresent().size(), 0);
        this.zooMaster.transferer(mermaid, source, aviary);
        assertEquals(this.source.getCreaturesPresent().size(),1);
        assertEquals(aviary.getCreaturesPresent().size(), 0);
    }
 }