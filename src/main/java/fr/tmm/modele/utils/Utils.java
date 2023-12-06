package fr.tmm.modele.utils;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.enclosure.Enclosure;

import java.util.ArrayList;
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

    /**
     * Calcul a random number between 0 and list size
     * @param list
     * @return a random index of the list
     */
    public static int getRandomIndexInList(ArrayList list) {
        if (list.isEmpty()) return -1;
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return randomIndex;
    }

    static public int randomIntInInterval(int min, int max) {
        Random random = new Random();
        int nbRandom = random.nextInt((max - min) - 1) + min;
        return nbRandom;
    }


    public static  <E extends Enum<E>> E getWorseState(E enumValue) {
        E[] enumValues = enumValue.getDeclaringClass().getEnumConstants();
        for (int i = 0; i < enumValues.length; i++) {
            if (enumValues[i] == enumValue && i > 0) {
                return enumValues[i - 1];
            }
        }
        return enumValue;
    }
}
