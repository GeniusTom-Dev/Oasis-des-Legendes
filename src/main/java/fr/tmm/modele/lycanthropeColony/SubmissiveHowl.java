package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.species.Lycanthrope;

public class SubmissiveHowl extends Howl {

    private Lycanthrope dominant;

    public SubmissiveHowl(Lycanthrope emetteur, Lycanthrope dominant) {
        super(emetteur);
        this.dominant = dominant;
        Log.getInstance().addLog(this.getEmetteur().getName() + " se soumet à " + this.dominant.getName() + ".");
    }
}
