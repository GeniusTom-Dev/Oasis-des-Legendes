package fr.tmm.modele.creature;

public abstract class Viviparous extends Creature{
    public Viviparous(String nomEspece, String sexe, double poids, double taille, int age) {
        super(nomEspece, sexe, poids, taille, age);
    }

    public String calve(int nbChild){
        try {
            if(this.getSex().equals("Femelle")){
                String strChild = nbChild > 1 ? " enfants." : "enfant.";
                System.out.println(this.getName() + ", la femelle " + this.getType() + " vient de mettre bas " + nbChild + strChild);
            }else{
                throw new Exception("Les mâles ne peuvent pas mettre bas");
            }
        }catch (Exception e){
            return e.getMessage();
        }
        return "";
    };
}
