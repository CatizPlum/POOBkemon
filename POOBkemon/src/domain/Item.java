package domain;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private String effect; // "heal20", "heal50", "heal200", "revive"
    private static boolean survivalMode = false;
    /**
     * Constructor del ítem.
     * @param name Nombre del ítem (e.g. Potion, SuperPotion, HyperPotion, Revive)
     * @param effect Efecto del ítem (e.g. "heal20", "revive")
     */
    public Item(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }

    /**
     * Establece si el juego está en modo Supervivencia.
     * @param enabled true para activar modo Supervivencia, false para desactivarlo
     */
    public static void setSurvivalMode(boolean enabled) {
        survivalMode = enabled;
    }

    /**
     * Aplica el ítem al Pokémon objetivo.
     * Lanza una excepción si el ítem no es aplicable por el estado del Pokémon.
     * <p>
     * - heal20, heal50, heal200: Solo se aplican si el Pokémon NO está debilitado.
     * - revive: Solo se aplica si el Pokémon está debilitado.
     *
     * @param target Pokémon al que se le aplicará el ítem.
     * @throws PoobkemonException si el ítem no se puede aplicar por las condiciones.
     */
    public void apply(Pokemon target) throws PoobkemonException {
        if (survivalMode) {
            throw new PoobkemonException("¡Los ítems están deshabilitados en el modo Supervivencia!");
        }

        if (effect.equalsIgnoreCase("revive")) {
            if (!target.isFainted()) {
                throw new PoobkemonException("¡Solo puedes usar Revivir en un Pokémon debilitado!");
            }
            target.heal(target.getMaxHP() / 2); // Revive con 50% de PS máximos
        } else if (effect.startsWith("heal")) {
            int healAmount = Integer.parseInt(effect.substring(4));
            if (target.isFainted()) {
                throw new PoobkemonException("¡No puedes curar a un Pokémon debilitado!");
            }
            target.heal(healAmount);
        } else {
            throw new PoobkemonException("Efecto de ítem desconocido: " + effect);
        }
    }

    public String getName() {
        return name;
    }
}

