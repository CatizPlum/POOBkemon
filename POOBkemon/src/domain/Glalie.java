package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Glalie, la evolución de Snorunt.
 * Es un Pokémon de tipo Hielo (Ice) conocido por su cabeza de hielo macizo y
 * su capacidad para congelar instantáneamente el aire a su alrededor.
 * Sus habilidades giran alrededor del control del hielo y ataques sorpresivos.
 */
public class Glalie extends AbstractPokemon implements Serializable {

    /**
     * Constructor de Glalie. Inicializa sus estadísticas base, tipo y movimientos.
     */
    public Glalie() {
        this.name = "Glalie";
        this.primaryType = Type.ICE;
        this.secondaryType = null;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 284;
        this.defense = 284;
        this.specialAttack = 284;
        this.specialDefense = 284;
        this.speed = 284;
        this.moves = new ArrayList<>();

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Glalie puede aprender por defecto.
     * Incluye movimientos de tipo Hielo y técnicas variadas que reflejan su naturaleza versátil.
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
     * Crea una copia exacta de este Glalie.
     */
    @Override
    public Glalie clone() {
        Glalie cloned = (Glalie) super.clone();
        return cloned;
    }
}