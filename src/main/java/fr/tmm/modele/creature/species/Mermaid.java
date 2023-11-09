package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Swimmer;

public class Mermaid extends Viviparous implements Swimmer {
    public Mermaid(String nomEspece, String sexe, double poids, double taille, int age) {
        super(nomEspece, sexe, poids, taille, age);
    }

    @Override
    public void swim() {
        System.out.println("Le Mermaid nage dans les profondeurs de l'océan.");
    }

    @Override
    public void mettreBas() {
        if (getSexe().equals("Femelle")) {
            System.out.println("La femelle Mermaid met bas avec une durée de gestation spécifique.");
        } else {
            System.out.println("Les mâles Mermaid ne mettent pas bas.");
        }
    }
}
