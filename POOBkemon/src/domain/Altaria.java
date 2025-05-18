package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Altaria, un Pokémon de tipo Dragón/Volador.
 * Tiene la habilidad de enfurecerse al recibir daño significativo, potenciando su ataque especial,
 * y puede usar melodías calmantes o descansar en sus alas esponjosas.
 */
public class Altaria extends AbstractPokemon {
    /**
     * Indica si Altaria está en estado de furia, lo que aumenta su ataque especial.
     */
    private boolean enraged = false;

    /**
     * Constructor de Altaria. Inicializa sus estadísticas base, tipos y movimientos.
     */
    public Altaria() {
        this.name = "Altaria";
        this.primaryType = Type.DRAGON;
        this.secondaryType = Type.FLYING;
        this.maxHP = 354;
        this.currentHP = maxHP;
        this.attack = 262;
        this.defense = 306;
        this.specialAttack = 262;
        this.specialDefense = 339;
        this.speed = 284;

        this.moves = new ArrayList<>();

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Altaria puede aprender por defecto.
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
     * Método para recibir daño. Si el daño recibido es mayor a 80,
     * Altaria se enfurece, aumentando su ataque especial en 50 puntos.
     * @param amount Cantidad de daño recibido.
     */
    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);

        if (!enraged && amount > 80) {
            enraged = true;
            specialAttack += 50;
            System.out.println("¡Altaria se enfurece! Su chillido estridente intimida y aumenta su ataque especial.");
        }
    }

    /**
     * Altaria canta una melodía calmante con un 25% de probabilidad de hacer perder el turno al oponente.
     */
    public void singSoothingMelody() {
        double chance = Math.random();
        if (chance < 0.25) {
            System.out.println("Altaria sings a crystal-clear melody. The opponent is mesmerized and loses their turn.");
        } else {
            System.out.println("Altaria hums a soothing tune, but the opponent resists its charm.");
        }
    }

    /**
     * Verifica si Altaria está en estado de furia.
     * @return true si está enfurecida, false en caso contrario.
     */
    public boolean isEnraged() {
        return enraged;
    }

    /**
     * Altaria descansa en sus alas esponjosas, recuperando 40 puntos de salud.
     */
    public void restInCloud() {
        System.out.println("Altaria snuggles into its fluffy wings and regains some energy.");
        this.heal(40); // Restores health
    }
}