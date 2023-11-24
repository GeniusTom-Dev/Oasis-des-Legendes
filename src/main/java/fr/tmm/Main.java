package fr.tmm;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.methodOfMovement.Flyer;
import fr.tmm.modele.creature.species.Dragon;
import fr.tmm.modele.creature.species.Phenix;
import fr.tmm.modele.enclosure.Enclosure;

/**
 * La classe de lancement du logiciel appelant l'application.
 */
public class Main extends Thread {

    static Zoo zoo;

    static boolean gameIsRunning = true;

    public static void main(String[] args) {
//        App.main();
        /*Phenix phenix = new Phenix("Patrick", "M", 0.5, 0.5, 0);
        Enclosure test = new Enclosure("dede", 50.0, 50);
        phenix.setDeathListener(test);*/
        //System.out.println(dragon.toString());
        /*launchSatietyThread();
        launchEnergyThread();
        launchAgingThread();*/
        //dragon.run();
    }

    // ---  A TESTER ---

    public static void launchSatietyThread() {
        Thread threadSatiety = new Thread(() -> {
            //while (gameIsRunning) {
            System.out.println("hunger = 100"); // Test

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (Creature creature : zoo.getAllCreatures()) {
                creature.getHungrier();
            }

            System.out.println("hunger = 75"); // Test
            //}
        });
        threadSatiety.start();
    }

    public static void launchEnergyThread() {
        Thread threadEnergy = new Thread(() -> {
            //while (gameIsRunning) {
            System.out.println("energy = 100"); // Test

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (Creature creature : zoo.getAllCreatures()) {
                creature.getSleepier();
            }

            System.out.println("energy = 75"); // Test
            //}
        });
        threadEnergy.start();
    }

    public static void launchAgingThread() {
        Thread threadAging = new Thread(() -> {
            //while (gameIsRunning) {
            System.out.println("age = 100"); // Test

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (Creature creature : zoo.getAllCreatures()) {
                creature.aging();
            }

            System.out.println("age = 75"); // Test
            //}
        });
        threadAging.start();
    }

}
