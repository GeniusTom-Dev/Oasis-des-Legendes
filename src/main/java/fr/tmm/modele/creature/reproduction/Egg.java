package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.listener.CreatureListener;

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
     * Thread of the egg
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
