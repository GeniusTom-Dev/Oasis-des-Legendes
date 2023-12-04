package fr.tmm.modele.enclosure;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.listener.CreatureListener;
import fr.tmm.modele.creature.methodOfMovement.Flyer;
import fr.tmm.modele.creature.methodOfMovement.Walker;
import fr.tmm.modele.creature.reproduction.BabySize;
import fr.tmm.modele.creature.reproduction.Egg;
import fr.tmm.modele.creature.species.Dragon;
import fr.tmm.modele.utils.Utils;

import fr.tmm.modele.creature.reproduction.BabySize;
import fr.tmm.modele.creature.reproduction.Gestation;
import fr.tmm.modele.creature.reproduction.Incubation;
import fr.tmm.modele.creature.reproduction.Viviparous;
import fr.tmm.modele.creature.reproduction.Oviparous;


import java.util.ArrayList;
import java.util.Random;

public class Enclosure implements CreatureListener {
    protected String name;
    protected double surfaceArea;
    protected int maxCapacity;
    protected ArrayList<Creature> creaturesPresent = new ArrayList<>();

    protected ArrayList<Egg> eggWaitingToHatch = new ArrayList<>();
    protected CleanlinessStatus cleanliness;

    /**
     * Enumeration representing the cleanliness state of the enclosure
     */
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

    /**
     * Remove a dead creature from the enclosure
     * @param deadCreature
     */
    @Override
    public void onCreatureDeath(Creature deadCreature) {
        this.creaturesPresent.remove(deadCreature);
    }

    /**
     * Add the newborn to the creature list after the hatching of an egg
     * @param egg
     */
    @Override
    public void onEggHatching(Egg egg) {
        Log.getInstance().addLog("Un oeuf de " + egg.getType() + " a éclos dans l'enclos " + name + ".");
        this.eggWaitingToHatch.remove(egg);
        this.onCreatureBirth(egg.getType());
    }

    /**
     * Add a newbord to the creature list after that a female has calved
     * @param type
     */
    @Override
    public void onCreatureBirth(String type) {
        // TMP - a changer
        this.creaturesPresent.add(new Dragon("egghatched", "m",50,50,0));
        // TODO -> laisser vide mais override la fct pour chaque espece
    }


    /*
    @Override
    public void onCreatureBirth(String type) {
        Creature baby;
        // Utiliser les informations de BabySize pour déterminer la taille du nouveau-né
        double babyWeight = determineBabyWeightSize(female.getType());
        double babyHeight = determineBabyHeightSize(female.getType());

        // Créer un bébé avec des valeurs aléatoires de largeur et hauteur
        this.creaturesPresent.add(new Creature("egghatched", "m",babyWeight,babyHeight,0));
    }

    private double determineBabyWeightSize(String creatureType) {
        return BabySize.Weight.getMin(creatureType) + Math.random() * (BabySize.Weight.getMax(creatureType) - BabySize.Weight.getMin(creatureType));
    }

    private double determineBabyHeightSize(String creatureType) {
        return BabySize.Height.getMin(creatureType) + Math.random() * (BabySize.Height.getMax(creatureType) - BabySize.Height.getMin(creatureType));
    }

     */

    /**
     * Add an egg to the eggs enclosure after that a female has layed eggs
     * @param egg
     */
    @Override
    public void onEggLaying(Egg egg) {
        this.eggWaitingToHatch.add(egg);
    }

    public Enclosure(String name, double area, int maxCapacity) {
        this.name = name;
        this.surfaceArea = area;
        this.maxCapacity = maxCapacity;
        this.cleanliness = CleanlinessStatus.SPOTLESS;
    }

    /**
     * Make some creature sick depending of the cleanliness state of the enclosure
     */
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

    /**
     * Check that a creature is a walker and can be added to an enclosure
     * @param creature : the creature who is going to be checked
     * @return true if the creature is a walker
     */
    public boolean addCreature(Creature creature) {
        if (creature instanceof Walker) {
            return addCreatureThatMatchesEnclosureType(creature);
        }
        Log.getInstance().addLog("Impossible d'ajouter " + creature.getName() + " à l'enclos " + name +
                " car ce n'est pas une créature terrestre.");
        return false;
    }

    /**
     * Check that a creature
     * @param creature
     * @return
     */
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

