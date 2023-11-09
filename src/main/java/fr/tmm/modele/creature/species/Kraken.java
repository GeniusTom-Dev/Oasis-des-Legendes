package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.Oviparous;
import fr.tmm.modele.creature.methodOfMovement.Swimmer;

public class Kraken extends Oviparous implements Swimmer {
    public Kraken(String nomEspece, String sexe, double poids, double taille, int age) {
        super(nomEspece, sexe, poids, taille, age);
    }

    @Override
    public void emettreSon() {
        System.out.println("Le Kraken émet un son puissant !");
    }

    @Override
    public void swim() {
        System.out.println("Le Kraken nage dans les profondeurs de l'océan.");
    }

    @Override
    public void pondreOeuf() {
        if (getSexe().equals("Femelle")) {
            System.out.println("La femelle Kraken a pondu un œuf avec une durée d'incubation spécifique.");
        } else {
            System.out.println("Les mâles Kraken ne pondent pas d'œufs.");
        }
    }
}
