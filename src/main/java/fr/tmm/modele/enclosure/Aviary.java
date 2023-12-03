package fr.tmm.modele.enclosure;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.methodOfMovement.Flyer;
import fr.tmm.modele.utils.Utils;

import java.util.ArrayList;

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
    }

    @Override
    public boolean addCreature(Creature creature) {
        if (creature instanceof Flyer) {
            return super.addCreature(creature);
        } else {
            System.out.println("Impossible d'ajouter " + creature.getName() + " à la volière " + name +
                    " car ce n'est pas une créature volante.");
            return false;
        }
    }

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
}
