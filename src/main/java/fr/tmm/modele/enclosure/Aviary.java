package fr.tmm.modele.enclosure;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.methodOfMovement.Flyer;
import fr.tmm.modele.utils.Utils;

import java.util.ArrayList;

public class Aviary extends Enclosure {
    private RoofState roofState;

    /**
     * Enumeration representing the state of the aviary roof
     */
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
    }

    /**
     * Check that a creature is a swimmer and can be added to an enclosure
     * @param creature : the creature who is going to be checked
     * @return true if the creature is a swimmer
     */
    @Override
    public boolean addCreature(Creature creature) {
        if (creature instanceof Flyer) {
            return super.addCreatureThatMatchesEnclosureType(creature);
        }
        Log.getInstance().addLog("Impossible d'ajouter " + creature.getName() + " à la voilière " + name +
                    " car ce n'est pas une créature volantes.");
        return false;
    }

    /**
     * Allow some creature to escape depending of the roof state
     */
    public void chanceOfEscapingDependingRoofState() {
        if (this.roofState.riskOfEvasion != 0) {
            ArrayList<Creature> escapedCreature = new ArrayList<>();
            for (Creature creature : this.getCreaturesPresent()) {
                if (Utils.isBadEventHappening(this.roofState.riskOfEvasion)) {
                    escapedCreature.add(creature);
                    Log.getInstance().addLog(creature.getName() + " s'est échappé.");
                }
            }
            for (Creature creature: escapedCreature) {
                this.removeCreature(creature);
            }
        }
    }

    /**
     * Set the roof state to INTACT
     */
    public void repareRoof() {
        this.roofState = RoofState.INTACT;
    }

    /**
     * Set the roof level to the level under
     */
    public void damageRoof() {
        this.roofState = Utils.getWorseState(this.roofState);
    }

    /**
     * Get the roof state of the aviary
     * @return the roof state of the aviary of type RoofState
     */
    public RoofState getRoofState() {
        return this.roofState;
    }

    /**
     * Set the roof state value of the aviary
     * @param newRoofState of type RoofState
     */
    public void setRoofState(RoofState newRoofState) {
        this.roofState = newRoofState;
    }
}
