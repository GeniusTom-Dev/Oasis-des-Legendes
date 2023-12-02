package fr.tmm.modele;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.species.Human;
import fr.tmm.modele.enclosure.Enclosure;

public class ZooMaster extends Human {
    public ZooMaster(String name, String sex, double weight, double height, int age) {
        super(name, sex, weight, height, age);
    }

    public boolean transferer(Creature creature, Enclosure source, Enclosure dest) {
        // TODO
        // si le transfert a pu etre realiser -> return true
        // sinon si il n'a pas pu etre realiser car la creature n'existe pas, n'est pas presente dans l'enclo source,
        // l'enclo dest n'est pas du bon type, l'enclos dest est plein -> return false
        return false;
    }
}
