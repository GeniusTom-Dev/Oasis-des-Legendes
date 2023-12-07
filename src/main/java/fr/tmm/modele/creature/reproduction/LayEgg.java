package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;

public class LayEgg implements ReproductionMethod{

    @Override
    public void reproduce(Creature mother, int nbChild) {
        this.layEgg(mother, nbChild);
    }

    /**
     * Initiates the egg-laying process for a given mother creature, producing a specified number of eggs.
     *
     * @param mother   The mother creature initiating the egg-laying process.
     * @param nbEgg    The number of eggs to be laid.
     * @throws IllegalArgumentException If the number of eggs is less than or equal to zero.
     * This method logs the egg-laying event and generates the specified number of eggs. For each egg,
     * it invokes the {@code onEggLaying} method of the mother's listener, passing the newly laid egg.
     * The log entry includes details such as the mother's name, type, and the number of eggs laid.
     */
    public void layEgg(Creature mother, int nbEgg) {
        if (nbEgg < 1) throw new IllegalArgumentException("Le nombre d'oeufs ne peut pas être inférieur à 1.");
        String strEgg = nbEgg > 1 ? " œufs." : "œuf.";
        Log.getInstance().addLog(mother.getName() + ", une femelle " + mother.getType() + ", vient de pondre " + nbEgg + strEgg);
        for (int i = 0; i < nbEgg; i++) {
            mother.getListener().onEggLaying(new Egg(mother));
        }
    }
}
