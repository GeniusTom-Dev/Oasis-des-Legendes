package fr.tmm.modele.enclosure;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.listener.CreatureListener;
import fr.tmm.modele.creature.methodOfMovement.Walker;
import fr.tmm.modele.creature.reproduction.BabySize;
import fr.tmm.modele.creature.reproduction.Egg;
import fr.tmm.modele.creature.species.*;
import fr.tmm.modele.utils.Utils;

import fr.tmm.modele.creature.reproduction.Viviparous;
import fr.tmm.modele.creature.reproduction.Oviparous;


import java.util.ArrayList;

public class Enclosure implements CreatureListener {
    protected String name;
    protected double surfaceArea;
    protected int maxCapacity;
    protected ArrayList<Creature> creaturesPresent = new ArrayList<>();

    protected ArrayList<Egg> eggWaitingToHatch = new ArrayList<>();
    protected CleanlinessStatus cleanliness;

    @Override
    public void onCreatureDeath(Creature deadCreature) {
        this.creaturesPresent.remove(deadCreature);
    }

    @Override
    public void onEggHatching(Egg egg) {
        Log.getInstance().addLog("Un oeuf de " + egg.getType() + " a éclos dans l'enclos " + name + ".");
        this.eggWaitingToHatch.remove(egg);
        this.onCreatureBirth(egg.getMother());
    }

    @Override
    public void onCreatureBirth(Creature mother) {
        double babyWeight = BabySize.Weight.determineFromType(mother.getType());
        double babyHeight = BabySize.Height.determineFromType(mother.getType());
        Creature baby = mother.born(babyWeight, babyHeight);
        this.creaturesPresent.add(baby);
    }

    @Override
    public void onEggLaying(Egg egg) {
        this.eggWaitingToHatch.add(egg);
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
        this.cleanliness = CleanlinessStatus.SPOTLESS;
    }

    public void makeCreatureSickDependingOfCleanliness() {
        if (this.cleanliness.riskOfGettingSick != 0) {
            for (Creature creature : this.getCreaturesPresent()) {
                if (Utils.isBadEventHappening(this.cleanliness.riskOfGettingSick)) {
                    creature.getHealthindicator().setSick(true);
                    Log.getInstance().addLog(creature.getName() + " est tombé malade.");
                }
            }
        }
    }

    // Méthode pour ajouter une créature à l'enclos
    public boolean addCreature(Creature creature) {
        if (creature instanceof Walker) {
            return addCreatureThatMatchesEnclosureType(creature);
        }
        Log.getInstance().addLog("Impossible d'ajouter " + creature.getName() + " à l'enclos " + name +
                " car ce n'est pas une créature terrestre.");
        return false;
    }

    protected boolean addCreatureThatMatchesEnclosureType(Creature creature) {
        if (creaturesPresent.size() < maxCapacity) {
            // Vérifie si la créature est du même type que celles déjà présentes dans l'enclos
            if (creaturesPresent.isEmpty() || creature.getType().equals(creaturesPresent.get(0).getType())) {
                creaturesPresent.add(creature);
                creature.setListener(this);
            } else {
                Log.getInstance().addLog("Impossible d'ajouter " + creature.getName() + " à l'enclos " + name +
                        " car il contient déjà des créatures de type différent.");
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    // Méthode pour enlever une créature de l'enclos
    public void removeCreature(Creature creature) {
        if (!creaturesPresent.remove(creature)) {
            throw new IllegalArgumentException(creature.getName() + " n'est pas présent dans l'enclos " + name + ".");
        }
    }

    // Méthode pour nourrir les créatures de l'enclos
    public void feedCreatures() {
        for (Creature creature : this.getCreaturesPresent()) {
            creature.eat();
        }
        Log.getInstance().addLog("Les créatures de l'enclos " + name + " ont été nourries.");
    }

    // Méthode pour entretenir l'enclos
    public void clean() {
        setCleanlinessDegree(CleanlinessStatus.SPOTLESS);
        Log.getInstance().addLog("L'enclos " + name + " a été entretenu. Degré de propreté : " + cleanliness);
    }

    public void getDirtier() {
        this.cleanliness = getWorseState();
        Log.getInstance().addLog("La propreté de l'enclos " + name + " s'est dégradé. Degrés de propreté actuel : " + cleanliness);
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
    
    /////////////////////////////////////////////////:

    public void startReproductionThread() {
        Thread reproductionThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000); // ajustez la fréquence selon vos besoins

                    // Vérifier si l'enclos a atteint sa capacité maximale
                    if (creaturesPresent.size() < maxCapacity) {
                        reproduce();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        reproductionThread.start();
    }

   private void reproduce() {
        // Sélectionner un mâle et une femelle aléatoirement
        Creature male = getActiveRandomMale();
        Creature female = getActiveRandomFemale();

        if (male != null && female != null) {
            // Faire se reproduire le mâle et la femelle
            reproduceCreatures(male, female);
        }
    }

    private Creature getActiveRandomMale() {
        ArrayList<Creature> males = getCreaturesBySex("male");
        return males.get(Utils.getRandomIndexInList(males));
    }

    private Creature getActiveRandomFemale() {
        ArrayList<Creature> females = getCreaturesBySex("female");
        return females.get(Utils.getRandomIndexInList(females));
    }

    private ArrayList<Creature> getCreaturesBySex(String sex) {
        ArrayList<Creature> creaturesBySex = new ArrayList<>();
        for (Creature creature : creaturesPresent) {
            if (creature.getSex().equalsIgnoreCase(sex) && !creature.isAsleep()) {
                creaturesBySex.add(creature);
            }
        }
        return creaturesBySex;
    }

    private Creature reproduceCreatures(Creature male, Creature female) {
        if (male instanceof Oviparous && female instanceof Oviparous) {
            ((Oviparous) female).startBecomePregnantThread();
        }
        else if (male instanceof Viviparous && female instanceof Viviparous) {
            ((Viviparous) female).startBecomePregnantThread();
        }
        return null;
    }

    ///////////////////////////////////////////////////////////////////////////
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

    public ArrayList<Egg> getEggWaitingToHatch() {
        return eggWaitingToHatch;
    }

}
