package fr.tmm.modele.indicator;

public class EnergyIndicator extends Indicator {

    boolean isAsleep;

    public EnergyIndicator() {
        this.value = 100;
        this.isAsleep = false;
    }

    @Override
    public void decrement(int amount) {
        super.decrement(amount);
        if (this.value == 0) {
            this.isAsleep = true;
        }
    }

    @Override
    public void setValue(int value) {
        super.setValue(value);
        if (this.value == 0) {
            this.isAsleep = true;
        }
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
