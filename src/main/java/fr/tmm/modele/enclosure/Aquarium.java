package fr.tmm.modele.enclosure;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.methodOfMovement.Swimmer;
import fr.tmm.modele.utils.Utils;

import java.util.ArrayList;

public class Aquarium extends Enclosure {
    private StateLevel salinityLevel;
    private StateLevel waterLevel;

    /**
     * Enumeration representing salinity and water levels
     */
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

    public Aquarium(String name, double surfaceArea, int maxCapacity) {
        super(name, surfaceArea, maxCapacity);
        this.salinityLevel = StateLevel.EXCELLENT;
        this.waterLevel = StateLevel.EXCELLENT;
    }

    /**
     * Check that a creature is a flyer and can be added to an enclosure
     * @param creature : the creature who is going to be checked
     * @return true if the creature is a flyer
     */
    @Override
    public boolean addCreature(Creature creature) {
        if (creature instanceof Swimmer) {
            return this.addCreatureThatMatchesEnclosureType(creature);
        }
        Log.getInstance().addLog("Impossible d'ajouter " + creature.getName() + " à l'aquarium " + name + " car " +
                "ce n'est pas une créature aquatique.");
        return false;
    }

    /**
     * Kill some creature depending of the salinity and water level
     */
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

    /**
     * Lower the salinity level
     */
    public void lowerSalinityLevel() {

        this.salinityLevel = Utils.getWorseState(this.salinityLevel);
    }

    /**
     * Set the salinity level to EXCELLENT
     */
    public void adjustSalinityLevel() {
        this.salinityLevel = StateLevel.EXCELLENT;
    }

    /**
     * Lower the salinity level
     */
    public void lowerWaterLevel() {

        this.waterLevel = Utils.getWorseState(this.waterLevel);
    }

    /**
     * Set the water level to EXCELLENT
     */
    public void refillWater() {
        this.waterLevel = StateLevel.EXCELLENT;
    }

    /**
     * Get the water level of the aquarium
     * @return water level of type StateLevel
     */
    public StateLevel getWaterLevel() {
        return this.waterLevel;
    }

    /**
     * Set the water level of the aquarium
     * @param newWaterLevel
     */
    public void setWaterLevel(StateLevel newWaterLevel) {
        this.waterLevel = newWaterLevel;
    }

    /**
     * Get the salinity level of the aquarium
     * @return salinity level of type StateLevel
     */
    public StateLevel getSalinityLevel() {
        return this.salinityLevel;
    }

    /**
     * Set the salinity level of the aquarium
     * @param newSalinityLevel
     */
    public void setSalinityLevel(StateLevel newSalinityLevel) {
        this.salinityLevel = newSalinityLevel;
    }

}
