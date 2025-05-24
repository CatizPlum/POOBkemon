package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Medicham, un Pokémon de tipo Lucha/Psíquico.
 * Conocido por su capacidad de leer auras y combinar artes marciales con poderes mentales.
 * Destaca por su velocidad y precisión en combate.
 */
public class Medicham extends AbstractPokemon implements Serializable {

    /**
     * Constructor de Medicham. Inicializa sus estadísticas base, tipos y movimientos.
     */
    public Medicham() {
        this.name = "Medicham";
        this.primaryType = Type.FIGHTING;
        this.secondaryType = Type.PSYCHIC;
        this.maxHP = 284;
        this.currentHP = maxHP;
        this.attack = 295;
        this.defense = 229;
        this.specialAttack = 229;
        this.specialDefense = 229;
        this.speed = 284;
        this.moves = new ArrayList<>();

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Medicham puede aprender por defecto.
     * Incluye movimientos físicos y técnicas de combate básicas.
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
     * Crea una copia exacta de este Medicham.
     */
    @Override
    public Medicham clone() {
        Medicham cloned = (Medicham) super.clone();
        return cloned;
    }
}