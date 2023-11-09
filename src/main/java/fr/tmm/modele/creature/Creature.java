package fr.tmm.modele.creature;

public abstract class Creature {
    private String nomEspece;
    private String sexe;
    private double poids;
    private double taille;
    private int age;
    private boolean indicateurFaim;
    private boolean indicateurSommeil;
    private boolean indicateurSante;
    private static final int AGE_MAX = 100;

    public Creature(String nomEspece, String sexe, double poids, double taille, int age) {
        this.nomEspece = nomEspece;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.indicateurFaim = true;
        this.indicateurSommeil = false;
        this.indicateurSante = true;
    }

    public String getNomEspece() {
        return nomEspece;
    }

    public String getSexe() {
        return sexe;
    }

    public double getPoids() {
        return poids;
    }

    public double getTaille() {
        return taille;
    }

    public int getAge() {
        return age;
    }

    public boolean isIndicateurFaim() {
        return indicateurFaim;
    }

    public boolean isIndicateurSommeil() {
        return indicateurSommeil;
    }

    public boolean isIndicateurSante() {
        return indicateurSante;
    }

    public void setIndicateurFaim(boolean indicateurFaim) {
        this.indicateurFaim = indicateurFaim;
    }

    public void setIndicateurSommeil(boolean indicateurSommeil) {
        this.indicateurSommeil = indicateurSommeil;
    }

    public void setIndicateurSante(boolean indicateurSante) {
        this.indicateurSante = indicateurSante;
    }

    public abstract void emettreSon();

    public abstract void renaître();

    public void manger() {
        if (!indicateurSommeil) {
            indicateurFaim = false;
        }
    }

    public void sEndormir() {
        indicateurSommeil = true;
    }

    public void seRéveiller() {
        indicateurSommeil = false;
    }

    public void soigner() {
        indicateurSante = true;
    }

    public void vieillir() {
        age++;
        if (age >= AGE_MAX || !indicateurSante) {
            System.out.println("La créature est morte.");
        }
    }
}
