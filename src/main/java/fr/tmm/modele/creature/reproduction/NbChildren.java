package fr.tmm.modele.creature.reproduction;

import java.util.Random;

public enum NbChildren {
    DRAGON(1, 2),
    KRAKEN(1, 5),
    LYCANTHROPE(1, 7),
    MEGALODON(1, 2),
    MERMAID(1, 2),
    NYMPH(1, 2),
    PHOENIX(1, 5),
    UNICORN(1, 2);

    private final int min;
    private final int max;

    NbChildren(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    private static int getMin(String type) {
        for (NbChildren nbChildren : values()) {
            if (nbChildren.name().contains(type.toUpperCase())) {
                return nbChildren.min;
            }
        }
        throw new IllegalArgumentException("Creature not found for class: " + type);
    }

    private static int getMax(String type) {
        for (NbChildren nbChildren : NbChildren.values()) {
            if (nbChildren.name().contains(type.toUpperCase())) {
                return nbChildren.max;
            }
        }
        throw new IllegalArgumentException("Creature not found for class: " + type);
    }

    public static int determineChildrenNb(String creatureType) {
        Random random = new Random();
        return random.nextInt((NbChildren.getMax(creatureType) - NbChildren.getMin(creatureType)) - 1) + NbChildren.getMin(creatureType);
    }
}
