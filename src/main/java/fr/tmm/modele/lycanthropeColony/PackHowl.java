package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.Log;
import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.species.Lycanthrope;

import java.util.ArrayList;

public class PackHowl extends Howl {

    ArrayList<Lycanthrope> lycanWhoAlreadyRespond = new ArrayList<>();

    public PackHowl(Lycanthrope emetteur) {
        super(emetteur);
        Log.getInstance().addLog(emetteur.getName() + " pousse un hurlement d'appartenance.");

        // Determine if a lycan from another pack respond
        for (Pack pack : Zoo.getInstance().getColony().getPacks()) {
            if (!pack.equals(Zoo.getInstance().getColony().getPackFromLycan(this.getEmetteur()))) {
                if(Math.random() > 0.8) {
                    new PackHowl(pack.getRancomLycanthrpe());
                }
            }
        }
        // Make all the lycan from the same pack respond
        Pack lycanPack = Zoo.getInstance().getColony().getPackFromLycan(this.getEmetteur());
        for (Lycanthrope lycan : lycanPack.getLycanthropes()) {
            if (!lycan.equals(this.getEmetteur()) && !this.lycanWhoAlreadyRespond.contains(lycan)) {
                lycan.hearHowl(this);
                this.lycanWhoAlreadyRespond.add(lycan);
            }
        }
    }

}
