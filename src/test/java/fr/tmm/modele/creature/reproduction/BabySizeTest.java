package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.creature.species.Dragon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BabySizeTest {

    @Test
    void dragonHeight() {
        double height = BabySize.Height.determineBabyHeightSize("dragon");
        assertTrue(height <= BabySize.Height.getMax("dragon"));
        assertTrue(height >= BabySize.Height.getMin("dragon"));
        assertTrue(height <= 2);
        assertTrue(height >= 1.5);
    }

    @Test
    void dragonWeight() {
        double weight = BabySize.Weight.determineBabyWeightSize("dragon");
        assertTrue(weight <= BabySize.Weight.getMax("dragon"));
        assertTrue(weight >= BabySize.Weight.getMin("dragon"));
        assertTrue(weight <= 70);
        assertTrue(weight >= 60);
    }

    @Test
    void phoenixHeight() {
        double height = BabySize.Height.determineBabyHeightSize("phoenix");
        assertTrue(height <= BabySize.Height.getMax("phoenix"));
        assertTrue(height >= BabySize.Height.getMin("phoenix"));
        assertTrue(height <= 0.2);
        assertTrue(height >= 0.1);
    }

    @Test
    void phoenixWeight() {
        double weight = BabySize.Weight.determineBabyWeightSize("phoenix");
        assertTrue(weight <= BabySize.Weight.getMax("phoenix"));
        assertTrue(weight >= BabySize.Weight.getMin("phoenix"));
        assertTrue(weight <= 5);
        assertTrue(weight >= 2);
    }

}