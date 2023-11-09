package fr.tmm;

import fr.tmm.modele.creature.species.Dragon;

/**
 * La classe de lancement du logiciel appelant l'application.
 */
public class Main {
    public static void main(String[] args) {
//        App.main();
        Dragon dragon = new Dragon("Patrick", "M", 0.5, 0.5, 0);
        System.out.println(dragon.toString());
    }
}
