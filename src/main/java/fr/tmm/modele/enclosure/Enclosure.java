package fr.tmm.modele.enclosure;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.listener.CreatureDeathListener;

import java.util.ArrayList;

public class Enclosure implements CreatureDeathListener {
    protected String name;
    protected double surfaceArea;
    protected int maxCapacity;
    protected ArrayList<Creature> creaturesPresent;
    protected CleanlinessStatus cleanliness;

    @Override
    public void onCreatureDeath(Creature deadCreature) {
        this.creaturesPresent.remove(deadCreature);
    }

    public enum CleanlinessStatus {
        UNSANITARY(75), // cratures have 75% chance of getting sick
        DIRTY(25),
        CLEAN(5),
        SPOTLESS(0);

        public int getRiskOfGettingSick() {
            return riskOfGettingSick;
        }

        private int riskOfGettingSick;

        CleanlinessStatus(int i) {
            this.riskOfGettingSick = i;
        }
    }

    public Enclosure(String name, double area, int maxCapacity) {
        this.name = name;
        this.surfaceArea = area;
        this.maxCapacity = maxCapacity;
        this.creaturesPresent = new ArrayList<>();
        this.cleanliness = CleanlinessStatus.SPOTLESS;
    }

    public void makeCreatureSickDependingOfCleanliness() {
        if (this.cleanliness.riskOfGettingSick != 0) {
            // TODO
        }
    }

    // Méthode pour ajouter une créature à l'enclos
    public void addCreature(Creature creature) {
        if (creature != null && creaturesPresent.size() < maxCapacity) {
            // Vérifie si la créature est du même type que celles déjà présentes dans l'enclos
            if (creaturesPresent.isEmpty() || creature.getType().equals(creaturesPresent.get(0).getType())) {
                creaturesPresent.add(creature);
                creature.setListener(this);
                System.out.println(creature.getName() + " a été ajouté à l'enclos " + name + ".");
            } else {
                System.out.println("Impossible d'ajouter " + creature.getName() + " à l'enclos " + name +
                        " car il contient déjà des créatures de type différent.");
            }
        } else {
            System.out.println("L'enclos est plein ou la créature est invalide.");
        }
    }

    // Méthode pour enlever une créature de l'enclos
    public void removeCreature(Creature creature) {
        if (creaturesPresent.remove(creature)) {
            System.out.println(creature.getName() + " a été retiré de l'enclos " + name + ".");
        } else {
            System.out.println(creature.getName() + " n'est pas présent dans l'enclos " + name + ".");
        }
    }

    // Méthode pour nourrir les créatures de l'enclos
    public void feedCreatures() {
        for (Creature creature : this.getCreaturesPresent()) {
            creature.eat();
        }
        System.out.println("Les créatures de l'enclos " + name + " sont nourries.");
    }

    // Méthode pour entretenir l'enclos
    public void clean() {
        setCleanlinessDegree(CleanlinessStatus.SPOTLESS);
        System.out.println("L'enclos " + name + " a été entretenu. Degré de propreté : " + cleanliness);
    }

    public void getDirtier() {
        this.cleanliness = getWorseState();
    }

    private CleanlinessStatus getWorseState() {
        CleanlinessStatus[] statuses = CleanlinessStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            if (statuses[i] == cleanliness && i > 0) {
                return statuses[i - 1];
            }
        }
        return cleanliness;
    }

    // Méthode pour afficher les caractéristiques de l'enclos
    public void showCaracteristics() {
        System.out.println("Caractéristiques de l'enclos " + name + ":");
        System.out.println("Superficie : " + surfaceArea);
        System.out.println("Capacité maximale : " + maxCapacity);
        System.out.println("Nombre de créatures présentes : " + creaturesPresent.size());
        System.out.println("Degré de propreté : " + cleanliness);
        System.out.println("Créatures présentes :");
        for (Creature creature : creaturesPresent) {
            System.out.println("- " + creature.getName() + " (Type : " + creature.getType() + ")");
        }
    }

    // --- GETTER ---

    public String getName() {
        return name;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public ArrayList<Creature> getCreaturesPresent() {
        return creaturesPresent;
    }

    public CleanlinessStatus getCleanlinessDegree() {
        return cleanliness;
    }


    // --- SETTER ---

    public void setCreaturesPresent(ArrayList<Creature> creaturesPresent) {
        this.creaturesPresent = creaturesPresent;
    }

    public void setCleanlinessDegree(CleanlinessStatus cleanlinessDegree) {
        this.cleanliness = cleanlinessDegree;
    }

}
