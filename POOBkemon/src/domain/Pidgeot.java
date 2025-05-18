package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Pidgeot, un Pokémon de tipo Normal/Volador.
 * Conocido como el Pokémon Pájaro, es la evolución final de Pidgey.
 * Destaca por su equilibrio entre ataque, defensa y velocidad.
 */
public class Pidgeot extends AbstractPokemon implements Serializable {

    /**
     * Constructor de Pidgeot. Inicializa sus estadísticas base, tipos y movimientos.
     * Características principales:
     * - HP alto (370) para un Pokémon volador común
     * - Velocidad considerable (331)
     * - Ataque físico decente (284)
     * - Defensas equilibradas (~260)
     * - Tipo dual Normal/Volador
     */
    public Pidgeot() {
        this.name = "Pidgeot";
        this.primaryType = Type.NORMAL;
        this.secondaryType = Type.FLYING;
        this.maxHP = 370;
        this.currentHP = maxHP;
        this.attack = 284;
        this.defense = 273;
        this.specialAttack = 262;
        this.specialDefense = 262;
        this.speed = 331;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Pidgeot puede aprender por defecto.
     * Incluye movimientos aéreos y técnicas de combate versátiles.
     */
    @Override
    protected void initializeMoves() {
        learnMove("Cross Poison");
        learnMove("Air Slash");
        learnMove("Bite");
        learnMove("Mean Look");
        learnMove("Screech");
        learnMove("Absorb");
    }

    /**
     * Método para recibir daño sin modificadores especiales.
     * @param amount Cantidad de daño recibido
     */
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
    }

    /**
     * Método para curar HP sin habilidades especiales.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
    }

    // === Movimientos Especiales ===

    /**
     * Ataque característico: Hurricane.
     * Movimiento de tipo Volador con alta potencia y posibilidad de confusión.
     * Potencia: 110 | Precisión: 70% | Probabilidad de confusión: 30%
     */
    public void hurricane() {
        System.out.println("¡Pidgeot crea un poderoso Hurricane! Puede confundir al oponente.");
    }

    /**
     * Técnica de recuperación: Roost.
     * Cura 50% del HP máximo y pierde temporalmente el tipo Volador.
     */
    public void roost() {
        System.out.println("¡Pidgeot se posa para descansar y recuperar HP!");
        this.heal(maxHP / 2);
    }

    /**
     * Movimiento de estado: Feather Dance.
     * Reduce el ataque del oponente en 2 niveles.
     */
    public void featherDance() {
        System.out.println("¡Pidgeot realiza un Feather Dance, reduciendo el ataque del oponente!");
    }

    /**
     * Ataque poderoso: Sky Attack.
     * Movimiento de dos turnos con alta potencia y posibilidad de hacer retroceder.
     * Turno 1: Preparación | Turno 2: Ataque (140 potencia, 30% chance de retroceso)
     */
    public void skyAttack() {
        System.out.println("¡Pidgeot se prepara para un devastador Sky Attack en el próximo turno!");
    }
}
