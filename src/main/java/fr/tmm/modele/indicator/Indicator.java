package fr.tmm.modele.indicator;

public abstract class Indicator {

    protected int value; // Between 0 and 100
    
    public Indicator() {
        this.value = 100;
    }

    /**
     * Increment by a certain amount the indicator value
     * @param amount
     */
    public void increment(int amount) {
        if (value + amount < 100) {
            this.value += amount;
        } else {
            this.value = 100;
        }
    }

    /**
     * Decrement by a certain amount the indicator value
     * @param amount
     */
    public void decrement(int amount) {
        if (value - amount > 0) {
            this.value -= amount;
        } else {
            this.value = 0;
        }
    }

    /**
     * Set the indicator value
     * @param newValue
     */
    public void setValue(int newValue) {
        if (newValue < 0 || newValue > 100) {
            throw new IllegalArgumentException("The indicator value must not be lower than 0 and greater than 100");
        }
        this.value = newValue;
    }

    /**
     * Getter of the indicator value attribut
     * @return the value of the indicator
     */
    public int getValue() {
        return this.value;
    }
}