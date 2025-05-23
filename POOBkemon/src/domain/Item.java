package domain;

import java.io.Serializable;

/**
 * Clase abstracta que representa un ítem en el juego.
 * Subclases deben implementar el método apply para definir el comportamiento específico del ítem.
 */
public abstract class Item implements Serializable {
    protected String name;
    protected static boolean survivalMode = false;

    /**
     * Constructor del ítem.
     * @param name Nombre del ítem (ej. Potion, SuperPotion)
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * Establece si el juego está en modo Supervivencia.
     * @param enabled true para activar modo Supervivencia, false para desactivarlo
     */
    public static void setSurvivalMode(boolean enabled) {
        survivalMode = enabled;
    }

    /**
     * Aplica el efecto del ítem al Pokémon objetivo.
     * Cada subclase define su propia implementación.
     * @param target Pokémon al que se aplicará el ítem
     * @throws PoobkemonException si no se puede aplicar el ítem
     */
    public abstract void apply(Pokemon target) throws PoobkemonException;

    /**
     * Devuelve el nombre del ítem.
     * @return nombre del ítem
     */
    public String getName() {
        return name;
    }
}
