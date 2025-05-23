package domain;

public class HyperPotion extends Item {
    public HyperPotion() {
        super("HyperPotion");
    }

    @Override
    public void apply(Pokemon target) throws PoobkemonException {
        if (survivalMode) throw new PoobkemonException("¡Los ítems están deshabilitados en el modo Supervivencia!");
        if (target.isFainted()) throw new PoobkemonException("¡No puedes usar una HiperPoción en un Pokémon debilitado!");
        target.heal(200);
    }
}
