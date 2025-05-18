package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Machamp, un Pokémon de tipo Lucha puro.
 * Conocido por su increíble fuerza física y sus cuatro brazos musculados.
 * Especializado en ataques físicos brutales y combate cuerpo a cuerpo.
 */
public class Machamp extends AbstractPokemon {

    /**
     * Constructor de Machamp. Inicializa sus estadísticas base, tipo y movimientos.
     * Características principales:
     * - Ataque físico extremadamente alto (394)
     * - Defensa física sólida (284)
     * - HP considerable (384)
     * - Velocidad baja (207)
     * - Tipo Lucha puro
     */
    public Machamp() {
        this.name = "Machamp";
        this.primaryType = Type.FIGHTING;
        this.secondaryType = null;
        this.maxHP = 384;
        this.currentHP = maxHP;
        this.attack = 394; // Muy alto
        this.defense = 284;
        this.specialAttack = 218;
        this.specialDefense = 273;
        this.speed = 207;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Machamp puede aprender por defecto.
     * Incluye movimientos físicos y técnicas básicas de combate.
     */
    @Override
    protected void initializeMoves() {
        learnMove("Leer");
        learnMove("Quick Attack");
        learnMove("Double Team");
        learnMove("Knock Off");
        learnMove("Future Sight");
        learnMove("Water Pulse");
    }

    /**
     * Habilidad pasiva: Brute Force Boost.
     * Aumenta el poder de todos los movimientos físicos en un 25%.
     *
     * @param move El movimiento a potenciar
     *
     * Efecto:
     * - Incrementa el poder base de movimientos físicos
     * - Muestra mensaje informando del aumento de poder
     * - No afecta movimientos especiales o de estado
     */
    public void applyPassiveAbility(Move move) {
        if (move.getCategory() == MoveCategory.PHYSICAL) {
            double boostedPower = move.getPower() * 1.25;
            move.setPower((int) boostedPower);
            System.out.println(this.name + "'s BruteForceBoost increases " +
                    move.getName() + "'s power to " + move.getPower());
        }
    }
}
