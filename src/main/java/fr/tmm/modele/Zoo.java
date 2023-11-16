package fr.tmm.modele;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.enclosure.Enclosure;

import java.util.ArrayList;

public class Zoo {

    public ArrayList<Enclosure> getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(ArrayList<Enclosure> enclosure) {
        this.enclosure = enclosure;
    }

    public ArrayList<Enclosure> enclosure;

    public ArrayList<Creature> getAllCreatures() {
        return new ArrayList<>();
    }

}
