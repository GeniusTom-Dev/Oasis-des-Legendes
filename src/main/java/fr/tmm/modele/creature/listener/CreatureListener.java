package fr.tmm.modele.creature.listener;

import fr.tmm.modele.creature.Creature;
import fr.tmm.modele.creature.reproduction.Egg;

public interface CreatureListener {

    void onCreatureDeath(Creature creature);
    void onEggHatching(Egg egg);
    void onCreatureBirth(Creature baby);
    void onEggLaying(Egg egg);
}
