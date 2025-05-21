package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa al Pokémon Tyranitar, una implementación concreta de AbstractPokemon.
 * Posee características especiales como su habilidad Sand Stream y movimientos únicos.
 */
public class Tyranitar extends AbstractPokemon  implements Serializable {

    private boolean sandstreamActive;  // Indica si la habilidad Sand Stream está activa

    /**
     * Constructor de Tyranitar.
     * Inicializa el estado de Sand Stream como falso y configura sus movimientos.
     */
    public Tyranitar() {
        this.sandstreamActive = false;
        this.moves = new ArrayList<>();

        // Establecer atributos básicos
        this.name = "tyranitar"; // <- ¡IMPORTANTE! Debe coincidir con la clave en pokemonGifs
        this.primaryType = Type.ROCK;
        this.secondaryType = Type.DARK;

        // Estadísticas base (valores de ejemplo, ajústalos según necesites)
        this.maxHP = 300;
        this.currentHP = this.maxHP;
        this.attack = 120;
        this.defense = 110;
        this.specialAttack = 95;
        this.specialDefense = 100;
        this.speed = 80;

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Tyranitar puede aprender.
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
     * Reduce los puntos de salud del Pokémon aplicando una reducción del 10% al daño recibido.
     * Puede activar Sand Stream si el daño recibido es mayor al 15% de su salud máxima.
     * @param amount Cantidad de daño a recibir
     */
    @Override
    public void takeDamage(int amount) {
        int reducedAmount = (int)(amount * 0.9);
        currentHP = Math.max(0, currentHP - reducedAmount);

        if (!sandstreamActive && amount > maxHP * 0.15) {
            sandstreamActive = true;
            this.defense = (int)(this.defense * 1.2);
            System.out.println("¡El ataque enfurece a Tyranitar y desata una tormenta de arena!");
        }
    }

    /**
     * Restaura puntos de salud al Pokémon.
     * @param amount Cantidad de salud a recuperar
     */
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);
    }

    /**
     * Verifica si el Pokémon está debilitado.
     * @return true si el Pokémon no tiene salud restante, false en caso contrario
     */
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }

    /**
     * Verifica el estado de la habilidad Sand Stream.
     * @return true si Sand Stream está activo, false en caso contrario
     */
    public boolean isSandstreamActive() {
        return sandstreamActive;
    }

    /**
     * Activa la habilidad Sand Stream, aumentando la defensa en un 20%.
     * Solo tiene efecto si la habilidad no estaba previamente activa.
     */
    public void activateSandstream() {
        if (!sandstreamActive) {
            sandstreamActive = true;
            this.defense = (int)(this.defense * 1.2);
            System.out.println("¡Tyranitar desata una violenta tormenta de arena! Su defensa aumenta.");
        } else {
            System.out.println("La tormenta de arena ya está activa.");
        }
    }

    /**
     * Ataque especial de Tyranitar que puede modificar el terreno de batalla.
     * Aumenta su ataque en un 30% y tiene efectos adicionales si Sand Stream está activo.
     */
    public void mountainCrusher() {
        System.out.println("¡Tyranitar golpea el suelo con toda su fuerza, modificando el terreno!");
        this.attack = (int)(this.attack * 1.3);

        if (sandstreamActive) {
            System.out.println("¡La combinación con la tormenta de arena hace que el impacto sea devastador!");
        }
    }

    @Override
    public Tyranitar clone() {
        Tyranitar cloned = (Tyranitar) super.clone();
        return cloned;
    }
}
