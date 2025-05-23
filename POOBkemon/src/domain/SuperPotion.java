package domain;

public class SuperPotion extends Item {
    public SuperPotion() {
        super("SuperPotion");
    }

    @Override
    public void apply(Pokemon target) throws PoobkemonException {
        if (survivalMode) throw new PoobkemonException("¡Los ítems están deshabilitados en el modo Supervivencia!");
        if (target.isFainted()) throw new PoobkemonException("¡No puedes usar una SuperPoción en un Pokémon debilitado!");
        target.heal(50);
    }
}
