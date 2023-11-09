package fr.tmm.modele.creature;

public abstract class Creature {
    private String name;
    private String type;
    private String sex;
    private double weight;
    private double height;
    private int age;
    private int hunger;
    private int sleep;
    private int health;
    private static final int HUNGER_MAX = 100;
    private static final int SLEEP_MAX = 100;

    public Creature(String name, String sex, double weight, double height, int age) {
        this.name = name;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.hunger = hunger;
        this.sleep = sleep;
        this.health = health;
        this.type = this.getClass().getSimpleName();
    }

    public void makeNoise() {
        System.out.println("Le " + this.name + " émet un son puissant !");
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSex() {
        return sex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getSleepiness() {
        return sleep;
    }

    public void setSleepiness(int sleep) {
        this.sleep = sleep;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void hungerLevel() {
        if (hunger >= 0 && hunger <= 25) {
            System.out.println(getName() + " a une faim intense.");
            // Logique spécifique pour une faim intense
        } else if (hunger > 25 && hunger <= 50) {
            System.out.println(getName() + " a une faim importante.");
            // Logique spécifique pour une faim importante
        } else if (hunger > 50 && hunger <= 75) {
            System.out.println(getName() + " a une faim modérée.");
            // Logique spécifique pour une faim modéréeé
        } else if (hunger > 75 && hunger <= 100) {
            System.out.println(getName() + " a une faim légère.");
            // Logique spécifique pour une faim légère
        } else {
            System.out.println("La valeur de la faim n'est pas dans une plage valide.");
            // Logique par défaut pour les valeurs de faim en dehors des plages définies
        }
    }

    public void eat() {
        setHunger(HUNGER_MAX);
    }

    public void sleepLevel() {
        if (sleep >= 0 && sleep <= 25) {
            System.out.println(getName() + " a un sommeil intense.");
            // Logique spécifique pour un sommeil intense
        } else if (sleep > 25 && sleep <= 50) {
            System.out.println(getName() + " a un sommeil important.");
            // Logique spécifique pour un sommeil important
        } else if (sleep > 50 && sleep <= 75) {
            System.out.println(getName() + " a un sommeil modéré.");
            // Logique spécifique pour un sommeil modéré
        } else if (sleep > 75 && sleep <= 100) {
            System.out.println(getName() + " a un sommeil léger.");
            // Logique spécifique pour un sommeil léger
        } else {
            System.out.println("La valeur de sommeil n'est pas dans une plage valide.");
            // Logique par défaut pour les valeurs de sommeil en dehors des plages définies
        }
    }

    public void sleep() {
        setSleepiness(SLEEP_MAX);
    }
    public void wakeUp() {setSleepiness(0);}

    public void aging() {++this.age;}
}
