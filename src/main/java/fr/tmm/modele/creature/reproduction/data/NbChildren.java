package fr.tmm.modele.creature.reproduction.data;

import java.util.Random;

public enum NbChildren {
    DRAGON(2),
    KRAKEN(5),
    LYCANTHROPE(7),
    MEGALODON(2),
    MERMAID(2),
    NYMPH( 2),
    PHOENIX( 5),
    UNICORN( 2);
    private final int max;

    NbChildren(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
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
        return random.nextInt(1, NbChildren.getMax(creatureType)+1);
    }
}
