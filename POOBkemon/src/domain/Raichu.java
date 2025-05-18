package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Raichu, la evolución final de Pikachu.
 * Pokémon de tipo Eléctrico puro conocido por su gran velocidad y poder ofensivo.
 * Destaca por su capacidad de paralizar oponentes al contacto.
 */
public class Raichu extends AbstractPokemon implements Serializable {

    /**
     * Constructor de Raichu. Inicializa sus estadísticas base, tipo y movimientos.
     * Características principales:
     * - Velocidad muy alta (350)
     * - Ataque físico y especial equilibrados (306)
     * - Defensa especial considerable (284)
     * - Tipo Eléctrico puro
     * - HP moderado (324)
     */
    public Raichu() {
        this.name = "RAICHU";
        this.primaryType = Type.ELECTRIC;
        this.secondaryType = null;
        this.maxHP = 324;
        this.currentHP = maxHP;
        this.attack = 306;
        this.defense = 229;
        this.specialAttack = 306;
        this.specialDefense = 284;
        this.speed = 350;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Raichu puede aprender por defecto.
     * Incluye movimientos eléctricos y técnicas de combate versátiles.
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
     * Intenta aplicar el efecto estático al oponente.
     * Representa la habilidad Static que tiene un 30% de probabilidad de paralizar al contacto.
     * @return true si se aplicó la parálisis, false en caso contrario
     */
    public boolean tryStaticEffect() {
        double chance = Math.random();
        if (chance < 0.3) {
            System.out.println("¡El atacante fue paralizado por electricidad estática!");
            return true;
        }
        return false;
    }
}