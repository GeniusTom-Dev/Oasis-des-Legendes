package fr.tmm.modele.indicator;

public class EnergyIndicator extends Indicator {

    boolean isAsleep;

    public EnergyIndicator() {
        this.value.set(100);
        this.isAsleep = false;
    }

    /**
     * Decrement by a certain amount the indicator value
     * @param amount
     */
    @Override
    public void decrement(int amount) {
        super.decrement(amount);
        if (this.value.get() == 0) {
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
        if (this.isAsleep && this.value.get() == 100) {
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
        if (this.value.get() == 0) {
            fallAsleep();
        } else if (this.value.get() == 100) {
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

    public void setAsleep(boolean asleep) {
        isAsleep = asleep;
    }

}
