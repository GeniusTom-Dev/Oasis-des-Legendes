package fr.tmm;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.species.Dragon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * La classe de lancement du logiciel appelant l'application.
 */
public class Main extends Thread {

    static Zoo zoo;

    static boolean gameIsRunning = true;

    public static void main(String[] args) {
//        App.main();
        Dragon dragon = new Dragon("Patrick", "M", 0.5, 0.5, 0);
        System.out.println(dragon.toString());
        try{
            String content = new String(Files.readAllBytes(Paths.get("chemin/vers/le/fichier.json")));
//            JSONObject json = new JSONObject(content);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }



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

}
