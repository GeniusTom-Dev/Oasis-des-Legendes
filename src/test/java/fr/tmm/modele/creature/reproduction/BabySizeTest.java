package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.creature.reproduction.data.BabySize;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BabySizeTest {

    @Test
    void dragonHeight() {
        double height = BabySize.Height.determineFromType("dragon");
        assertTrue(height <= BabySize.Height.DRAGON.getMax());
        assertTrue(height >= BabySize.Height.DRAGON.getMin());
        assertTrue(height <= 2);
        assertTrue(height >= 1.5);
    }

    @Test
    void dragonWeight() {
        double weight = BabySize.Weight.determineFromType("dragon");
        assertTrue(weight <= BabySize.Weight.DRAGON.getMax());
        assertTrue(weight >= BabySize.Weight.DRAGON.getMin());
        assertTrue(weight <= 70);
        assertTrue(weight >= 60);
    }

    @Test
    void phoenixHeight() {
        double height = BabySize.Height.determineFromType("phoenix");
        assertTrue(height <= BabySize.Height.PHOENIX.getMax());
        assertTrue(height >= BabySize.Height.PHOENIX.getMin());
        assertTrue(height <= 0.2);
        assertTrue(height >= 0.1);
    }

    @Test
    void phoenixWeight() {
        double weight = BabySize.Weight.determineFromType("phoenix");
        assertTrue(weight <= BabySize.Weight.PHOENIX.getMax());
        assertTrue(weight >= BabySize.Weight.PHOENIX.getMin());
        assertTrue(weight <= 5);
        assertTrue(weight >= 2);
    }

}