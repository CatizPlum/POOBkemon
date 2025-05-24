package domain;

/**
 * Clase utilitaria para verificar la efectividad de un tipo de ataque
 * contra un tipo defensor en un sistema de tipos (por ejemplo, Pokémon).
 *
 * Proporciona métodos para determinar si un ataque es súper efectivo
 * según las reglas básicas de ventajas de tipos.
 */
public class TypeAdvantageChecker {

    /**
     * Determina si un tipo atacante es súper efectivo contra un tipo defensor.
     *
     * Un ataque es súper efectivo si el tipo del atacante tiene ventaja
     * sobre el tipo del defensor según las reglas establecidas.
     *
     * @param attacker el tipo que realiza el ataque
     * @param defender el tipo que recibe el ataque
     * @return {@code true} si el ataque es súper efectivo, {@code false} en caso contrario
     */
    public static boolean isSuperEffective(Type attacker, Type defender) {
        // Relaciones básicas (puedes agregar más)
        if (attacker == Type.FIRE && defender == Type.PLANT) return true;
        if (attacker == Type.WATER && defender == Type.FIRE) return true;
        if (attacker == Type.PLANT && defender == Type.WATER) return true;
        if (attacker == Type.ELECTRIC && defender == Type.WATER) return true;
        if (attacker == Type.FIGHTING && defender == Type.DARK) return true;
        if (attacker == Type.GHOST && defender == Type.PSYCHIC) return true;
        if (attacker == Type.DARK && defender == Type.PSYCHIC) return true;
        if (attacker == Type.ICE && defender == Type.DRAGON) return true;

        return false;
    }
}
