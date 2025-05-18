package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Mawile, un Pokémon de tipo Acero/Hada.
 * Conocido por su apariencia engañosa y su poderosa mandíbula secundaria.
 * Combina defensas sólidas con la capacidad de engañar a sus oponentes.
 */
public class Mawile extends AbstractPokemon {

    /**
     * Indica si Mawile está usando su postura engañosa.
     * Cuando es true:
     * - Puede confundir o intimidar al oponente
     * - Representa su estrategia de combate característica
     * - Aumenta su efectividad en ataques sorpresa
     */
    private boolean deceivingStance;

    /**
     * Constructor de Mawile. Inicializa sus estadísticas base, tipos y movimientos.
     * Características principales:
     * - Ataque y defensa equilibrados (295)
     * - Tipo dual Acero/Hada con múltiples resistencias
     * - Postura engañosa activada por defecto
     * - Velocidad baja (218) pero compensada por su durabilidad
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
    protected void initializeMoves() {
        learnMove("Leer");
        learnMove("Quick Attack");
        learnMove("Double Team");
        learnMove("Knock Off");
        learnMove("Future Sight");
        learnMove("Water Pulse");
    }

    /**
     * Verifica el estado de postura engañosa de Mawile.
     * @return true si está usando su apariencia engañosa, false en caso contrario
     */
    public boolean isDeceivingStance() {
        return deceivingStance;
    }
}
