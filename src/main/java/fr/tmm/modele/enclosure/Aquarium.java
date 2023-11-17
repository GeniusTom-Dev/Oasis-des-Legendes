package fr.tmm.modele.enclosure;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.methodOfMovement.Swimmer;

import java.util.Timer;
import java.util.TimerTask;

public class Aquarium extends Enclosure {
    private double waterDepth;
    private int salinityLevel;
    private int criticalStateDuration;
    private Timer criticalTimer;

    public Aquarium(String name, double surfaceArea, int maxCapacity, double waterDepth, int salinityLevel) {
        super(name, surfaceArea, maxCapacity);
        this.waterDepth = waterDepth;
        this.salinityLevel = salinityLevel;
        this.criticalStateDuration = 0;
        scheduleCriticalCheck();
    }

    public void ajouterCreature(Creature creature) {
        if (creature != null) {
            if (creature instanceof Swimmer) {
                creaturesPresent.add(creature);
                System.out.println(creature.getName() + " a été ajouté à l'aquarium " + name + ".");
            } else {
                System.out.println("Impossible d'ajouter " + creature.getName() + " à l'aquarium " + name + " car ce n'est pas une créature aquatique.");
            }
        } else {
            System.out.println("La créature est invalide.");
        }
    }

    private void scheduleCriticalCheck() {
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
    }

    private void deleteAquarium() {
        System.out.println("L'aquarium a été supprimé en raison d'un état critique pendant 5 minutes consécutives.");
    }

    public void adjustWaterDepth() {
        double decreaseRate = 0.1;
        waterDepth -= decreaseRate;

        if (waterDepth <= 0.0) {
            refillWater();
        }

        String depthState = determineDepthState();
        System.out.println("Le niveau d'eau est de " + depthState);
    }

    private String determineDepthState() {
        if (waterDepth >= 100) {
            return "EXCELLENT";
        } else if (waterDepth >= 80) {
            return "GOOD";
        } else if (waterDepth >= 60) {
            return "FAIR";
        } else if (waterDepth >= 40) {
            return "POOR";
        } else {
            return "CRITICAL";
        }
    }

    public void refillWater() {
        System.out.println("Remplissage de l'aquarium jusqu'à la profondeur maximale");
        waterDepth = 100.0;
    }

    public void adjustSalinityLevel() {
        int decreaseRate = 1;
        salinityLevel -= decreaseRate;

        if (salinityLevel <= 0) {
            refillWater();
        }

        String salinityState = determineSalinityState();
        System.out.println("Le niveau de salinité est de " + salinityState);
    }

    private String determineSalinityState() {
        if (salinityLevel >= 100) {
            return "EXCELLENT";
        } else if (salinityLevel >= 75) {
            return "GOOD";
        } else if (salinityLevel >= 50) {
            return "FAIR";
        } else {
            return "CRITICAL";
        }
    }

    public void refillSalinity() {
        System.out.println("Remplissage de l'aquarium jusqu'à la salinité maximale");
        salinityLevel = 100;
    }
}
