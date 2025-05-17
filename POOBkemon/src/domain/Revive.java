package domain;

import domain.Item;
import domain.Pokemon;
import domain.PoobkemonException;

public class Revive extends Item {
    public Revive() {
        super("Revive");
    }

    @Override
    public void apply(Pokemon target) throws PoobkemonException {
        if (!target.isFainted()) {
            throw new PoobkemonException("You can only use Revive on fainted Pokémon!");
        }
        target.heal(target.getMaxHP() / 2); // Asumo que existe getMaxHP()
    }
}