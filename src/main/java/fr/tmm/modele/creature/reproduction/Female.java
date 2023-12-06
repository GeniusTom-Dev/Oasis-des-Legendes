package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.Oviparous;
import fr.tmm.modele.creature.Viviparous;
import fr.tmm.modele.creature.reproduction.data.Gestation;
import fr.tmm.modele.creature.reproduction.data.NbChildren;

public class Female extends Sex implements Runnable {

    private final Creature creature;
    private boolean isPregnant = false;
    public int getGestationCounter() {
        return gestationCounter;
    }
    private int gestationCounter = 0;
    private ReproductionMethod reproductionMethod;

    public Female(Creature creature) {
        this.creature = creature;
        if (creature instanceof Oviparous) {
            this.reproductionMethod = new LayEgg();
        } else if (creature instanceof Viviparous) {
            this.reproductionMethod = new Calve();
        }
    }

    /**
     * Initiates a thread for the pregnancy process, allowing the creature to become pregnant.
     * This method sets the pregnancy status to true, initializes the gestation counter based on the
     * creature's type, and starts a new thread to handle the pregnancy process.
     */
    public void startBecomePregnantThread() {
        this.isPregnant = true;
        this.gestationCounter = Gestation.getValue(this.creature.getType());
        Thread t = new Thread(this);
        t.start();
    }

    /**
     * The run method for the pregnancy thread. It decrements the gestation counter at regular intervals
     * and, upon reaching zero, marks the creature as not pregnant and initiates the reproduction process.
     * The reproduction process involves invoking the reproduce method of the associated reproduction strategy
     * with the creature and the determined number of children based on the creature's type.
     */
    @Override
    public void run() {
        while (this.gestationCounter > 0) {
            try {
                Thread.sleep(1000);
                this.gestationCounter--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.isPregnant = false;
        this.reproductionMethod.reproduce(creature, NbChildren.determineChildrenNb(creature.getType()));
    }

    /**
     * Checks if the creature is currently pregnant.
     *
     * @return {@code true} if the creature is pregnant, {@code false} otherwise.
     */
    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }
    public void setGestationCounter(int counter) {this.gestationCounter = counter;}

    public ReproductionMethod getReproductionMethod() {
        return reproductionMethod;
    }

    @Override
    public String toString() {
        return "Female";
    }
}
