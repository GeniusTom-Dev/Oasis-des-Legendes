package fr.tmm.modele;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.species.Human;
import fr.tmm.modele.enclosure.Enclosure;

public class ZooMaster extends Human {
    public ZooMaster(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    public boolean transferer(Creature creature, Enclosure source, Enclosure dest) {
        if (creature == null || source == null || dest == null) {
            throw new IllegalArgumentException("One of the parameter is null");
        } else if (!source.getCreaturesPresent().contains(creature)) {
            throw new RuntimeException("The creature is not present is the source enclosure");
        } else if (dest.addCreature(creature)) {
            source.removeCreature(creature);
            Log.getInstance().addLog(creature.getName() + " a été transféré de l'enclos " + source.getName() + " à l'enclos " + dest.getName() + ".");
            return true;
        }
        return false;
    }

    public void cleanEnclosure(Enclosure enclos) {
        enclos.clean();
    }

    public void feedCreature(Enclosure enclos) {
        enclos.feedCreatures();
    }
}
