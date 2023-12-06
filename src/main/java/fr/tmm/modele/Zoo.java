package fr.tmm.modele;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.species.Megalodon;
import fr.tmm.modele.creature.species.Phoenix;
import fr.tmm.modele.creature.species.Unicorn;
import fr.tmm.modele.enclosure.Aquarium;
import fr.tmm.modele.enclosure.Aviary;
import fr.tmm.modele.enclosure.Enclosure;

import java.util.ArrayList;

public class Zoo {

    private String name;
    private ZooMaster zooMaster;
    final private int NB_MAX_ENCLOSURE = 10;
    private ArrayList<Enclosure> enclosures = new ArrayList<>();
    private static Zoo INSTANCE;

    private Zoo() {}

    /**
     * Get the unique instance of the class Zoo
     * @return the instance
     */
    public static Zoo getInstance() {
        if (INSTANCE == null) {
            // TODO -> demander les infos pour creer le maitre du zoo et le nom du zoo
            INSTANCE = new Zoo();
        }
        return INSTANCE;
    }

    /**
     * Simulate a zoo with enclosures and creatures
     */
    public void temporaryInit() {
        this.zooMaster = new ZooMaster("Julo", "m", 10, 10, 19);
        this.name = "Zoo de Test";
        Enclosure enclos1 = new Enclosure("Enclos 1", 50, 5);
        Aquarium aquarium1 = new Aquarium("Aquarium 1", 50, 5);
        Aviary voiliere1 = new Aviary("Voiliere 1", 50, 7);
        enclos1.addCreature(new Unicorn("Licorne 1", "m", 10, 10, 10));
        enclos1.addCreature(new Unicorn("Licorne 2", "m", 10, 10, 10));
        enclos1.addCreature(new Unicorn("Licorne 3", "m", 10, 10, 10));
        aquarium1.addCreature(new Megalodon("Magalodon 1", "m", 10, 10, 10));
        aquarium1.addCreature(new Megalodon("Magalodon 2", "m", 10, 10, 10));
        voiliere1.addCreature(new Phoenix("Phenix 1", "m", 10, 10, 10));
        this.addAnEnclosure(enclos1);
        this.addAnEnclosure(aquarium1);
        this.addAnEnclosure(voiliere1);
    }

    // NAME

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    // ZOO MASTER

    public ZooMaster getZooMaster() {
        return this.zooMaster;
    }

    // NB MAX ENCLOSURE

    public int getNbMaxEnclosure() {
        return this.NB_MAX_ENCLOSURE;
    }

    // Enclosures

    public ArrayList<Enclosure> getEnclosures() {
        return this.enclosures;
    }

    public void setEnclosures(ArrayList<Enclosure> enclosures) {
        this.enclosures = enclosures;
    }

    /**
     * Add an enclosure to the zoo
     * @param enclosure : The enclosure that will be added to the zoo
     */
    public void addAnEnclosure(Enclosure enclosure) {
        if (this.enclosures.size() < NB_MAX_ENCLOSURE) {
            this.enclosures.add(enclosure);
        }
    }

    /**
     * Get all the creature of the zoo
     * @return an arrayList with all the creature of the zoo
     */
    public ArrayList<Creature> getAllCreatures() {
        ArrayList<Creature> creatures = new ArrayList<>();
        for (Enclosure enclo : this.enclosures) {
            creatures.addAll(enclo.getCreaturesPresent());
        }
        return creatures;
    }

    public void setZooMaster(ZooMaster zooMaster) {
        this.zooMaster = zooMaster;
    }
}
