package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa al Pokémon Metagross, un Pokémon de tipo Acero/Psíquico.
 * Conocido como el Pokémon Computacional, combina una coraza de acero con cuatro cerebros
 * que le permiten realizar cálculos avanzados durante el combate.
 */
public class Metagross extends AbstractPokemon implements Serializable {

    /**
     * Indica si Metagross está en modo de cálculo intensivo.
     */
    private boolean calculationMode;

    /**
     * Constructor de Metagross. Inicializa sus estadísticas base, tipos y movimientos.
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
    public void initializeMoves() {
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

    /**
     * Crea una copia exacta de este Metagross.
     */
    @Override
    public Metagross clone() {
        Metagross cloned = (Metagross) super.clone();
        return cloned;
    }
}
