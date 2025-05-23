package domain;

public class Potion extends Item {
    public Potion() {
        super("Potion");
    }

    @Override
    public void apply(Pokemon target) throws PoobkemonException {
        if (survivalMode) throw new PoobkemonException("¡Los ítems están deshabilitados en el modo Supervivencia!");
        if (target.isFainted()) throw new PoobkemonException("¡No puedes usar una Poción en un Pokémon debilitado!");
        target.heal(20);
    }
}
