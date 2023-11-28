package fr.tmm.modele.creature.methodOfMovement;

import fr.tmm.modele.creature.species.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class checkImplementation {

    @Test
    void checkDragonImplementation() {
        Dragon dragon = new Dragon("", "", 5, 5, 5);
        assertTrue(dragon instanceof Walker);
        assertTrue(dragon instanceof Flyer);
        assertTrue(dragon instanceof Swimmer);
    }

    @Test
    void checkKrakenImplementation() {
        Kraken kraken = new Kraken("", "", 5, 5, 5);
        assertFalse(kraken instanceof Walker);
        assertFalse(kraken instanceof Flyer);
        assertTrue(kraken instanceof Swimmer);
    }

    @Test
    void checkHumanImplementation() {
        Human human = new Human("", "", 5, 5, 5);
        assertTrue(human instanceof Walker);
        assertFalse(human instanceof Flyer);
        assertFalse(human instanceof Swimmer);
    }

    @Test
    void checkLycantropeImplementation() {
        Lycanthrope lycan = new Lycanthrope("", "", 5, 5, 5);
        assertTrue(lycan instanceof Walker);
        assertFalse(lycan instanceof Flyer);
        assertFalse(lycan instanceof Swimmer);
    }

    @Test
    void checkMegalodonImplementation() {
        Megalodon megalodon = new Megalodon("", "", 5, 5, 5);
        assertFalse(megalodon instanceof Walker);
        assertFalse(megalodon instanceof Flyer);
        assertTrue(megalodon instanceof Swimmer);
    }

    @Test
    void checkMermaidImplementation() {
        Mermaid marmaid = new Mermaid("", "", 5, 5, 5);
        assertFalse(marmaid instanceof Walker);
        assertFalse(marmaid instanceof Flyer);
        assertTrue(marmaid instanceof Swimmer);
    }

    @Test
    void checkNymphImplementation() {
        Nymph nymph = new Nymph("", "", 5, 5, 5);
        assertTrue(nymph instanceof Walker);
        assertFalse(nymph instanceof Flyer);
        assertFalse(nymph instanceof Swimmer);
    }

    @Test
    void checkPhenixImplementation() {
        Phenix phenix = new Phenix("", "", 5, 5, 5);
        assertFalse(phenix instanceof Walker);
        assertTrue(phenix instanceof Flyer);
        assertFalse(phenix instanceof Swimmer);
    }

    @Test
    void checkUnicornImplementation() {
        Unicorn unicorn = new Unicorn("", "", 5, 5, 5);
        assertTrue(unicorn instanceof Walker);
        assertFalse(unicorn instanceof Flyer);
        assertFalse(unicorn instanceof Swimmer);
    }
}
