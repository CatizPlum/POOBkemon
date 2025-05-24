package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Masquerain, la evolución de Surskit.
 * Pokémon de tipo Bicho/Volador conocido por sus ojos intimidantes y movimientos acuáticos.
 * Tiene un modo especial de intimidación con sus patrones oculares.
 */
public class Masquerain extends AbstractPokemon implements Serializable {

    private boolean eyeThreatenedMode; // Modo de intimidación ocular

    /**
     * Constructor de Masquerain. Inicializa sus estadísticas base y movimientos.
     */
    public Masquerain(){
        this.name = "Masquerain";
        this.primaryType = Type.BUG;
        this.secondaryType = Type.FLYING;
        this.maxHP = 344;
        this.currentHP = maxHP;
        this.attack = 240;
        this.defense = 245;
        this.specialAttack = 284;
        this.specialDefense = 289;
        this.speed = 240;
        this.eyeThreatenedMode = true;
        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Masquerain puede aprender por defecto.
     * Incluye movimientos de varios tipos que aprovechan su versatilidad.
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
     * Verifica si Masquerain ha sido debilitado.
     * @return true si HP <= 0, false en caso contrario
     */
    @Override
    public boolean isFainted() {
        return currentHP <= 0;
    }

    /**
     * Crea una copia exacta de este Masquerain.
     */
    @Override
    public Masquerain clone() {
        Masquerain cloned = (Masquerain) super.clone();
        return cloned;
    }
}