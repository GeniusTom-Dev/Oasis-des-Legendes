package fr.tmm.modele.utils;

import java.util.Random;

public class Utils {

    static public boolean isBadEventHappening(int riskPourcentage) {
        Random random = new Random();
        int nbRandom = random.nextInt(101); // [0, 100]
        if (nbRandom <= riskPourcentage) {
            return true;
        }
        return false;
    }
}
