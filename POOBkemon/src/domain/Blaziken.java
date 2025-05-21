package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Blaziken, la evolución final de Torchic.
 * Es un Pokémon de tipo Fuego/Lucha conocido por sus poderosos ataques físicos
 * y su capacidad para generar llamas intensas durante el combate.
 * Sus habilidades giran alrededor del manejo del fuego y el combate cuerpo a cuerpo.
 */
public class Blaziken extends AbstractPokemon implements Serializable {

    /**
     * Estado de las llamas intensas de Blaziken.
     * true = llamas activadas (aumento de ataque)
     * false = llamas inactivas (estado normal)
     */
    private boolean intenseFlamesActive = false;

    /**
     * Contador de turnos restantes para la habilidad de llamas intensas.
     * La habilidad dura 3 turnos cuando se activa.
     */
    private int flamesTurns = 0;

    /**
     * Constructor de Blaziken. Inicializa sus estadísticas base, tipo y movimientos.
     * Estadísticas destacadas:
     * - Alto ataque físico (372) y ataque especial (350)
     * - Tipo dual Fuego/Fighting
     * - Llamas inicialmente inactivas
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
     * Incluye movimientos de tipo Fuego, Lucha y técnicas variadas.
     */
    @Override
    public void initializeMoves() {
        learnMove("Cross Poison");
        learnMove("Air Slash");
        learnMove("Bite");
        learnMove("Mean Look");
        learnMove("Screech");
        learnMove("Absorb");
    }

    /**
     * Habilidad especial: Llamas Intensas.
     * Blaziken expulsa llamas desde sus muñecas, aumentando su ataque físico en un 30%.
     * El efecto dura 3 turnos y solo puede activarse una vez hasta que termine.
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
     * Método para manejar el final de cada turno de combate.
     * Reduce la duración de las habilidades activas y las desactiva cuando terminan.
     */
    public void endTurn() {
        if (intenseFlamesActive) {
            flamesTurns--;
            if (flamesTurns <= 0) {
                intenseFlamesActive = false;
                this.attack /= 1.3; // El ataque vuelve a su valor normal
                System.out.println("El poder de Blaziken disminuye al cesar las llamas intensas.");
            }
        }
    }

    /**
     * Habilidad secundaria: Ignite Spirit.
     * Aumenta temporalmente el ataque y velocidad en un 5%.
     * Representa el espíritu combativo de Blaziken.
     */
    public void igniteSpirit() {
        this.attack *= 1.05;
        this.speed *= 1.05;
        System.out.println("Blaziken's fighting spirit flares up!");
    }

    @Override
    public Blaziken clone() {
        Blaziken cloned = (Blaziken) super.clone();
        return cloned;
    }
}