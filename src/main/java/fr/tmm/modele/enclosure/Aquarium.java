package fr.tmm.modele.enclosure;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.methodOfMovement.Swimmer;
import fr.tmm.modele.utils.Utils;

import java.util.ArrayList;

/*import java.util.Timer;
import java.util.TimerTask;*/

public class Aquarium extends Enclosure {
    private double waterDepth;
    private StateLevel salinityLevel;
    private StateLevel waterLevel;

    public enum StateLevel {
        CRITICAL(50), BAD(10), GOOD(0), EXCELLENT(0);

        private int riskOfDying;
        StateLevel(int i) {
            this.riskOfDying = i;
        }

        public int getRiskOfDying() {
            return riskOfDying;
        }
    }

    public Aquarium(String name, double surfaceArea, int maxCapacity, double waterDepth, int salinityLevel) {
        super(name, surfaceArea, maxCapacity);
        this.waterDepth = waterDepth;
        this.salinityLevel = StateLevel.EXCELLENT;
        this.waterLevel = StateLevel.EXCELLENT;
        /*this.criticalStateDuration = 0;
        scheduleCriticalCheck();*/
    }

    @Override
    public boolean addCreature(Creature creature) {
        if (creature instanceof Swimmer) {
            return super.addCreature(creature);
        }
        return false;
    }

    public void killCreatureDependingOfSalinityAndWaterLevel() {
        if (this.salinityLevel.riskOfDying != 0 || this.waterLevel.riskOfDying != 0) {
            ArrayList<Creature> deadCreatures = new ArrayList<>();
            for (Creature creature : this.getCreaturesPresent()) {
                if (Utils.isBadEventHappening(this.salinityLevel.riskOfDying) || Utils.isBadEventHappening(this.waterLevel.riskOfDying)) {
                    deadCreatures.add(creature);
                }
            }
            for (Creature creature: deadCreatures) {
                creature.die();
            }
        }
    }

    public void lowerSalinityLevel() {
        this.salinityLevel = getWorseState(this.salinityLevel);
    }

    public void adjustSalinityLevel() {
        this.salinityLevel = StateLevel.EXCELLENT;
    }

    public void lowerWaterLevel() {
        this.waterLevel = getWorseState(this.waterLevel);
    }

    public void refillWater() {
        this.waterLevel = StateLevel.EXCELLENT;
    }

    private StateLevel getWorseState(StateLevel level) {
        StateLevel[] statuses = StateLevel.values();
        for (int i = 0; i < statuses.length; i++) {
            if (statuses[i] == level && i > 0) {
                return statuses[i - 1];
            }
        }
        return level;
    }

    public StateLevel getWaterLevel() {
        return this.waterLevel;
    }

    public void setWaterLevel(StateLevel newWaterLevel) {
        this.waterLevel = newWaterLevel;
    }

    public StateLevel getSalinityLevel() {
        return this.salinityLevel;
    }

    public void setSalinityLevel(StateLevel newSalinityLevel) {
        this.salinityLevel = newSalinityLevel;
    }

/*    private void scheduleCriticalCheck() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkState();
            }
        }, 0, 1 * 60 * 1000);
    }

    private void checkState() {
        String depthState = determineDepthState();
        String salinityState = determineSalinityState();

        if (depthState.equals("CRITICAL") || salinityState.equals("CRITICAL")) {
            criticalStateDuration++;
            if (criticalStateDuration >= 5) {
                deleteAquarium();
            }
        } else {
            criticalStateDuration = 0;
        }
    }*/

    /*private void deleteAquarium() {
        System.out.println("L'aquarium a été supprimé en raison d'un état critique pendant 5 minutes consécutives.");
    }*/

//    public void adjustWaterDepth() {
//        double decreaseRate = 0.1;
//        waterDepth -= decreaseRate;
//
//        if (waterDepth <= 0.0) {
//            refillWater();
//        }
//
//        String depthState = determineDepthState();
//        System.out.println("Le niveau d'eau est de " + depthState);
//    }
//
//    private String determineDepthState() {
//        if (waterDepth >= 100) {
//            return "EXCELLENT";
//        } else if (waterDepth >= 80) {
//            return "GOOD";
//        } else if (waterDepth >= 60) {
//            return "FAIR";
//        } else if (waterDepth >= 40) {
//            return "POOR";
//        } else {
//            return "CRITICAL";
//        }
//    }
//
//    public void refillWater() {
//        System.out.println("Remplissage de l'aquarium jusqu'à la profondeur maximale");
//        waterDepth = 100.0;
//    }
//
//    public void adjustSalinityLevel() {
//        int decreaseRate = 1;
//        salinityLevel -= decreaseRate;
//
//        if (salinityLevel <= 0) {
//            refillWater();
//        }
//
//        String salinityState = determineSalinityState();
//        System.out.println("Le niveau de salinité est de " + salinityState);
//    }
//
//    private String determineSalinityState() {
//        if (salinityLevel >= 100) {
//            return "EXCELLENT";
//        } else if (salinityLevel >= 75) {
//            return "GOOD";
//        } else if (salinityLevel >= 50) {
//            return "FAIR";
//        } else {
//            return "CRITICAL";
//        }
//    }
//
//    public void refillSalinity() {
//        System.out.println("Remplissage de l'aquarium jusqu'à la salinité maximale");
//        salinityLevel = 100;
//    }
}
