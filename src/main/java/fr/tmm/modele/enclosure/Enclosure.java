package fr.tmm.modele.enclosure;

import fr.tmm.modele.Log;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.listener.CreatureListener;
import fr.tmm.modele.creature.methodOfMovement.Walker;
import fr.tmm.modele.creature.reproduction.Female;
import fr.tmm.modele.creature.reproduction.data.BabySize;
import fr.tmm.modele.creature.reproduction.Egg;
import fr.tmm.modele.utils.Utils;


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
        if (this.creaturesPresent.size() == this.maxCapacity) {
            baby.die();
        } else {
            Log.getInstance().addLog(baby.getName() + " vient de naître dans l'enclos " + this.name + ".");
            this.creaturesPresent.add(baby);
        }
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

        private final int riskOfGettingSick;

        CleanlinessStatus(int i) {
            this.riskOfGettingSick = i;
        }
    }

    public Enclosure(String name, double area, int maxCapacity) {
        this.name = name;
        this.surfaceArea = area;
        this.maxCapacity = maxCapacity;
        this.cleanliness = CleanlinessStatus.SPOTLESS;
        enclosureThread();
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
        this.cleanliness = Utils.getWorseState(this.cleanliness);
        Log.getInstance().addLog("La propreté de l'enclos " + name + " s'est dégradé. Degrés de propreté actuel : " + cleanliness);
    }

    public void enclosureThread() {
        Thread reproductionThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    if (creaturesPresent.size() < maxCapacity) {
                        System.out.println("reproduction");
                        reproduce();
                    }
                    makeCreatureSickDependingOfCleanliness();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        reproductionThread.start();
    }

   private void reproduce() throws InterruptedException {
        Creature male = getActiveRandomMale();
        Creature female = getActiveRandomFemale();

        if (male != null && female != null) {
            ((Female) female.getSex()).startBecomePregnantThread();
            Log.getInstance().addLog(male.getName() + " et " + female.getName() + " se sont accouplés.");
        }
    }

    private Creature getActiveRandomMale() {
        ArrayList<Creature> males = getCreaturesBySex("Male");
        int index = Utils.getRandomIndexInList(males);
        if (index == -1) {
            return null;
        }
        return males.get(index);
    }

    private Creature getActiveRandomFemale() {
        ArrayList<Creature> males = getCreaturesBySex("Female");
        int index = Utils.getRandomIndexInList(males);
        if (index == -1) {
            return null;
        }
        return males.get(index);
    }

    public ArrayList<Creature> getCreaturesBySex(String sex) {
        ArrayList<Creature> creaturesBySex = new ArrayList<>();
        for (Creature creature : creaturesPresent) {
            if (creature.getSex().toString() == sex && !creature.isAsleep()) {
                if (creature.getSex().toString() == "Female") {
                    if (!((Female) creature.getSex()).isPregnant()) {
                        creaturesBySex.add(creature);
                    }
                } else {
                    creaturesBySex.add(creature);
                }
            }
        }
        return creaturesBySex;
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
