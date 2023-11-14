package fr.tmm.modele.creature;

public abstract class Oviparous extends Creature {

    public Oviparous(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    public String layEgg(int nbEgg) {
        try {
            if (this.getSex().equals("Femelle")) {
                String strEgg = nbEgg > 1 ? " œufs" : "œuf";
                System.out.println(this.getName() + ", la femelle " + this.getType() + " vient de pondre " + nbEgg + strEgg);
            } else {
                throw new Exception("Les mâles ne pondent pas d'œufs");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }
}