    /**
     * Remove a creature from the enclosure
     * @param creature : the creature to remove
     */
    public void removeCreature(Creature creature) {
        if (!creaturesPresent.remove(creature)) {
            throw new IllegalArgumentException(creature.getName() + " n'est pas présent dans l'enclos " + name + ".");
        }
    }

    /**
     * Feed the creature of the enclosure
     */
    public void feedCreatures() {
        for (Creature creature : this.getCreaturesPresent()) {
            creature.eat();
        }
        Log.getInstance().addLog("Les créatures de l'enclos " + name + " ont été nourries.");
    }

    /**
     * Set the cleanliness to the enclosure to the maximal state
     */
    public void clean() {
        setCleanlinessDegree(CleanlinessStatus.SPOTLESS);
        Log.getInstance().addLog("L'enclos " + name + " a été entretenu. Degré de propreté : " + cleanliness);
    }

    /**
     * Set the cleanliness of the enclosure to the level just under the current level
     */
    public void getDirtier() {
        this.cleanliness = getWorseState();
        Log.getInstance().addLog("La propreté de l'enclos " + name + " s'est dégradé. Degrés de propreté actuel : " + cleanliness);
    }

    /**
     * Get the cleanliness state just under the current one
     * @return the cleanliness state just under the current one or the current one if the current one is the worst state possible
     */
    private CleanlinessStatus getWorseState() {
        CleanlinessStatus[] statuses = CleanlinessStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            if (statuses[i] == cleanliness && i > 0) {
                return statuses[i - 1];
            }
        }
        return cleanliness;
    }

/*
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
*/


    public void startReproductionThread() {
        Thread reproductionThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000); // ajustez la fréquence selon vos besoins

                    // Vérifier si l'enclos a atteint sa capacité maximale
                    if (creaturesPresent.size() < maxCapacity) {
                        // reproduce();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        reproductionThread.start();
    }

   /* private void reproduce() {
        // Sélectionner un mâle et une femelle aléatoirement
        Creature male = getRandomMale();
        Creature female = getRandomFemale();

        if (male != null && female != null) {
            // Faire se reproduire le mâle et la femelle
            // Appeler la méthode spécifique pour la reproduction en fonction du type de créature
            Creature baby = reproduceCreatures(male, female);
            addCreature(baby);
        }
    }

    private Creature getRandomMale() {
        ArrayList<Creature> males = getCreaturesBySex("male");
        return getRandomCreature(males);
    }

    private Creature getRandomFemale() {
        ArrayList<Creature> females = getCreaturesBySex("female");
        return getRandomCreature(females);
    }

    private Creature getRandomCreature(ArrayList<Creature> creatures) {
        if (creatures.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(creatures.size());
        return creatures.get(randomIndex);
    }

    private ArrayList<Creature> getCreaturesBySex(String sex) {
        ArrayList<Creature> creaturesBySex = new ArrayList<>();
        for (Creature creature : creaturesPresent) {
            if (creature.getSex().equalsIgnoreCase(sex)) {
                creaturesBySex.add(creature);
            }
        }
        return creaturesBySex;
    }

    private Creature reproduceCreatures(Creature male, Creature female) {
        // Logique spécifique pour la reproduction d'ovipares
        if (male instanceof Oviparous && female instanceof Oviparous) {
            ((Oviparous) female).layEgg(1);

            // Utiliser les informations de BabySize pour déterminer la taille du nouveau-né
            double babySize = determineBabySize(female.getType());
            int incubationPeriod = determineIncubationPeriod(female.getType());

        }

        // Logique spécifique pour la reproduction de vivipares
        else if (male instanceof Viviparous && female instanceof Viviparous) {
            ((Viviparous) female).calve(1);

            // Utiliser les informations de BabySize et Gestation pour déterminer la taille du nouveau-né et la période de gestation
            double babySize = determineBabySize(female.getType());
            int gestationPeriod = determineGestationPeriod(female.getType());

        }
    }

    private double determineBabySize(String creatureType) {
        return BabySize.getMin(creatureType) + Math.random() * (BabySize.getMax(creatureType) - BabySize.getMin(creatureType));
    }

    private int determineGestationPeriod(String creatureType) {
        return Gestation.getClassDuration(creatureType);
    }

    private int determineIncubationPeriod(String creatureType) {
        return Incubation.getClassDuration(creatureType);
    }*/



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
