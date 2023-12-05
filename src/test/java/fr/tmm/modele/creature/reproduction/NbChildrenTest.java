package fr.tmm.modele.creature.reproduction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NbChildrenTest {

    @Test
    void phoenixChildrenNb() {
        int childrenNb = NbChildren.determineChildrenNb("phoenix");
        assertTrue(childrenNb <= NbChildren.PHOENIX.getMax());
        assertTrue(childrenNb >= NbChildren.PHOENIX.getMin());
        assertTrue(childrenNb <= 5);
        assertTrue(childrenNb >= 1);
    }

    @Test
    void lycanChildrenNb() {
        int childrenNb = NbChildren.determineChildrenNb("lycanthrope");
        assertTrue(childrenNb <= NbChildren.LYCANTHROPE.getMax());
        assertTrue(childrenNb >= NbChildren.LYCANTHROPE.getMin());
        assertTrue(childrenNb <= 7);
        assertTrue(childrenNb >= 1);
    }

}