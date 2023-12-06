package fr.tmm.modele.creature.reproduction.data;

public enum Incubation {
    DRAGON(100),
    KRAKEN(100),
    MEGALODON(100),
    PHENIX(100);
    private final int incubationTime;

    Incubation(int time) {
        this.incubationTime = time;
    }

    public double getIncubationTime() {
        return incubationTime;
    }

    public static int getValue(String type) {
        for (Incubation value : values()) {
            if (value.name().contains(type.toUpperCase())) {
                return value.incubationTime;
            }
        }
        throw new IllegalArgumentException("Creature not found for class: " + type);
    }

}
