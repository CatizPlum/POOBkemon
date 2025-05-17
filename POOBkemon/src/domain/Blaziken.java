package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Blaziken, un Pokémon de tipo Fuego/Lucha.
 * Destaca por su gran ataque físico y habilidades que potencian su poder de combate.
 * Posee una habilidad especial llamada "Llamas Intensas" que aumenta temporalmente su ataque.
 */
public class Blaziken extends AbstractPokemon {

    /**
     * Indica si la habilidad "Llamas Intensas" está actualmente activa.
     * Cuando está activa, aumenta el ataque en un 30%.
     */
    private boolean intenseFlamesActive = false;

    /**
     * Contador que lleva la cuenta de los turnos restantes
     * para la habilidad "Llamas Intensas" (dura 3 turnos).
     */
    private int flamesTurns = 0;

    /**
     * Constructor de Blaziken. Inicializa sus estadísticas base, tipos y movimientos.
     * Estadísticas destacadas:
     * - Alto ataque físico (372)
     * - Buen ataque especial (350)
     * - Velocidad decente (284)
     */
    public Blaziken() {
        this.name = "Blaziken";
        this.primaryType = Type.FIRE;
        this.secondaryType = Type.FIGHTING;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 372;
        this.defense = 262;
        this.specialAttack = 350;
        this.specialDefense = 262;
        this.speed = 284;
        this.moves = new ArrayList<>();

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Blaziken puede aprender por defecto.
     * Incluye movimientos de varios tipos para versatilidad en combate.
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
     * Habilidad especial: Activa las Llamas Intensas que aumentan el ataque en un 30%.
     * Duración: 3 turnos.
     * Solo puede activarse una vez hasta que termine su efecto.
     */
    public void intenseFlames() {
        System.out.println("Blaziken expulsa llamas intensas desde sus muñecas.");

        if (!intenseFlamesActive) {
            intenseFlamesActive = true;
            this.attack *= 1.3; // Aumenta el ataque en un 30%
            System.out.println("¡El poder de Blaziken aumenta por las llamas intensas!");
        }
        flamesTurns = 3;
    }

    /**
     * Método que debe llamarse al final de cada turno para actualizar estados temporales.
     * Gestiona la duración de la habilidad "Llamas Intensas".
     */
    public void endTurn() {
        if (intenseFlamesActive) {
            flamesTurns--;
            if (flamesTurns <= 0) {
                intenseFlamesActive = false;
                this.attack /= 1.3; // Revierte el aumento de ataque
                System.out.println("El poder de Blaziken disminuye al cesar las llamas intensas.");
            }
        }
    }

    /**
     * Habilidad secundaria: Ignite Spirit.
     * Aumenta permanentemente el ataque y velocidad en un 5%.
     * Representa el espíritu combativo de Blaziken.
     */
    public void igniteSpirit() {
        this.attack *= 1.05;
        this.speed *= 1.05;
        System.out.println("Blaziken's fighting spirit flares up!");
    }
}