package fr.tmm.modele;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.species.Human;
import fr.tmm.modele.enclosure.Enclosure;

public class ZooMaster extends Human {
    public ZooMaster(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    /**
     * Transfer a creature from an enclosure to another
     *
     * @param creature : the creature to moved
     * @param source   : the current enclosure of the creature
     * @param dest     : the future enclosure of the creature
     */
    public void transferer(Creature creature, Enclosure source, Enclosure dest) {
        if (creature == null || source == null || dest == null) {
            throw new IllegalArgumentException("One of the parameter is null");
        } else if (!source.getCreaturesPresent().contains(creature)) {
            throw new RuntimeException("The creature is not present is the source enclosure");
        } else if (dest.addCreature(creature)) {
            source.removeCreature(creature);
            Log.getInstance().addLog(creature.getName() + " a été transféré de l'enclos " + source.getName() + " à l'enclos " + dest.getName() + ".");
        }
    }

    /**
     * Clean an enclosure
     * @param enclos : The enclosure that the zookeeper wants to clean
     */
    public void cleanEnclosure(Enclosure enclos) {
        enclos.clean();
    }

    /**
     * Feed the creature of an enclosure
     * @param enclos : The enclosure that the zookeeper wants to feed the creatures
     */
    public void feedCreature(Enclosure enclos) {
        enclos.feedCreatures();
    }
}
