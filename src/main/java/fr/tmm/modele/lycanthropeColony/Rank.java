package fr.tmm.modele.lycanthropeColony;

public enum Rank {
    ALPHA('α'),
    BETA('β'),
    GAMMA('γ'),
    DELTA('δ'),
    EPSILON('ε'),
    ZETA('ζ'),
    ETA('η'),
    THETA('θ'),
    IOTA('ι'),
    KAPPA('κ'),
    LAMBDA('λ'),
    MU('μ'),
    NU('ν'),
    XI('ξ'),
    OMICRON('ο'),
    PI('π'),
    RHO('ρ'),
    SIGMA('σ'),
    TAU('τ'),
    UPSILON('υ'),
    PHI('φ'),
    CHI('χ'),
    PSI('ψ'),
    OMEGA('ω');

    Rank(char α) {
    }

    /**
     * Returns a rank based on an index.
     *
     * @param index the index for which the rank should be returned.
     * @return the rank corresponding to the index.
     * @throws IllegalArgumentException if the index is out of bounds.
     */
    public static Rank getRankByIndex(int index) {
        if (index >= 0 && index < values().length) {
            return values()[index];
        } else {
            return OMEGA;
        }
    }

}
