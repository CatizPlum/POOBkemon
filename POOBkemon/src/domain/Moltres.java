package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon legendario Moltres, de tipo Fuego/Volador.
 * Conocido como el Pokémon Llama, es una de las aves legendarias de Kanto.
 * Destaca por su poder ígneo y habilidades de vuelo majestuoso.
 */
public class Moltres extends AbstractPokemon implements Serializable {

    /**
     * Constructor de Moltres. Inicializa sus estadísticas base como Pokémon legendario.
     */
    public Moltres() {
        this.name = "Moltres";
        this.primaryType = Type.FIRE;
        this.secondaryType = Type.FLYING;
        this.maxHP = 380;
        this.currentHP = maxHP;
        this.attack = 290;
        this.defense = 280;
        this.specialAttack = 350;
        this.specialDefense = 280;
        this.speed = 290;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Moltres puede aprender por defecto.
     * Incluye movimientos de varios tipos para versatilidad en combate.
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
     * Método para recibir daño con resistencia a tipo Fuego.
     * @param amount Cantidad de daño recibido
     */
    @Override
    public void takeDamage(int amount) {
        // En implementación completa, reducir daño si es de tipo Fuego
        super.takeDamage(amount);
    }

    /**
     * Método para curar HP.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
    }

    /**
     * Crea una copia exacta de este Moltres.
     */
    @Override
    public Moltres clone() {
        Moltres cloned = (Moltres) super.clone();
        return cloned;
    }
}
