package fr.tmm.modele.lycanthropeColony;

import fr.tmm.modele.creature.species.Lycanthrope;

import java.util.ArrayList;
import java.util.List;

public class Colony {
    private ArrayList<Pack> packs;
    private ArrayList<Lycanthrope> loneWolf;
    public static final List<Character> allRank = List.of('α', 'β', 'γ', 'δ', 'ε', 'ω');

    public void hearPackHowl(PackHowl packHowl) {
        for (Pack pack : this.packs) {
            for (Lycanthrope lycanthrope : pack.getLycanthropes()) {

            }
        }
    }

    public Pack getPackFromLycan(Lycanthrope targetLycan) {
        for (Pack pack : this.packs) {
            for (Lycanthrope lycan : pack.getLycanthropes()) {
                if (lycan.equals(targetLycan)) {
                    return pack;
                }
            }
        }
        return null;
    }
}


/*
Appartenance : -> loup.packHowl() -> doit remonter de loup à pack
    - Log
    - Possibilité de répondre

Domination : -> dominant.dominationHowl(Lycanthrope soumis) -> lancer depuis meute
    - possibilité de répondre

Soumission : -> si a reçu domination
    -Log

Aggression : -> si à reçu un hurlement de domination ou envers un oméga
    - Log

////////////////////////////////////////////////////////////////////////////////

Howl {
    Lycanthrope source
    Lycanthrop dest
    type
}

packHowl() {
    this.listener.packHowl(Lycan source)
}

dominationHowl(Lycan soumis) {
    soumis.hear()
}






 */