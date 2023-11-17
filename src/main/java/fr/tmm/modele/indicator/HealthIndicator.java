package fr.tmm.modele.indicator;

public class HealthIndicator extends Indicator {

    private boolean isSick;
    private boolean isAlive;

    public HealthIndicator() {
        this.isSick = false;
        this.isAlive = true;
    }

    @Override
    public void decrement(int amount) {
        super.decrement(amount);
        if (this.value == 0) {
            this.isAlive = false;
        }
    }

    @Override
    public void setValue(int value) {
        super.setValue(value);
        if (this.value == 0) {
            this.isAlive = false;
        } else {
            this.isAlive = true;
        }
    }

    public String toString(String name) {
        if (this.value == 0) {
            return name + " est mort.";
        } else if (this.value < 25) {
            return name + " est gravement blessé.";
        } else if (this.value < 50) {
            return name + " est blessé.";
        } else if (this.value < 75) {
            return name + " est mal en point.";
        } else if (this.value < 100) {
            return name + " est en bonne santé.";
        }
        throw new IllegalArgumentException("The indicator value must not be greater than 100");
    }

    public boolean isSick() {
        return this.isSick;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

}
