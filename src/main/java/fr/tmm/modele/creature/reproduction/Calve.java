package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.reproduction.data.BabySize;

public class Calve implements ReproductionMethod{
    @Override
    public void reproduce(Creature mother, int nbChild) {
        this.calve(mother, nbChild);
    }

    /**
     * Initiates the calving process for a given mother creature, producing a specified number of offspring.
     *
     * @param mother   The mother creature initiating the calving process.
     * @param nbChild  The number of offspring to be born.
     * @throws IllegalArgumentException If the number of offspring is less than or equal to zero.
     * This method logs the calving event and generates the specified number of offspring. For each offspring,
     * it determines their weight and height based on the type of the mother creature, and then invokes the
     * {@code onCreatureBirth} method of the mother's listener, passing the newly born creature.
     * The log entry includes details such as the mother's name, type, and the number of offspring born.
     */
    public void calve(Creature mother, int nbChild) {
        if (nbChild >= 0) throw new IllegalArgumentException("Le nombre d'enfants ne peut pas être inférieur à 1.");
        String strChild = nbChild > 1 ? " enfants." : "enfant.";
        Log.getInstance().addLog(mother.getName() + ", une femelle " + mother.getType() + ", vient de mettre bas " + nbChild + strChild);
        for (int i = 0; i < nbChild; i++) {
            double babyWeight = BabySize.Weight.determineFromType(mother.getType());
            double babyHeight = BabySize.Height.determineFromType(mother.getType());
            mother.getListener().onCreatureBirth(mother.born(babyWeight, babyHeight));
        }
    }
}
