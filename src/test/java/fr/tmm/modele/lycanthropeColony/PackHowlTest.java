package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.Log;
import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.species.Lycanthrope;
import fr.tmm.modele.enclosure.Enclosure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackHowlTest {

    Lycanthrope emetteur;
    Lycanthrope samePack;
    Lycanthrope anotherPack1;
    Lycanthrope anotherPack2;

    @BeforeEach
    void init() {
        Zoo.getInstance().clear();
        this.emetteur = new Lycanthrope("Emetteur", "Male",50,50,50);
        this.samePack = new Lycanthrope("SamePack", "Male",50,50,50);
        this.anotherPack1 = new Lycanthrope("AnotherPack1", "Male",50,50,50);
        this.anotherPack2 = new Lycanthrope("AnotherPack2", "Male",50,50,50);
        Enclosure enclo1 = new Enclosure("Enclos 1", 50,5);
        Enclosure enclo2 = new Enclosure("Enclos 2", 50,5);
        enclo1.addCreature(emetteur);
        enclo1.addCreature(samePack);
        enclo2.addCreature(anotherPack1);
        enclo2.addCreature(anotherPack2);
        Zoo.getInstance().getColony().addPack(new Pack(emetteur, samePack));
        Zoo.getInstance().getColony().addPack(new Pack(anotherPack1, anotherPack2));
        Log.getInstance().clear();
    }

    @Test
    void testEmettre() {
        this.emetteur.packHowl();
        assertTrue(Log.getInstance().getLogs().contains("Emetteur pousse un hurlement d'appartenance."));
    }

    @Test
    void testRepondre() {
        this.emetteur.packHowl();
        assertTrue(Log.getInstance().getLogs().contains("SamePack a repondu au hurlement de Emetteur."));
    }

    @Test
    void autreMeutreEmet() {
        boolean test = false;
        for (int i = 0; i < 15; i++) {
            this.emetteur.packHowl();
            if (Log.getInstance().getLogs().contains("AnotherPack1 pousse un hurlement d'appartenance.")) {
                test = true;
            } else if (Log.getInstance().getLogs().contains("AnotherPack2 pousse un hurlement d'appartenance.")) {
                test = true;
            }
        }
        assertTrue(test);
    }

}