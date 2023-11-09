package fr.tmm.modele.creature.species;
import fr.tmm.modele.creature.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Walker;

public class Lycanthrope extends Viviparous implements Walker {
    public Lycanthrope(String nomEspece, String sexe, double poids, double taille, int age) {
        super(nomEspece, sexe, poids, taille, age);
    }
    @Override
    public void run() {
        System.out.println("Le Lycanthrope court.");
    }

    @Override
    public void mettreBas() {
        if (getSexe().equals("Femelle")) {
            System.out.println("La femelle Lycanthrope met bas avec une durée de gestation spécifique.");
        } else {
            System.out.println("Les mâles Lycanthrpe ne mettent pas bas.");
        }
    }
}
