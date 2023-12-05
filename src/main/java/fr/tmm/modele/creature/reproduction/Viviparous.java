package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;

public abstract class Viviparous extends Creature {

    private int gestationCounter;
    public Viviparous(String nomEspece, String sexe, double poids, double taille, int age) {
        super(nomEspece, sexe, poids, taille, age);
        this.gestationCounter = Gestation.getValue(this.getType());
    }

    public void startBecomePregnantThread() {
        Thread becomePregnantThread = new Thread(() -> {
            while (gestationCounter > 0) {
                try {
                    Thread.sleep(1000);
                    gestationCounter--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            calve(NbChildren.determineChildrenNb(this.getType()));
        });
        becomePregnantThread.start();
    }
    public String calve(int nbChild) {
        try {
            if (this.getSex().equals("Femelle")) {
                String strChild = nbChild > 1 ? " enfants." : "enfant.";
                Log.getInstance().addLog(this.getName() + ", une femelle " + this.getType() + ", vient de mettre bas " + nbChild + strChild);
                for (int i = 0; i < nbChild; i++) {
                    double babyWeight = BabySize.Weight.determineFromType(type);
                    double babyHeight = BabySize.Height.determineFromType(type);
                    this.listener.onCreatureBirth(this.born(babyWeight, babyHeight));
                }
            } else {
                throw new Exception("Les mâles ne peuvent pas mettre bas");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

}
