package fr.tmm;

import fr.tmm.modele.Zoo;
import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.species.Dragon;

import java.io.FileNotFoundException;

import static fr.tmm.save.readFile.readJSON;

/**
 * La classe de lancement du logiciel appelant l'application.
 */
public class Main extends Thread {

    static Zoo zoo;

    static boolean gameIsRunning = true;

    public static void main(String[] args) {
        try{
            readJSON("src/main/resources/config/decrement.json");
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
