package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.Oviparous;
import fr.tmm.modele.creature.methodOfMovement.Flyer;
import fr.tmm.modele.creature.methodOfMovement.Swimmer;
import fr.tmm.modele.creature.methodOfMovement.Walker;

public class Dragon extends Oviparous implements Walker, Swimmer, Flyer {
    public Dragon(String nomEspece, String sexe, double poids, double taille, int age) {
        super(nomEspece, sexe, poids, taille, age);
    }

    @Override
    public void swim() {
        System.out.println("Le Dragon nage dans les profondeurs de l'océan.");
    }

    @Override
    public void run() {
        System.out.println("Le Dragon court.");
    }

    @Override
    public void fly() {
        System.out.println("Le Dragon vole.");
    }

    @Override
    public void pondreOeuf() {
        if (getSexe().equals("Femelle")) {
            System.out.println("La femelle Dragon a pondu un œuf avec une durée d'incubation spécifique.");
        } else {
            System.out.println("Les mâles Dragon ne pondent pas d'œufs.");
        }
    }

    public void renaître() {
        System.out.println("Le Dragon renaît et prend un nouveau départ !");
        setIndicateurFaim(true);
        setIndicateurSommeil(false);
        setIndicateurSante(true);
    }
}
