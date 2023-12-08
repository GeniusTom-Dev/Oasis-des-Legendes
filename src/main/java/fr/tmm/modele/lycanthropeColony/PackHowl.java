package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.Log;
import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.species.Lycanthrope;

public class PackHowl extends Howl {
    private Lycanthrope emetteur;

    public PackHowl(Lycanthrope emetteur) {
        this.emetteur = emetteur;
        Log.getInstance().addLog(emetteur.getName() + " pousse un hurlement d'appartenance.");

        // Determine if a lycan from another pack respond
        for (Pack pack : Zoo.getInstance().getColony().getPacks()) {
            if (!pack.equals(Zoo.getInstance().getColony().getPackFromLycan(this.emetteur))) {
                if(Math.random() > 0.8) {
                    new PackHowl(pack.getRancomLycanthrpe());
                }
            }
        }
        // Make all the lycan from the same pack respond
        for (Lycanthrope lycan : Zoo.getInstance().getColony().getPackFromLycan(this.emetteur).getLycanthropes()) {
            if (!lycan.equals(this.emetteur)) {
                Log.getInstance().addLog(lycan.getName() + " a repondu au hurlement de " + this.emetteur.getName()+ ".");
            }
        }
    }

}
