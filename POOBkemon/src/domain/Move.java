package domain;

import java.io.Serializable;

/**
 * Clase que representa un movimiento que puede usar un Pokémon en combate.
 * Contiene toda la información necesaria para calcular daños, efectos secundarios
 * y mecánicas de batalla. Implementa Serializable para permitir guardar/recuperar
 * estados de juego.
 */
public class Move implements Serializable, Cloneable {
    private String name;
    private Type type;
    private int power;
    private int accuracy;
    private int pp;
    private MoveEffect secondaryEffect;
    private int priority;
    private MoveCategory category;
    private int effectChance;

    /**
     * Constructor completo para crear un movimiento.
     *
     * @param name Nombre del movimiento
     * @param type Tipo del movimiento (Fuego, Agua, etc.)
     * @param power Poder base del movimiento
     * @param accuracy Precisión del movimiento (0-100)
     * @param pp Puntos de poder (usos disponibles)
     * @param secondaryEffect Efecto secundario del movimiento
     * @param priority Prioridad en el turno (valores más altos actúan primero)
     * @param category Categoría (FÍSICO, ESPECIAL, ESTADO)
     * @param effectChance Probabilidad de que ocurra el efecto secundario (0-100)
     */
    public Move(String name, Type type, int power, int accuracy, int pp,
                MoveEffect secondaryEffect, int priority, MoveCategory category,
                int effectChance) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.secondaryEffect = secondaryEffect;
        this.priority = priority;
        this.category = category;
        this.effectChance = effectChance;
    }

    // Getters
    public String getName() { return name; }
    public Type getType() { return type; }
    public int getPower() { return power; }
    public int getAccuracy() { return accuracy; }
    public int getPp() { return pp; }
    public int getPriority() { return priority; }
    public MoveCategory getCategory() { return category; }
    public MoveEffect getSecondaryEffect() { return secondaryEffect; }
    public int getEffectChance() { return effectChance; }

    // Setters
    public void setPower(int power) {
        this.power = power;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    /**
     * Calcula la efectividad del movimiento contra los tipos del defensor.
     *
     * @param defenderPrimaryType Tipo primario del defensor
     * @param defenderSecondaryType Tipo secundario del defensor (puede ser null)
     * @return Multiplicador de efectividad (0, 0.5, 1, 2)
     */
    public double getEffectivenessMultiplier(Type defenderPrimaryType, Type defenderSecondaryType) {
        return Table.adv(this.type, defenderPrimaryType,
                defenderSecondaryType != null ? defenderSecondaryType : defenderPrimaryType);
    }

    /**
     * Calcula el daño que este movimiento infligirá al defensor.
     *
     * @param attacker Pokémon que usa el movimiento
     * @param defender Pokémon que recibe el movimiento
     * @return Cantidad de daño calculada
     */
    public int calculateDamage(Pokemon attacker, Pokemon defender) {
        // Movimientos de estado no hacen daño
        if (this.category == MoveCategory.STATUS) {
            return 0;
        }

        // Selección de estadísticas según categoría
        double effectiveAttack = (this.category == MoveCategory.PHYSICAL) ?
                attacker.getAttack() : attacker.getSpecialAttack();
        double effectiveDefense = (this.category == MoveCategory.PHYSICAL) ?
                defender.getDefense() : defender.getSpecialDefense();

        // Cálculo de STAB (Same Type Attack Bonus)
        double stab = 1.0;
        if (this.type == attacker.getPrimaryType() ||
                (attacker.getSecondaryType() != null && this.type == attacker.getSecondaryType())) {
            stab = 1.5;
        }

        // Efectividad contra tipos del defensor
        double typeEffectiveness = getEffectivenessMultiplier(
                defender.getPrimaryType(),
                defender.getSecondaryType()
        );

        // Fórmula de daño simplificada
        int level = 50; // Nivel asumido
        double baseDamage = ((2 * level / 5 + 2) * effectiveAttack * this.power / effectiveDefense / 50) + 2;

        // Factores aleatorios y modificadores
        double randomFactor = 0.85 + (Math.random() * 0.15); // Variación 85-100%
        double finalDamage = baseDamage * stab * typeEffectiveness * randomFactor;

        // Debug (puede comentarse en producción)
        System.out.println("Cálculo de daño:");
        System.out.println("- Ataque efectivo: " + effectiveAttack);
        System.out.println("- Defensa efectiva: " + effectiveDefense);
        System.out.println("- STAB: " + stab);
        System.out.println("- Efectividad de tipo: " + typeEffectiveness);
        System.out.println("- Factor aleatorio: " + randomFactor);
        System.out.println("- Daño final: " + finalDamage);

        return (int)Math.max(1, finalDamage); // Mínimo 1 de daño
    }

    @Override
    public String toString() {
        return name + " (" + type + ", " + power + ")";
    }

    @Override
    public Move clone() {
        try {
            return (Move) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("La clonación no está soportada", e);
        }
    }
}