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
    public void initializeMoves() {
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

    /**
     * Crea una copia exacta de este Pidgeot.
     */
    @Override
    public Pidgeot clone() {
        Pidgeot cloned = (Pidgeot) super.clone();
        return cloned;
    }
}
