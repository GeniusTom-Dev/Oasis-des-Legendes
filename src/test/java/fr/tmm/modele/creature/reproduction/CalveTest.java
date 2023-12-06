package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.species.Unicorn;
import fr.tmm.modele.enclosure.Enclosure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalveTest {

    Unicorn licorne;
    Enclosure enclos;

    @BeforeEach
    void init() {
         this.licorne = new Unicorn("Licorne", "Female", 50,50,50);
         this.enclos = new Enclosure("Un Enclos", 50, 2);
         this.enclos.addCreature(this.licorne);
    }

    @Test
    void normalCalve () {
        ((Female) licorne.getSex()).getReproductionMethod().reproduce(licorne,1);
        assertEquals(enclos.getCreaturesPresent().size(), 2);
        assertEquals(enclos.getCreaturesPresent().get(1).getAge(), 0);
    }

    @Test
    void oneChildButEnclosureFull() {
        ((Female) licorne.getSex()).getReproductionMethod().reproduce(licorne,2);
        assertEquals(enclos.getCreaturesPresent().size(), 2); // baby was add to the enclosure
        assertEquals(enclos.getCreaturesPresent().get(0).getAge(), 50);
        assertEquals(enclos.getCreaturesPresent().get(1).getAge(), 0);
        String txt = "Un bébé Unicorn est mort-né car il est né dans un enclos plein.";
        assertEquals(Log.getInstance().getLastLog(), txt);
    }



}