import fr.tmm.modele.creature.species.Dragon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Creature {
    @Test
    void initCreature(){
        String name = "Hugo";
        Dragon dragon = new Dragon(name, "M", 0.5, 0.5, 0);
        assertEquals(name, dragon.getName());
    }
}
