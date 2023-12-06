package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.creature.reproduction.data.NbChildren;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NbChildrenTest {

    @Test
    void phoenixChildrenNb() {
        int childrenNb = NbChildren.determineChildrenNb("phoenix");
        assertTrue(childrenNb <= NbChildren.PHOENIX.getMax());
        assertTrue(childrenNb <= 5);
        assertTrue(childrenNb >= 1);
    }

    @Test
    void lycanChildrenNb() {
        int childrenNb = NbChildren.determineChildrenNb("lycanthrope");
        assertTrue(childrenNb <= NbChildren.LYCANTHROPE.getMax());
        assertTrue(childrenNb <= 7);
        assertTrue(childrenNb >= 1);
    }

    @Test
    void mermaidChildrenNb() {
        int childrenNb = NbChildren.determineChildrenNb("mermaid");
        assertTrue(childrenNb <= NbChildren.MERMAID.getMax());
        assertTrue(childrenNb <= 2);
        assertTrue(childrenNb >= 1);
    }

}