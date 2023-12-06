package fr.tmm.modele.utils;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.species.Dragon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void test100percent() {
        assertTrue(Utils.isBadEventHappening(100));
        assertTrue(Utils.isBadEventHappening(100));
        assertTrue(Utils.isBadEventHappening(100));
        assertTrue(Utils.isBadEventHappening(100));
        assertTrue(Utils.isBadEventHappening(100));
    }

    @Test
    void test0percent() {
        assertFalse(Utils.isBadEventHappening(0));
        assertFalse(Utils.isBadEventHappening(0));
        assertFalse(Utils.isBadEventHappening(0));
        assertFalse(Utils.isBadEventHappening(0));
        assertFalse(Utils.isBadEventHappening(0));
    }

    @Test
    void getRandomIndexInList() {
        ArrayList<Dragon> list = new ArrayList<>();
        list.add(new Dragon("Dragon", "m", 50,50,50));
        list.add(new Dragon("Dragon", "m", 50,50,50));
        list.add(new Dragon("Dragon", "m", 50,50,50));
        int index = Utils.getRandomIndexInList(list);
        assertTrue(index >= 0 && index <= 2);

    }

}