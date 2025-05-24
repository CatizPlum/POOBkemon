package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Mawile, un Pokémon de tipo Acero/Hada.
 * Conocido por su apariencia engañosa y su poderosa mandíbula secundaria.
 * Combina defensas sólidas con la capacidad de engañar a sus oponentes.
 */
public class Mawile extends AbstractPokemon implements Serializable {

    /**
     * Indica si Mawile está usando su postura engañosa.
     */
    private boolean deceivingStance;

    /**
     * Constructor de Mawile. Inicializa sus estadísticas base, tipos y movimientos.
     */
    public Mawile() {
        this.name = "Mawile";
        this.primaryType = Type.STEEL;
        this.secondaryType = Type.FAIRY;
        this.maxHP = 304;
        this.currentHP = maxHP;
        this.attack = 295;
        this.defense = 295;
        this.specialAttack = 229;
        this.specialDefense = 229;
        this.speed = 218;
        this.deceivingStance = true;
        this.moves = new ArrayList<>();

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Mawile puede aprender por defecto.
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
     * Crea una copia exacta de este Mawile.
     */
    @Override
    public Mawile clone() {
        Mawile cloned = (Mawile) super.clone();
        return cloned;
    }
}