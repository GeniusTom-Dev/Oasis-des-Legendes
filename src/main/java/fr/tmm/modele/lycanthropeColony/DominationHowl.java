package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.Log;
import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.species.Lycanthrope;

public class DominationHowl extends Howl {

    private Lycanthrope lycanTarget;

    public DominationHowl(Lycanthrope emetteur, Lycanthrope lycanTarget) {
        super(emetteur);
        this.lycanTarget = lycanTarget;
        this.lycanTarget.hearHowl(this);
        Log.getInstance().addLog(this.getEmetteur().getName() + " émet un hurlement de domination à l'encontre de " + lycanTarget.getName()+ ".");
        this.getEmetteur().attack(this.lycanTarget);
    }
}
