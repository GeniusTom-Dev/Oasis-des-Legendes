package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.creature.species.Lycanthrope;
import javafx.util.Pair;

import java.util.Collection;

public class Pack {

    private Collection<Lycanthrope> lycanthropes;
    private Lycanthrope[] coupleAlpha = new Lycanthrope[2];

    public Pack(Lycanthrope maleAlpha, Lycanthrope femaleAlpha) {
/*
        this.coupleAlpha.
*/
    }

    public Collection<Lycanthrope> getLycanthropes() {
        return this.lycanthropes;
    }
}
