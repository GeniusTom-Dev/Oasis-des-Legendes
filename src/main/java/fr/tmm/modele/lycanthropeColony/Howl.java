package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.creature.species.Lycanthrope;

public abstract class Howl {

    private Lycanthrope emetteur;

    public Howl(Lycanthrope emetteur) {
        this.emetteur = emetteur;
    }

    public Lycanthrope getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(Lycanthrope emetteur) {
        this.emetteur = emetteur;
    }
}
