package domain;

/**
 * Ítem HiperPoción que restaura 200 puntos de salud (HP) a un Pokémon.
 */
public class HyperPotion extends Item {

    /**
     * Constructor que crea una HiperPoció.
     */
    public HyperPotion() {
        super("HyperPotion");
    }

    /**
     * Aplica el efecto de la HiperPoción al Pokémon objetivo.
     *
     * @param target Pokémon que recibirá la curación
     * @throws PoobkemonException En dos casos:
     *         1. Si se usa en modo Supervivencia (ítems deshabilitados)
     *         2. Si se intenta usar en un Pokémon debilitado (HP = 0)
     *
     * Efecto principal:
     * - Restaura 200 puntos de salud al Pokémon objetivo
     */
    @Override
    public void apply(Pokemon target) throws PoobkemonException {
        if (survivalMode) throw new PoobkemonException("¡Los ítems están deshabilitados en el modo Supervivencia!");
        if (target.isFainted()) throw new PoobkemonException("¡No puedes usar una HiperPoción en un Pokémon debilitado!");
        target.heal(200);
    }
}