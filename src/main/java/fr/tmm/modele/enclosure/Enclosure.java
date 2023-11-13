package fr.tmm.modele.enclosure;

import fr.tmm.modele.creature.Creature;

import java.util.ArrayList;

public class Enclosure {
    private String name;
    private double area;
    private int maxCapacity;
    private int currentCreatureCount;
    private ArrayList<Creature> creaturesPresent;
    private String cleanlinessDegree;

    public Enclosure(String name, double area, int maxCapacity) {
        this.name = name;
        this.area = area;
        this.maxCapacity = maxCapacity;
        this.currentCreatureCount = 0;
        this.creaturesPresent = new ArrayList<>();
        this.cleanlinessDegree = "correct";
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCreatureCount() {
        return currentCreatureCount;
    }

    public void setCurrentCreatureCount(int currentCreatureCount) {
        this.currentCreatureCount = currentCreatureCount;
    }

    public ArrayList<Creature> getCreaturesPresent() {
        return creaturesPresent;
    }

    public void setCreaturesPresent(ArrayList<Creature> creaturesPresent) {
        this.creaturesPresent = creaturesPresent;
    }

    public String getCleanlinessDegree() {
        return cleanlinessDegree;
    }

    public void setCleanlinessDegree(String cleanlinessDegree) {
        this.cleanlinessDegree = cleanlinessDegree;
    }

    // Méthode pour afficher les caractéristiques de l'enclos
    public void afficherCaracteristiques() {
        System.out.println("Caractéristiques de l'enclos " + name + ":");
        System.out.println("Superficie : " + area);
        System.out.println("Capacité maximale : " + maxCapacity);
        System.out.println("Nombre de créatures présentes : " + currentCreatureCount);
        System.out.println("Degré de propreté : " + cleanlinessDegree);
        System.out.println("Créatures présentes :");
        for (Creature creature : creaturesPresent) {
            System.out.println("- " + creature.getName() + " (Type : " + creature.getType() + ")");
        }
    }

    // Méthode pour ajouter une créature à l'enclos
    public void ajouterCreature(Creature creature) {
        if (creature != null && creaturesPresent.size() < maxCapacity) {
            // Vérifie si la créature est du même type que celles déjà présentes dans l'enclos
            if (creaturesPresent.isEmpty() || creature.getType().equals(creaturesPresent.get(0).getType())) {
                creaturesPresent.add(creature);
                currentCreatureCount++;
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
    public void enleverCreature(Creature creature) {
        if (creaturesPresent.remove(creature)) {
            currentCreatureCount--;
            System.out.println(creature.getName() + " a été retiré de l'enclos " + name + ".");
        } else {
            System.out.println(creature.getName() + " n'est pas présent dans l'enclos " + name + ".");
        }
    }

    // Méthode pour nourrir les créatures de l'enclos
    public void nourrirCreatures() {
        System.out.println("Les créatures de l'enclos " + name + " sont nourries.");
        // Logique de nourrissage spécifique à l'enclos
    }

    // Méthode pour entretenir l'enclos
    public void entretenirEnclos() {
        setCleanlinessDegree("bon");
        System.out.println("L'enclos " + name + " a été entretenu. Degré de propreté : " + cleanlinessDegree);
    }

}
