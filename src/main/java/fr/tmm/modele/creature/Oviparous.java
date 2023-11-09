package fr.tmm.modele.creature;

public abstract class Oviparous extends Creature {
    public Viviparous(String nomEspece, String sexe, double poids, double taille, int age) {
        super(nomEspece, sexe, poids, taille, age);
    }

    public abstract void pondreOeuf();
}
