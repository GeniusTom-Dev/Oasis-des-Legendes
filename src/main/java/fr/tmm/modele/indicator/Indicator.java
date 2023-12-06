package fr.tmm.modele.indicator;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Indicator {

    protected SimpleIntegerProperty value; // Between 0 and 100
    
    public Indicator() {
        this.value = new SimpleIntegerProperty(100);
    }

    /**
     * Increment by a certain amount the indicator value
     * @param amount
     */
    public void increment(int amount) {
        if (this.value.get() + amount < 100) {
            this.value.set(this.value.get() + amount);
        } else {
            this.value.set(100);
        }
    }

    /**
     * Decrement by a certain amount the indicator value
     * @param amount
     */
    public void decrement(int amount) {
        if (this.value.get() - amount > 0) {
            this.value.set(this.value.get() - amount);
        } else {
            this.value.set(0);
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
        this.value.set(newValue);
    }

    /**
     * Getter of the indicator value attribut
     * @return the value of the indicator
     */
    public int getValue() {
        return this.value.get();
    }

    public SimpleIntegerProperty valueProperty() {
        return value;
    }
}