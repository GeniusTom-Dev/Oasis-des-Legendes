package fr.tmm.modele.indicator;

public class SatietyIndicator extends Indicator {

    /**
     * Check if the creature is starving, which means that his satiety is equal to 0.
     * @return true if the creature is starving
     */
    public boolean isStarving() {
        return (this.value == 0);
    }

    public String toString(String name) {
        if (this.isStarving()) {
            return name + " meurt de faim.";
        } else if (this.value < 25) {
            return name + " a très faim.";
        } else if (this.value < 50) {
            return name + " a faim.";
        } else if (this.value < 75) {
            return name + " n'a pas très faim.";
        } else if (this.value < 100) {
            return name + " n'a pas faim.";
        }
        throw new IllegalArgumentException("The indicator value must not be greater than 100");
    }
}
