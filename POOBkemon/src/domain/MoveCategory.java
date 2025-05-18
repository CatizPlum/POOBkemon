package domain;

import java.io.Serializable;

/**
 * Enumeración que representa las categorías de movimientos Pokémon.
 * Define los tres tipos fundamentales de movimientos en el sistema de combate.
 */
public enum MoveCategory implements Serializable {
    /**
     * Movimiento físico - El daño se calcula basado en el ataque físico del atacante
     * y la defensa física del defensor. Ejemplo: Puñetazo, Patada.
     */
    PHYSICAL,

    /**
     * Movimiento especial - El daño se calcula basado en el ataque especial del atacante
     * y la defensa especial del defensor. Ejemplo: Lanzallamas, Rayo.
     */
    SPECIAL,

    /**
     * Movimiento de estado - No causa daño directo, pero altera las condiciones de combate.
     * Puede afectar estadísticas, causar estados alterados o cambiar el entorno.
     * Ejemplo: Aumentar defensa, Dormir, Niebla.
     */
    STATUS
}
