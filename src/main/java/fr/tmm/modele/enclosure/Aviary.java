package fr.tmm.modele.enclosure;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.methodOfMovement.Flyer;

public class Aviary extends Enclosure {
    private RoofState roofState;

    public enum RoofState {
        BROKEN(50), // 50% chance of a creature escaping
        DAMAGED(20), // 20% chance of a creature escaping
        GOOD(0),
        INTACT(0);
        private int riskOfEvasion;

        RoofState(int i) {
            this.riskOfEvasion = i;
        }
    }

    public Aviary(String name, double surfaceArea, int maxCapacity) {
        super(name, surfaceArea, maxCapacity);
        this.roofState = RoofState.INTACT;
        /*scheduleRoofCheck();*/
    }

    @Override
    public void addCreature(Creature creature) {
        if (creature instanceof Flyer) {
            super.addCreature(creature);
        } else {
            System.out.println("Impossible d'ajouter " + creature.getName() + " à la volière " + name +
                    " car ce n'est pas une créature volante.");
        }
    }

    public void repareRoof() {
        this.roofState = RoofState.INTACT;
    }

    public void damageRoof() {
        this.roofState = getWorseRoofState();
    }

    private RoofState getWorseRoofState() {
        RoofState[] statuses = RoofState.values();
        for (int i = 0; i < statuses.length; i++) {
            if (statuses[i] == roofState && i > 0) {
                return statuses[i - 1];
            }
        }
        return roofState;
    }

    public RoofState getRoofState() {
        return this.roofState;
    }

    public void setRoofState(RoofState newRoofState) {
        this.roofState = newRoofState;
    }

 /*   private void scheduleRoofCheck() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkRoofState();
            }
        }, 0, 5 * 60 * 1000);
    }*/

/*
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
*/

/*    private void alterRoofState() {
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
    }*/
}
