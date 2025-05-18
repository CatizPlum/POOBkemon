package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Masquerain, un Pokémon de tipo Bicho/Volador.
 * Conocido por sus patrones oculares intimidantes que pueden asustar a los oponentes.
 * Combina características de insecto y volador con habilidades defensivas únicas.
 */
public class Masquerain extends AbstractPokemon {

    /**
     * Indica si Masquerain está usando sus patrones oculares para intimidar.
     * Cuando es true:
     * - Puede causar efectos de intimidación en el oponente
     * - Representa su mecanismo de defensa natural
     *
     * Se activa automáticamente al entrar en combate
     */
    private boolean eyeThreatenedMode;

    /**
     * Constructor de Masquerain. Inicializa sus estadísticas base, tipos y movimientos.
     * Características principales:
     * - Defensa especial alta (289)
     * - Ataque especial decente (284)
     * - Tipo dual Bicho/Volador
     * - Modo de intimidación ocular activado por defecto
     */
    public Masquerain() {
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
     * Incluye movimientos de varios tipos para versatilidad en combate.
     */
    @Override
    protected void initializeMoves() {
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
     * Verifica el estado de intimidación ocular de Masquerain.
     * @return true si está usando sus patrones oculares intimidantes, false en caso contrario
     */
    public boolean isEyeThreatenedMode() {
        return eyeThreatenedMode;
    }
}
