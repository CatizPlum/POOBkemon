package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Machamp, la evolución final de Machop.
 * Pokémon de tipo Lucha puro conocido por su enorme fuerza física y sus cuatro brazos.
 * Destaca por su alto ataque físico y habilidades para potenciar movimientos de contacto.
 */
public class Machamp extends AbstractPokemon implements Serializable {

    /**
     * Constructor de Machamp. Inicializa sus estadísticas base y movimientos.
     */
    public Machamp() {
        this.name = "Machamp";
        this.primaryType = Type.FIGHTING;
        this.secondaryType = null;
        this.maxHP = 384;
        this.currentHP = maxHP;
        this.attack = 394;
        this.defense = 284;
        this.specialAttack = 218;
        this.specialDefense = 273;
        this.speed = 207;
        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Machamp puede aprender por defecto.
     * Incluye movimientos variados que aprovechan su fuerza física.
     */
    @Override
    public void initializeMoves() {
        learnMove("Leer");         // Reduce defensa del oponente
        learnMove("Quick Attack"); // Ataque prioritario
        learnMove("Double Team");  // Aumenta evasión
        learnMove("Knock Off");    // Remueve item del oponente
        learnMove("Future Sight"); // Ataque psíquico diferido
        learnMove("Water Pulse");  // Ataque especial de agua
    }

    /**
     * Crea una copia exacta de este Machamp.
     */
    @Override
    public Machamp clone() {
        Machamp cloned = (Machamp) super.clone();
        return cloned;
    }
}