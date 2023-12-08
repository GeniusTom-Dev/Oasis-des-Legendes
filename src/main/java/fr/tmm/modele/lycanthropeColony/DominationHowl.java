package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.species.Lycanthrope;

public class DominationHowl extends Howl {

    private Lycanthrope lycanTarget;

    public DominationHowl(Lycanthrope lycanTarget) {
        this.lycanTarget = lycanTarget;
        this.lycanTarget.hearHowl(this);
    }
}
