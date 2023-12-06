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

    public void startBecomePregnantThread() {
        this.isPregnant = true;
        this.gestationCounter = Gestation.getValue(this.creature.getType());
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (this.gestationCounter > 0) {
            try {
                Thread.sleep(1000);
                System.out.println("Gestation Counter : " + this.gestationCounter);
                this.gestationCounter--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Fin de la gestation de " + this.creature.getName());
        this.isPregnant = false;
        this.reproductionMethod.reproduce(creature, NbChildren.determineChildrenNb(creature.getType()));
    }

    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }

    public ReproductionMethod getReproductionMethod() {
        return reproductionMethod;
    }

    @Override
    public String toString() {
        return "Female";
    }
}
