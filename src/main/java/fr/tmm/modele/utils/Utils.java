package fr.tmm.modele.utils;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.enclosure.Enclosure;

import java.util.ArrayList;
import java.util.Random;

public class Utils {

    /**
     * Calcul if a bad event is going to happend depending of a risk pourcentage
     * @param riskPourcentage : the pourcentage of the bad event
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
     * @param list : a list
     * @return a random index of the list
     */
    public static int getRandomIndexInList(ArrayList list) {
        if (list.isEmpty()) return -1;
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return randomIndex;
    }

    /**
     * Returns the value lower than the current value of an enum
     * @param enumValue : the current enum value
     * @return : the lower value of the enum
     * @param <E> : an enum
     */
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
