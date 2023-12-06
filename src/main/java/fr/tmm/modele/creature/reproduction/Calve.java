package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.reproduction.data.BabySize;

public class Calve implements ReproductionMethod{
    @Override
    public void reproduce(Creature mother, int nbChild) {
        this.calve(mother, nbChild);
    }

    public void calve(Creature mother, int nbChild) {
        String strChild = nbChild > 1 ? " enfants." : "enfant.";
        Log.getInstance().addLog(mother.getName() + ", une femelle " + mother.getType() + ", vient de mettre bas " + nbChild + strChild);
        for (int i = 0; i < nbChild; i++) {
            double babyWeight = BabySize.Weight.determineFromType(mother.getType());
            double babyHeight = BabySize.Height.determineFromType(mother.getType());
            mother.getListener().onCreatureBirth(mother.born(babyWeight, babyHeight));
        }
    }
}
