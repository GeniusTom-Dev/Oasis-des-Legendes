package fr.tmm.modele.utils;

import java.util.Random;

public class Utils {

    /**
     * Calcul if a bad event is going to happend depending of a risk pourcentage
     * @param riskPourcentage
     * @return true if the bad event is going to happend
     */
    static public boolean isBadEventHappening(int riskPourcentage) {
        Random random = new Random();
        int nbRandom = random.nextInt(101); // [0, 100]
        if (nbRandom <= riskPourcentage) {
            return true;
        }
        return false;
    }

    static public int randomIntInInterval(int min, int max) {
        Random random = new Random();
        int nbRandom = random.nextInt((max - min) - 1) + min;
        return nbRandom;
    }
}
