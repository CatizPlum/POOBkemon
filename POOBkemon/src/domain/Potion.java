package domain;

/**
 * Representa un objeto consumible de tipo Poción dentro del sistema Poobkemon.
 * La poción permite curar a un Poobkemon, restaurando una cantidad fija de HP,
 * salvo que esté deshabilitado por el modo Supervivencia.
 * Este ítem no puede ser usado en Poobkemons que ya estén debilitados.
 */
public class Potion extends Item {

    /**
     * Crea una nueva instancia de Poción con el nombre predefinido "Potion".
     */
    public Potion() {
        super("Potion");
    }

    /**
     * Aplica el efecto de la poción al Poobkemon objetivo.
     * Si el modo Supervivencia está activo o el Poobkemon está debilitado, se lanza una excepción.
     * De lo contrario, el Poobkemon recupera 20 puntos de vida.
     * @param target El Poobkemon que recibirá la curación.
     * @throws PoobkemonException Si el ítem no puede usarse por estar en modo Supervivencia
     *                             o si el Poobkemon está debilitado.
     */
    @Override
    public void apply(Pokemon target) throws PoobkemonException {
        if (survivalMode)
            throw new PoobkemonException("¡Los ítems están deshabilitados en el modo Supervivencia!");
        if (target.isFainted())
            throw new PoobkemonException("¡No puedes usar una Poción en un Pokémon debilitado!");
        target.heal(20);
    }
}
