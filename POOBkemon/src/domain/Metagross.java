package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa al Pokémon Metagross, un Pokémon de tipo Acero/Psíquico.
 * Conocido como el Pokémon Computacional, combina una coraza de acero con cuatro cerebros
 * que le permiten realizar cálculos avanzados durante el combate.
 */
public class Metagross extends AbstractPokemon {

    /**
     * Indica si Metagross está en modo de cálculo intensivo.
     * Cuando es true:
     * - Aumenta su ataque y ataque especial en 15%
     * - Representa su capacidad de análisis de combate
     * - Mejora su precisión y estrategia
     */
    private boolean calculationMode;

    /**
     * Constructor de Metagross. Inicializa sus estadísticas base, tipos y movimientos.
     * Características principales:
     * - Ataque físico extremadamente alto (405)
     * - Defensa física excepcional (394)
     * - Tipo dual Acero/Psíquico con múltiples resistencias
     * - Modo de cálculo inicialmente desactivado
     */
    public Metagross() {
        this.name = "Metagross";
        this.primaryType = Type.STEEL;
        this.secondaryType = Type.PSYCHIC;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 405;
        this.defense = 394;
        this.specialAttack = 317;
        this.specialDefense = 306;
        this.speed = 262;
        this.calculationMode = false;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Metagross puede aprender por defecto.
     * Incluye movimientos de varios tipos para versatilidad en combate.
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
     * Método para recibir daño con reducción del 15% por su armadura de acero.
     * @param amount Cantidad de daño recibido (se reduce un 15%)
     *
     * Efecto secundario:
     * - Entra en modo cálculo si recibe daño significativo (>20% HP máximo)
     */
    @Override
    public void takeDamage(int amount) {
        // Reducción por armadura de acero
        int reducedAmount = (int)(amount * 0.85);
        currentHP = Math.max(0, currentHP - reducedAmount);

        // Activación del modo cálculo por daño significativo
        if (amount > maxHP * 0.2 && !calculationMode) {
            calculationMode = true;
            System.out.println("¡Metagross entra en modo de cálculo avanzado para analizar a su oponente!");
        }
    }

    // Métodos específicos de Metagross

    /**
     * Verifica si Metagross está en modo de cálculo intensivo.
     * @return true si está en modo cálculo, false en caso contrario
     */
    public boolean isInCalculationMode() {
        return calculationMode;
    }

    /**
     * Activa manualmente el modo de cálculo intensivo.
     * Efectos:
     * - Aumenta ataque y ataque especial en 15%
     * - Mejora la precisión de movimientos
     * - Solo se puede activar una vez por combate
     */
    public void activateCalculationMode() {
        if (!calculationMode) {
            calculationMode = true;
            System.out.println("¡Metagross activa sus cuatro cerebros para cálculos avanzados!");

            // Mejora ofensiva
            this.attack = (int)(this.attack * 1.15);
            this.specialAttack = (int)(this.specialAttack * 1.15);
        } else {
            System.out.println("Metagross ya está en modo de cálculo.");
        }
    }

    /**
     * Habilidad especial: Levitación Magnética.
     * Efectos:
     * - Aumenta velocidad en 30%
     * - Otorga inmunidad temporal a movimientos de tipo Tierra
     */
    public void magneticLevitation() {
        System.out.println("¡Metagross utiliza sus poderes magnéticos para levitar!");
        this.speed = (int)(this.speed * 1.3);
        // En implementación completa, añadir flag de inmunidad a Tierra
    }

    /**
     * Movimiento defensivo: Iron Defense.
     * Efectos:
     * - Aumenta defensa en 20%
     * - Aumenta defensa especial en 10%
     * - Representa el endurecimiento de su armadura
     */
    public void ironDefense() {
        this.defense = (int)(this.defense * 1.2);
        this.specialDefense = (int)(this.specialDefense * 1.1);
        System.out.println("¡Metagross refuerza su coraza de acero! Su defensa aumenta considerablemente.");
    }
}