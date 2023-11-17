package fr.tmm.modele;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.enclosure.Enclosure;

import java.util.ArrayList;

public class Zoo {

    private String name;
    private ZooMaster zooMaster;
    final private int NB_MAX_ENCLOSURE = 10;
    private ArrayList<Enclosure> enclosures;

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

    public int addAnEnclosure(Enclosure enclosure) {
        if (this.enclosures.size() < NB_MAX_ENCLOSURE) {
            this.enclosures.add(enclosure);
            return 0;
        }
        return -1;
    }

    public ArrayList<Creature> getAllCreatures() {
        ArrayList<Creature> creatures = new ArrayList<>();
        for (Enclosure enclo : this.enclosures) {
            creatures.addAll(enclo.getCreaturesPresent());
        }
        return creatures;
    }
}
