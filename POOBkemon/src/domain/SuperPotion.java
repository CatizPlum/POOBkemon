package domain;

/**
 * Representa un objeto consumible de tipo SuperPoción dentro del sistema Poobkemon.
 * La SuperPoción restaura una cantidad significativa de puntos de salud a un Pokémon que no esté debilitado.
 * Este ítem no puede usarse en Pokémon debilitados ni cuando el modo Supervivencia está activo.
 */
public class SuperPotion extends Item {

    /**
     * Crea una nueva instancia de SuperPoción con el nombre predefinido "SuperPotion".
     */
    public SuperPotion() {
        super("SuperPotion");
    }

    /**
     * Aplica el efecto de la SuperPoción al Pokémon objetivo.
     * Si el modo Supervivencia está activo o el Pokémon está debilitado, se lanza una excepción.
     * De lo contrario, el Pokémon recupera 50 puntos de salud.
     * @param target El Pokémon que recibirá la curación.
     * @throws PoobkemonException Si los ítems están deshabilitados en modo Supervivencia
     * o si el Pokémon está debilitado.
     */
    @Override
    public void apply(Pokemon target) throws PoobkemonException {
        if (survivalMode)
            throw new PoobkemonException("¡Los ítems están deshabilitados en el modo Supervivencia!");
        if (target.isFainted())
            throw new PoobkemonException("¡No puedes usar una SuperPoción en un Pokémon debilitado!");
        target.heal(50);
    }
}
