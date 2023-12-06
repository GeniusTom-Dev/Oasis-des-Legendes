package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.creature.Creature;

public interface ReproductionMethod {

    public void reproduce(Creature mother, int nbChild);
}
