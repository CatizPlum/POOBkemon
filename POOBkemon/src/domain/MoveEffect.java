package domain;

import java.io.Serializable;

/**
 * Efectos secundarios que pueden aplicar los movimientos.
 * Incluye cambios de estado, modificadores de stats, efectos de campo, etc.
 */
public enum MoveEffect  implements Serializable {

    BURN,           // Quemadura (reduce Ataque físico, daño por turno)
    POISON,         // Envenenamiento (daño por turno)
    CONFUSION,      // Confusión (puede atacarse a sí mismo)
    FLINCH,         // Retroceso (pierde turno si es golpeado primero)
    DEFENSE_UP,     //Aumenta la defensa
    DEFENSE_DOWN,   // Reduce Defensa
    EVASION_UP,     // Aumenta Evasión
    HP, //El usuario recupera el 50% del HP drenado
    NONE;           // Sin efecto
}