package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.species.Lycanthrope;

public class AgressivityHowl extends Howl {

    private Lycanthrope target;

    public AgressivityHowl(Lycanthrope emetteur, Lycanthrope target) {
        super(emetteur);
        this.target = target;
        Log.getInstance().addLog(this.getEmetteur().getName() + " agresse " + this.target.getName()+".");
    }
}
