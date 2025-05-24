package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Snorlax, un Pokémon de tipo Normal.
 * Conocido como el Pokémon Dormilón, destaca por su enorme resistencia y capacidad de curación.
 * Su estilo de combate se basa en su gran resistencia y poderosos ataques físicos.
 */
public class Snorlax extends AbstractPokemon implements Serializable {

    /**
     * Indica si Snorlax está en estado de sueño profundo.
     */
    private boolean deeplyAsleep;

    /**
     * Constructor de Snorlax. Inicializa sus estadísticas base, tipo y movimientos.
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
    public void initializeMoves() {
        learnMove("Cross Poison");
        learnMove("Air Slash");
        learnMove("Bite");
        learnMove("Mean Look");
        learnMove("Screech");
        learnMove("Absorb");
    }

    /**
     * Crea una copia exacta de este Snorlax.
     */
    @Override
    public Snorlax clone() {
        Snorlax cloned = (Snorlax) super.clone();
        return cloned;
    }
}
