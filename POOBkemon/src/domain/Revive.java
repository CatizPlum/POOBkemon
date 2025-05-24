package domain;

/**
 * Representa un objeto consumible de tipo Revivir dentro del sistema Poobkemon.
 * El objeto Revivir permite restaurar la vida de un Pokémon debilitado,
 * recuperando la mitad de sus puntos máximos de salud.
 * Este ítem solo puede ser usado en Pokémon que estén debilitados.
 */
public class Revive extends Item {

    /**
     * Crea una nueva instancia de Revivir con el nombre predefinido "Revive".
     */
    public Revive() {
        super("Revive");
    }

    /**
     * Aplica el efecto de Revivir al Pokémon objetivo.
     * Si el modo Supervivencia está activo, o el Pokémon no está debilitado, se lanza una excepción.
     * De lo contrario, el Pokémon recupera la mitad de su HP máximo.
     * @param target El Pokémon que será revivido.
     * @throws PoobkemonException Si el ítem no puede usarse por estar en modo Supervivencia
     *                             o si el Pokémon no está debilitado.
     */
    @Override
    public void apply(Pokemon target) throws PoobkemonException {
        if (survivalMode)
            throw new PoobkemonException("¡Los ítems están deshabilitados en el modo Supervivencia!");
        if (!target.isFainted())
            throw new PoobkemonException("¡Solo puedes usar Revivir en un Pokémon debilitado!");
        target.heal(target.getMaxHP() / 2);
    }
}
