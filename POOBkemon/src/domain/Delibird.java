package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que representa al Pokémon Delibird, un Pokémon de tipo Hielo/Volador.
 * Conocido por su habilidad única de entregar regalos con efectos aleatorios.
 * Es un Pokémon con estadísticas modestas pero con una habilidad especial impredecible.
 */
public class Delibird extends AbstractPokemon implements Serializable {

    /**
     * Constructor de Delibird. Inicializa sus estadísticas base, tipos y movimientos.
     */
    public Delibird() {
        this.name = "Delibird";
        this.primaryType = Type.ICE;
        this.secondaryType = Type.FLYING;
        this.maxHP = 294;
        this.currentHP = maxHP;
        this.attack = 229;
        this.defense = 207;
        this.specialAttack = 251;
        this.specialDefense = 207;
        this.speed = 273;
        this.moves = new ArrayList<>();

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Delibird puede aprender por defecto.
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
     * Habilidad especial: Entrega un regalo con efectos aleatorios al oponente.
     */
    public void surpriseGift(Pokemon opponent) {
        if (opponent == null) {
            throw new IllegalArgumentException("El oponente no puede ser null");
        }

        Random rand = new Random();
        int effect = rand.nextInt(3);  // Genera un valor aleatorio entre 0 y 2

        switch (effect) {
            case 0:
                System.out.println("Delibird entrega un regalo explosivo al oponente.");
                opponent.takeDamage(50);  // Inflige daño fijo
                break;

            case 1:
                System.out.println("Delibird entrega un regalo curativo al oponente.");
                opponent.heal(30);  // Curación fija
                break;

            case 2:
                System.out.println("Delibird entrega un regalo que aumenta las estadísticas del oponente.");
                if (opponent instanceof AbstractPokemon) {
                    ((AbstractPokemon) opponent).incrementAttack(10);  // Mejora de ataque
                }
                break;
        }
    }

    /**
     * Crea y devuelve una copia exacta de este objeto Delibird.
     */
    @Override
    public Delibird clone() {
        Delibird cloned = (Delibird) super.clone();
        return cloned;
    }
}