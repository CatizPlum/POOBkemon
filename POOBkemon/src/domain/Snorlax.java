package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Snorlax, un Pokémon de tipo Normal.
 * Conocido como el Pokémon Dormilón, destaca por su enorme resistencia y capacidad de curación.
 * Su estilo de combate se basa en su gran resistencia y poderosos ataques físicos.
 */
public class Snorlax extends AbstractPokemon {

    /**
     * Indica si Snorlax está en estado de sueño profundo.
     * Cuando es true:
     * - No puede realizar movimientos normales
     * - Se cura completamente
     * - Puede usar movimientos especiales como Snore
     */
    private boolean deeplyAsleep;

    /**
     * Constructor de Snorlax. Inicializa sus estadísticas base, tipo y movimientos.
     * Características principales:
     * - HP extremadamente alto (524)
     * - Ataque físico poderoso (350)
     * - Defensa especial alta (350)
     * - Tipo Normal puro
     * - Velocidad muy baja (174)
     */
    public Snorlax() {
        this.name = "Snorlax";
        this.primaryType = Type.NORMAL;
        this.secondaryType = null;
        this.maxHP = 524;
        this.currentHP = maxHP;
        this.attack = 350;
        this.defense = 251;
        this.specialAttack = 251;
        this.specialDefense = 350;
        this.speed = 174;
        this.deeplyAsleep = false;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Snorlax puede aprender por defecto.
     * Incluye movimientos físicos y técnicas de combate variadas.
     */
    @Override
    protected void initializeMoves() {
        learnMove("Cross Poison");
        learnMove("Air Slash");
        learnMove("Bite");
        learnMove("Mean Look");
        learnMove("Screech");
        learnMove("Absorb");
    }

    /**
     * Habilidad especial: Rest.
     * Snorlax entra en sueño profundo, curándose completamente.
     * Efectos:
     * - Cura todo el HP
     * - Lo deja dormido durante 2 turnos
     * - Permite usar movimientos como Snore mientras duerme
     */
    public void restDeeply() {
        this.deeplyAsleep = true;
        this.currentHP = this.maxHP;
        System.out.println("Snorlax entra en un sueño profundo y se cura completamente.");
    }

    /**
     * Despierta a Snorlax de su sueño profundo.
     */
    public void wakeUp() {
        this.deeplyAsleep = false;
        System.out.println("Snorlax se ha despertado.");
    }

    /**
     * Verifica si Snorlax está en sueño profundo.
     * @return true si está dormido profundamente, false en caso contrario
     */
    public boolean isDeeplyAsleep() {
        return deeplyAsleep;
    }

    /**
     * Ataque especial: Snore.
     * Solo funciona cuando Snorlax está dormido.
     * Potencia: 50 | Probabilidad de hacer retroceder: 30%
     */
    public void snoreLoudly() {
        if (deeplyAsleep) {
            System.out.println("¡Snorlax ronca tan fuerte que asusta a sus enemigos!");
        } else {
            System.out.println("Snorlax no está dormido profundamente y no puede usar Ronquido.");
        }
    }
}
