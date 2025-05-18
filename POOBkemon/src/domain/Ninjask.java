package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Ninjask, un Pokémon de tipo Bicho/Volador.
 * Conocido como el Pokémon Ninja, destaca por ser el Pokémon más rápido en combate
 * y por aumentar su velocidad cada turno mediante su habilidad Speed Boost.
 */
public class Ninjask extends AbstractPokemon {

    /**
     * Contador de turnos transcurridos en combate.
     * Se utiliza para activar el aumento progresivo de velocidad.
     */
    private int turnsPassed = 0;

    /**
     * Constructor de Ninjask. Inicializa sus estadísticas base, tipos y movimientos.
     * Características principales:
     * - Velocidad extremadamente alta (460 - la más alta de todos los Pokémon)
     * - Ataque físico decente (306)
     * - Defensas moderadas (~210)
     * - Tipo dual Bicho/Volador
     */
    public Ninjask() {
        this.name = "Ninjask";
        this.primaryType = Type.BUG;
        this.secondaryType = Type.FLYING;

        this.maxHP = 326;
        this.currentHP = maxHP;
        this.attack = 306;
        this.defense = 207;
        this.specialAttack = 218;
        this.specialDefense = 218;
        this.speed = 460; // Velocidad más alta de todos los Pokémon

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Ninjask puede aprender por defecto.
     * Incluye movimientos rápidos y técnicas de combate ágiles.
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
     * Método que se ejecuta al inicio de cada turno.
     * Implementa la habilidad Speed Boost que aumenta la velocidad cada turno.
     * Efecto:
     * - A partir del segundo turno, aumenta velocidad en +10 puntos por turno
     * - Muestra mensaje cuando aumenta la velocidad
     */
    public void onTurnStart() {
        turnsPassed++;
        if (turnsPassed > 1) {
            this.speed += 10;
            System.out.println("¡La velocidad de Ninjask aumentó!");
        }
    }
}
