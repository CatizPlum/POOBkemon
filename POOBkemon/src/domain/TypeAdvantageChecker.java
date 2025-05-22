package domain;

public class TypeAdvantageChecker {
    /**
     * Determina si un tipo atacante es súper efectivo contra un tipo defensor.
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
