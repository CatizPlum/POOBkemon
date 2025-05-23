package domain;

public class Revive extends Item {
    public Revive() {
        super("Revive");
    }

    @Override
    public void apply(Pokemon target) throws PoobkemonException {
        if (survivalMode) throw new PoobkemonException("¡Los ítems están deshabilitados en el modo Supervivencia!");
        if (!target.isFainted()) throw new PoobkemonException("¡Solo puedes usar Revivir en un Pokémon debilitado!");
        target.heal(target.getMaxHP() / 2);
    }
}
