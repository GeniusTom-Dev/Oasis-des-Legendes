package fr.tmm.modele.indicator;


// PAS UTILISE POUR L'INSTANT - A VOIR PLUS TARD

public enum StateLevel {
    CRITICAL(100) { // 100% of a bad event happening

        @Override
        StateLevel getWorseState() {
            return this;
        }
    }, BAD(50) { // 50% of a bad event happening

        @Override
        StateLevel getWorseState() {
            return StateLevel.CRITICAL;
        }
    },
    ACCEPTABLE(10) { // 10% of a bad event happening

        @Override
        StateLevel getWorseState() {
            return StateLevel.BAD;
        }
    }, GOOD(3) { // 3% of a bad event happening

        @Override
        StateLevel getWorseState() {
            return StateLevel.ACCEPTABLE;
        }
    }, EXCELLENT(0) { // 0% of a bad event happening

        @Override
        StateLevel getWorseState() {
            return StateLevel.GOOD;
        }
    };
    private final int riskPercentage;

    StateLevel(int riskPercentage) {
        this.riskPercentage = riskPercentage;
    }

    public int getRiskPercentage() {
        return this.riskPercentage;
    }

    abstract StateLevel getWorseState();

    static StateLevel getBestState() {
        return StateLevel.EXCELLENT;
    }

    /*static StateLevel updateStateLevel(int value) {
        if (value == 0) {
            return StateLevel.CRITICAL;
        } else if (value < 25) {
            return StateLevel.BAD;
        } else if (value < 50) {
            return StateLevel.ACCEPTABLE;
        } else if (value < 75) {
            return StateLevel.GOOD;
        } else {
            return StateLevel.EXCELLENT;
        }
    }*/
}