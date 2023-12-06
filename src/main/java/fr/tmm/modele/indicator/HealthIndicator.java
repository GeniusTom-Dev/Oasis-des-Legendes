package fr.tmm.modele.indicator;

public class HealthIndicator extends Indicator {

    private boolean isSick;
    private boolean isAlive;

    public HealthIndicator() {
        this.isSick = false;
        this.isAlive = true;
    }

    /**
     * Completly heal the creature health which set the health value to 100
     */
    public void heal() {
        this.value = 100;
        this.isSick = false;
    }

    /**
     * Decrement by a certain amount the indicator value
     * @param amount
     */
    @Override
    public void decrement(int amount) {
        super.decrement(amount);
        if (this.value == 0) {
            this.isAlive = false;
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
            this.isAlive = false;
        } else {
            this.isAlive = true;
        }
    }

    /**
     * Check if the creature is sick.
     * @return true if the creature is sick
     */
    public boolean isSick() {
        return this.isSick;
    }

    /**
     * Check if the creature is alive
     * @return true is the creature is alive
     */
    public boolean isAlive() {
        return this.isAlive;
    }

    /**
     * Setter of the attribut isSick
     * @param sick : the new boolean value of isSick
     */
    public void setSick(boolean sick) {
        isSick = sick;
    }

}
