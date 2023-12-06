package fr.tmm.modele.indicator;

public class SatietyIndicator extends Indicator {

    /**
     * Check if the creature is starving, which means that his satiety is equal to 0.
     * @return true if the creature is starving
     */
    public boolean isStarving() {
        return (this.value.get() == 0);
    }
}
