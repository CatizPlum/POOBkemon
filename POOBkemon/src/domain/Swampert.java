package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Swampert, la evolución final de Mudkip.
 * Pokémon de tipo Agua/Tierra conocido por su gran resistencia y fuerza física.
 * Posee una habilidad especial llamada "Armadura Fangosa" que se activa al recibir
 * ataques eléctricos, aumentando su defensa.
 */
public class Swampert extends AbstractPokemon implements Serializable {

    /**
     * Indica si la Armadura Fangosa está activa.
     */
    private boolean muddyArmor;

    /**
     * Constructor de Swampert. Inicializa sus estadísticas base, tipos y movimientos.
     */
    public Swampert() {
        this.name = "Swampert";
        this.primaryType = Type.WATER;
        this.secondaryType = Type.GROUND;
        this.maxHP = 404;
        this.currentHP = maxHP;
        this.attack = 350;
        this.defense = 306;
        this.specialAttack = 295;
        this.specialDefense = 306;
        this.speed = 240;
        this.muddyArmor = false;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Swampert puede aprender por defecto.
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
     * Crea y devuelve una copia exacta de este objeto Swampert.
     */
    @Override
    public Swampert clone() {
        Swampert cloned = (Swampert) super.clone();
        cloned.muddyArmor = this.muddyArmor;
        return cloned;
    }
}