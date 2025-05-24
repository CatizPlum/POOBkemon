package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Granbull, un Pokémon de tipo Hada.
 * Aunque su apariencia es intimidante, es en realidad muy cariñoso.
 * Destaca por su enorme fuerza física y potentes mordiscos.
 */
public class Granbull extends AbstractPokemon implements Serializable {

    /**
     * Constructor de Granbull. Inicializa sus estadísticas base, tipo y movimientos.
     */
    public Granbull() {
        this.name = "Granbull";
        this.primaryType = Type.FAIRY;
        this.secondaryType = null;
        this.maxHP = 384;
        this.currentHP = maxHP;
        this.attack = 372;
        this.defense = 273;
        this.specialAttack = 240;
        this.specialDefense = 240;
        this.speed = 207;
        this.moves = new ArrayList<>();

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Granbull puede aprender por defecto.
     * Incluye movimientos físicos y técnicas básicas.
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
     * Crea una copia exacta de este Granbull.
     */
    @Override
    public Granbull clone() {
        Granbull cloned = (Granbull) super.clone();
        return cloned;
    }
}