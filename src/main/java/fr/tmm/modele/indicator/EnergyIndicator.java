package fr.tmm.modele.indicator;

public class EnergyIndicator extends Indicator {

    boolean isAsleep;

    public EnergyIndicator() {
        this.value = 100;
        this.isAsleep = false;
    }

    /**
     * Decrement by a certain amount the indicator value
     * @param amount
     */
    @Override
    public void decrement(int amount) {
        super.decrement(amount);
        if (this.value == 0) {
            fallAsleep();
        }
    }

    /**
     * Increment by a certain amount the indicator value
     * @param amount
     */
    @Override
    public void increment(int amount) {
        super.increment(amount);
        if (this.isAsleep && this.value == 100) {
            wakeUp();
        }
    }

    /**
     * Set the indicator value
     * @param value
     */
    @Override
    public void setValue(int value) {
        super.setValue(value);
        if (this.value == 0) {
            fallAsleep();
        } else if (this.value == 100) {
            wakeUp();
        }
    }

    /**
     * Make the creature fall asleep
     */
    private void fallAsleep() {
        this.isAsleep = true;
    }

    /**
     * Wake up the creature
     */
    private void wakeUp() {
        this.isAsleep = false;
    }

    /**
     * Check if the creature is asleep
     * @return true if the creature is asleep
     */
    public boolean isAsleep() {
        return this.isAsleep;
    }

    public String toString(String name) {
        if (this.isAsleep) {
            return name + " est endormi.";
        } else if (this.value < 25) {
            return name + " est très fatigué.";
        } else if (this.value < 50) {
            return name + " est fatigué.";
        } else if (this.value < 75) {
            return name + " n'est pas très fatigué.";
        } else if (this.value < 100) {
            return name + " n'est pas fatigué.";
        }
        throw new IllegalArgumentException("The indicator value must not be greater than 100");
    }
}
