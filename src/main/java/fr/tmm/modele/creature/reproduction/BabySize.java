package fr.tmm.modele.creature.reproduction;

import fr.tmm.modele.creature.Creature;

public class BabySize {
    public enum Height {
        DRAGON(1.5, 2),
        KRAKEN(1, 1.5),
        LYCANTHROPE(0.2, 0.4),
        MEGALODON(1, 1.5),
        MERMAID(0.45, 0.55),
        NYMPH(0.45, 0.55),
        PHOENIX(0.1, 0.2),
        UNICORN(1, 1.2);

        private final double min;
        private final double max;

        Height(double min, double max) {
            this.min = min;
            this.max = max;
        }

        public double getMin() {
            return min;
        }

        public double getMax() {
            return max;
        }

        public static double getMin(String type) {
            for (Height height : values()) {
                if (height.name().contains(type.toUpperCase())) {
                    return height.min;
                }
            }
            throw new IllegalArgumentException("Creature not found for class: " + type);
        }

        public static double getMax(String type) {
            for (Height height : values()) {
                if (height.name().contains(type.toUpperCase())) {
                    return height.max;
                }
            }
            throw new IllegalArgumentException("Creature not found for class: " + type);
        }

        public static double determineBabyHeightSize(String creatureType) {
            return BabySize.Height.getMin(creatureType) + Math.random() * (BabySize.Height.getMax(creatureType) - BabySize.Height.getMin(creatureType));
        }
    }

    public enum Weight {
        DRAGON(60, 70),
        KRAKEN(5, 7.5),
        LYCANTHROPE(5, 10),
        MEGALODON(50, 100),
        MERMAID(7, 15),
        NYMPH(5, 10),
        PHOENIX(2, 5),
        UNICORN(50, 60);

        private final double min;
        private final double max;

        Weight(double min, double max) {
            this.min = min;
            this.max = max;
        }

        public double getMin() {
            return min;
        }

        public double getMax() {
            return max;
        }

        public static double getMin(String type) {
            for (Weight weight : values()) {
                if (weight.name().contains(type.toUpperCase())) {
                    return weight.min;
                }
            }
            throw new IllegalArgumentException("Creature not found for class: " + type);
        }

        public static double getMax(String type) {
            for (Weight weight : values()) {
                if (weight.name().contains(type.toUpperCase())) {
                    return weight.max;
                }
            }
            throw new IllegalArgumentException("Creature not found for class: " + type);
        }

        public static double determineBabyWeightSize(String creatureType) {
            return BabySize.Weight.getMin(creatureType) + Math.random() * (BabySize.Weight.getMax(creatureType) - BabySize.Weight.getMin(creatureType));
        }
    }

}
