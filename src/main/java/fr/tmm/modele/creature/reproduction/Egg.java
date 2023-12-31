package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.listener.CreatureListener;
import fr.tmm.modele.creature.reproduction.data.Incubation;

public class Egg implements Runnable {
    private Creature mother;
    private int timeBeforeHatching;
    private CreatureListener listener;

    public Egg(Creature mother) {
        this.mother = mother;
        this.timeBeforeHatching = Incubation.getValue(mother.getType());
        this.listener = mother.getListener();
        Thread t = new Thread(this);
        t.start();
    }

    public String getType() {
        return this.mother.getType();
    }

    /**
     * The run method for the hatching thread. It decrements the time before hatching at regular intervals,
     * and upon reaching zero, invokes the onEggHatching method of the associated listener.
     * The hatching process is executed in a separate thread to simulate the passage of time.
     * @throws RuntimeException If the thread is interrupted unexpectedly.
     */
    @Override
    public void run() {
        while (timeBeforeHatching > 0) {
            try {
                Thread.sleep(3000);
                this.timeBeforeHatching -= 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.listener.onEggHatching(this);
    }

    public CreatureListener getListener() {
        return listener;
    }

    public Creature getMother() {return this.mother;}
}
