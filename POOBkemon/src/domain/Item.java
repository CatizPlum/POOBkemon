package domain;

import java.io.Serializable;
/**
 * Constructor del ítem.
 * @param name Nombre del ítem (e.g. Potion, SuperPotion, HyperPotion, Revive)
 * @param effect Efecto del ítem (e.g. "heal20", "revive")
 */

public class Item implements Serializable {
    private String name;
    private String effect; // "heal20", "heal50", "heal200", "revive"
    private static boolean survivalMode = false;

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
     * Lógica:
     * - Potion: cura 20 PS si no está debilitado.
     * - SuperPotion: cura 50 PS si no está debilitado.
     * - HyperPotion: cura 200 PS si no está debilitado.
     * - Revive: solo funciona si el Pokémon está debilitado, cura al 50% de sus PS máximos.
     */
    public void apply(Pokemon target) throws PoobkemonException {
        if (survivalMode) {
            throw new PoobkemonException("¡Los ítems están deshabilitados en el modo Supervivencia!");
        }

        switch (effect.toLowerCase()) {
            case "heal20":
                if (target.isFainted()) {
                    throw new PoobkemonException("¡No puedes usar una Poción en un Pokémon debilitado!");
                }
                target.heal(20);
                break;

            case "heal50":
                if (target.isFainted()) {
                    throw new PoobkemonException("¡No puedes usar una SuperPoción en un Pokémon debilitado!");
                }
                target.heal(50);
                break;

            case "heal200":
                if (target.isFainted()) {
                    throw new PoobkemonException("¡No puedes usar una HiperPoción en un Pokémon debilitado!");
                }
                target.heal(200);
                break;

            case "revive":
                if (!target.isFainted()) {
                    throw new PoobkemonException("¡Solo puedes usar Revivir en un Pokémon debilitado!");
                }
                target.heal(target.getMaxHP() / 2);
                break;

            default:
                throw new PoobkemonException("Efecto de ítem desconocido: " + effect);
        }
    }

    public String getName() {
        return name;
    }
}
