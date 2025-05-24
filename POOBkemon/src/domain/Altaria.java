package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Altaria, un Pokémon de tipo Dragón/Volador.
 * Tiene la habilidad de enfurecerse al recibir daño significativo, potenciando su ataque especial,
 * y puede usar melodías calmantes o descansar en sus alas esponjosas.
 */
public class Altaria extends AbstractPokemon implements Serializable {

    /**
     * Indica si Altaria está en estado de furia, lo que aumenta su ataque especial en 50 puntos.
     * Este estado se activa cuando recibe un daño superior a 80 puntos.
     */
    private boolean enraged = false;

    /**
     * Constructor de Altaria. Inicializa sus estadísticas base, tipos y movimientos aprendidos.
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
    public void initializeMoves() {
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
     * @param amount Cantidad de daño recibido. Debe ser un valor positivo.
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
     * Crea y devuelve una copia exacta de este objeto Altaria.
     * @return Un clon de esta instancia de Altaria, incluyendo su estado actual.
     */
    @Override
    public Altaria clone() {
        Altaria cloned = (Altaria) super.clone();
        return cloned;
    }
}