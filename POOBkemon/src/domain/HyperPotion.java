package domain;

import domain.Item;
import domain.Pokemon;
import domain.PoobkemonException;

public class HyperPotion extends Item {
    public HyperPotion() {
        super("Hyper Potion");
    }

    @Override
    public void apply(Pokemon target) throws PoobkemonException {
        if (target.isFainted()) {
            throw new PoobkemonException("Cannot heal a fainted Pokémon!");
        }
        target.heal(200);
    }
}