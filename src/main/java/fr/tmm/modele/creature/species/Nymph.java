package fr.tmm.modele.creature.species;
import fr.tmm.modele.creature.Viviparous;
import fr.tmm.modele.creature.methodOfMovement.Walker;

public class Nymph extends Viviparous implements Walker {
    public Nymph(String nomEspece, String sexe, double poids, double taille, int age) {
        super(nomEspece, sexe, poids, taille, age);
    }

    @Override
    public void emettreSon() {
        System.out.println("La Nymph émet un son puissant !");
    }

    @Override
    public void run() {
        System.out.println("La Nymph court.");
    }

    @Override
    public void mettreBas() {
        if (getSexe().equals("Femelle")) {
            System.out.println("La femelle Nymph met bas avec une durée de gestation spécifique.");
        } else {
            System.out.println("Les mâles Nymph ne mettent pas bas.");
        }
    }

    @Override
    public void renaître() {
        System.out.println("La Nymph renaît et prend un nouveau départ !");
        setIndicateurFaim(true);
        setIndicateurSommeil(false);
        setIndicateurSante(true);
    }
}
