package domain;

import domain.Item;
import domain.Pokemon;
import domain.PoobkemonException;

public class SuperPotion extends Item {
    public SuperPotion() {
        super("Super Potion");
    }

    @Override
    public void apply(Pokemon target) throws PoobkemonException {
        if (target.isFainted()) {
            throw new PoobkemonException("Cannot heal a fainted Pokémon!");
        }
        target.heal(50);
    }
}