package domain;

public class Move {
    private String name;
    private Type type;
    private int power;
    private int accuracy;
    private int pp;
    private MoveEffect secondaryEffect;  // Cambiado de String a MoveEffect
    private int priority;
    private MoveCategory category;
    private int effectChance;  // probabilidad del efecto (0-100)

    // Constructor modificado
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

    // Getters existentes (sin cambios)
    public String getName() { return name; }
    public Type getType() { return type; }
    public int getPower() { return power; }
    public int getAccuracy() { return accuracy; }
    public int getPp() { return pp; }
    public int getPriority() { return priority; }
    public MoveCategory getCategory() { return category; }

    // Getter modificado para secondaryEffect
    public MoveEffect getSecondaryEffect() {
        return secondaryEffect;
    }

    // Nuevo getter para effectChance
    public int getEffectChance() {
        return effectChance;
    }

    // Setter existente (sin cambios)
    public void setPower(int power) {
        this.power = power;
    }

    // Métodos existentes (sin cambios)
    @Override
    public String toString() {
        return name + " (" + type + ", " + power;
    }

    public double getEffectivenessMultiplier(Type defenderPrimaryType, Type defenderSecondaryType) {
        return Table.adv(this.type, defenderPrimaryType,
                defenderSecondaryType != null ? defenderSecondaryType : defenderPrimaryType);
    }

    /**
     * Calcula el daño que hace este movimiento cuando es usado por el atacante contra el defensor
     * @param attacker Pokémon atacante
     * @param defender Pokémon defensor
     * @return Cantidad de daño calculado
     */
    public int calculateDamage(Pokemon attacker, Pokemon defender) {
        // Si es un movimiento de estado, no hace daño
        if (this.category == MoveCategory.STATUS) {
            return 0;
        }

        // Seleccionar estadísticas apropiadas según la categoría del movimiento
        double effectiveAttack = (this.category == MoveCategory.PHYSICAL) ?
                attacker.getAttack() : attacker.getSpecialAttack();
        double effectiveDefense = (this.category == MoveCategory.PHYSICAL) ?
                defender.getDefense() : defender.getSpecialDefense();

        // Calcular STAB (Same Type Attack Bonus)
        double stab = 1.0;
        if (this.type == attacker.getPrimaryType() ||
                (attacker.getSecondaryType() != null && this.type == attacker.getSecondaryType())) {
            stab = 1.5;
        }

        // Calcular efectividad de tipo
        double typeEffectiveness = getEffectivenessMultiplier(
                defender.getPrimaryType(),
                defender.getSecondaryType()
        );

        // Calcular daño base
        // Usando fórmula simplificada: ((2 * nivel / 5 + 2) * ataque * poder / defensa / 50) + 2
        int level = 50; // Nivel asumido para cálculos
        double baseDamage = ((2 * level / 5 + 2) * effectiveAttack * this.power / effectiveDefense / 50) + 2;

        // Aplicar modificadores
        double randomFactor = 0.85 + (Math.random() * 0.15); // Factor aleatorio entre 0.85 y 1.0
        double finalDamage = baseDamage * stab * typeEffectiveness * randomFactor;

        // Mostrar información sobre el cálculo (para depuración)
        System.out.println("Cálculo de daño:");
        System.out.println("- Ataque efectivo: " + effectiveAttack);
        System.out.println("- Defensa efectiva: " + effectiveDefense);
        System.out.println("- STAB: " + stab);
        System.out.println("- Efectividad de tipo: " + typeEffectiveness);
        System.out.println("- Factor aleatorio: " + randomFactor);
        System.out.println("- Daño final: " + finalDamage);

        // Devolver el daño redondeado a entero
        return (int)Math.max(1, finalDamage); // Mínimo 1 punto de daño
    }
}