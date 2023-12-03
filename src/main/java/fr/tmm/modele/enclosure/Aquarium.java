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

}
