package fr.tmm.modele.creature.reproduction.data;

public enum Gestation {
    DRAGON(100),
    KRAKEN(100),
    LYCANTHROPE(100),
    MEGALODON(100),
    MERMAID(100),
    NYMPH(100),
    PHOENIX(100),
    UNICORN(100);

    private final int gestationTime;

    Gestation(int time) {
        this.gestationTime = time;
    }

    public int getGestationTime() {
        return gestationTime;
    }

    public static int getValue(String type) {
        for (Gestation value : values()) {
            if (value.name().contains(type.toUpperCase())) {
                return value.gestationTime;
            }
        }
        throw new IllegalArgumentException("Creature not found for class: " + type);
    }
}