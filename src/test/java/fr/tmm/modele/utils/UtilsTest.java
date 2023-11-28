package fr.tmm.modele.utils;

import jdk.jshell.execution.Util;
import org.junit.jupiter.api.Test;

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

}