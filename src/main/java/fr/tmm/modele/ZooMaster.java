package fr.tmm.modele;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.species.Human;
import fr.tmm.modele.enclosure.Enclosure;

public class ZooMaster extends Human implements Runnable {

    private final int MAX_ACTIONS = 3;
    private int actionsLeft = 3;
    public ZooMaster(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
        Thread t = new Thread();
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                if (this.actionsLeft < MAX_ACTIONS) {
                    addAnAction();
                    Thread.sleep(60000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Transfer a creature from an enclosure to another
     *
     * @param creature : the creature to moved
     * @param source   : the current enclosure of the creature
     * @param dest     : the future enclosure of the creature
     */
    public void moveCreature(Creature creature, Enclosure source, Enclosure dest) {
        if (isThereActionLeft()) {
            if (creature == null || source == null || dest == null) {
                throw new IllegalArgumentException("One of the parameter is null");
            } else if (!source.getCreaturesPresent().contains(creature)) {
                throw new RuntimeException("The creature is not present is the source enclosure");
            } else if (dest.addCreature(creature)) {
                source.removeCreature(creature);
                removeAnAction();
                Log.getInstance().addLog(creature.getName() + " a été transféré de l'enclos " + source.getName() + " à l'enclos " + dest.getName() + ".");
            }
        }
    }

    /**
     * Clean an enclosure
     * @param enclos : The enclosure that the zookeeper wants to clean
     */
    public void cleanEnclosure(Enclosure enclos) {
        if (isThereActionLeft()) {
            enclos.clean();
            removeAnAction();
        }
    }

    /**
     * Feed the creature of an enclosure
     * @param enclos : The enclosure that the zookeeper wants to feed the creatures
     */
    public void feedEnclosure(Enclosure enclos) {
        if (isThereActionLeft()) {
            enclos.feedCreatures();
            removeAnAction();
        }
    }

    public boolean isThereActionLeft() {
        return this.actionsLeft > 0;
    }

    public void addAnAction() {
        if (this.actionsLeft < MAX_ACTIONS) {
            this.actionsLeft += 1;
        }
    }

    public void removeAnAction() {
        if (isThereActionLeft()) {
            this.actionsLeft -=1;
        }
    }

    public int getMAX_ACTIONS() {
        return MAX_ACTIONS;
    }
}
