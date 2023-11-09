package fr.tmm.modele.creature.species;

import fr.tmm.modele.creature.Oviparous;
import fr.tmm.modele.creature.methodOfMovement.Flyer;

public class Phenix extends Oviparous implements Flyer {
    public Phenix(String nomEspece, String sexe, double poids, double taille, int age) {
        super(nomEspece, sexe, poids, taille, age);
    }

    @Override
    public void emettreSon() {
        System.out.println("Le Phenix émet un son puissant !");
    }

    @Override
    public void fly() {
        System.out.println("Le Phenix vole.");
    }

    @Override
    public void pondreOeuf() {
        if (getSexe().equals("Femelle")) {
            System.out.println("La femelle Phenix a pondu un œuf avec une durée d'incubation spécifique.");
        } else {
            System.out.println("Les mâles Phenix ne pondent pas d'œufs.");
        }
    }

    @Override
    public void renaître() {
        System.out.println("Le Phénix renaît de ses cendres et prend un nouveau départ !");
        setIndicateurFaim(true);
        setIndicateurSommeil(false);
        setIndicateurSante(true);
    }
}
