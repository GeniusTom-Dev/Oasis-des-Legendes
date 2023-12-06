package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;

public class LayEgg implements ReproductionMethod{

    @Override
    public void reproduce(Creature mother, int nbChild) {
        this.layEgg(mother, nbChild);
    }

    public void layEgg(Creature mother, int nbEgg) {
        String strEgg = nbEgg > 1 ? " œufs." : "œuf.";
        Log.getInstance().addLog(mother.getName() + ", une femelle " + mother.getType() + ", vient de pondre " + nbEgg + strEgg);
        for (int i = 0; i < nbEgg; i++) {
            mother.getListener().onEggLaying(new Egg(mother));
        }
    }
}
