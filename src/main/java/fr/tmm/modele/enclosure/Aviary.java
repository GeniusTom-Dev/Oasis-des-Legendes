package fr.tmm.modele.enclosure;

import java.util.Timer;
import java.util.TimerTask;

public class Aviary extends Enclosure {
    private String roofState;

    public Aviary(String name, double surfaceArea, int maxCapacity) {
        super(name, surfaceArea, maxCapacity);
        this.roofState = "Neuf";
        scheduleRoofCheck();
    }

    public void ajouterCreature(Creature creature) {
        if (creature != null) {
            if (creature instanceof Flyer) {
                creaturesPresent.add(creature);
                System.out.println(creature.getName() + " a été ajouté à la volière " + name + ".");
            } else {
                System.out.println("Impossible d'ajouter " + creature.getName() + " à la volière " + name +
                        " car ce n'est pas une créature volante.");
            }
        } else {
            System.out.println("La créature est invalide.");
        }
    }

    private void scheduleRoofCheck() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkRoofState();
            }
        }, 0, 5 * 60 * 1000);
    }

    private void checkRoofState() {
        alterRoofState();
        System.out.println("L'état du toit est : " + roofState);

        if (roofState.equals("CRITICAL")) {
            Timer criticalTimer = new Timer();
            criticalTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    deleteAviary();
                }
            }, 5 * 60 * 1000);
        }
    }

    private void alterRoofState() {
        double randomValue = Math.random();

        if (roofState.equals("EXCELLENT")) {
            if (randomValue > 0.5) {
                roofState = "GOOD";
            } else if (randomValue > 0.2) {
                roofState = "FAIR";
            } else {
                roofState = "CRITICAL";
            }
        } else if (roofState.equals("GOOD")) {
            if (randomValue > 0.7) {
                roofState = "FAIR";
            } else if (randomValue > 0.3) {
                roofState = "CRITICAL";
            }
        } else if (roofState.equals("FAIR")) {
            if (randomValue > 0.8) {
                roofState = "CRITICAL";
            }
        }
    }

    private void deleteAviary() {
        System.out.println("La volière a été supprimée en raison de l'état critique du toit.");
    }
}
