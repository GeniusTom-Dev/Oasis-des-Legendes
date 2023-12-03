package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;

public abstract class Viviparous extends Creature {
    public Viviparous(String nomEspece, String sexe, double poids, double taille, int age) {
        super(nomEspece, sexe, poids, taille, age);
    }

    public String calve(int nbChild) {
        try {
            if (this.getSex().equals("Femelle")) {
                String strChild = nbChild > 1 ? " enfants." : "enfant.";
                Log.getInstance().addLog(this.getName() + ", une femelle " + this.getType() + ", vient de mettre bas " + nbChild + strChild);
            } else {
                throw new Exception("Les mâles ne peuvent pas mettre bas");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    ;
}
