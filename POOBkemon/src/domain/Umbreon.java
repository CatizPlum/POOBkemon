package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Umbreon, una implementación concreta de AbstractPokemon.
 * Umbreon es un Pokémon de tipo Siniestro conocido por su gran defensa y habilidades especiales
 * que se activan cuando su salud está baja (en "oscuridad").
 */
public class Umbreon extends AbstractPokemon implements Serializable {

    private boolean isInDarkness;

    /**
     * Constructor de Umbreon.
     * Inicializa sus estadísticas base y movimientos aprendidos.
     */
    public Umbreon() {
        this.name = "Umbreon";
        this.primaryType = Type.DARK;
        this.secondaryType = null;
        this.maxHP = 394;
        this.currentHP = maxHP;
        this.attack = 251;
        this.defense = 350;
        this.specialAttack = 240;
        this.specialDefense = 394;
        this.speed = 251;
        this.moves = new ArrayList<>();
        this.isInDarkness = false;

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Umbreon puede aprender.
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
     * Reduce los puntos de salud del Pokémon.
     * Si la salud baja del 50%, activa el estado de oscuridad.
     * @param amount Cantidad de daño a recibir
     */
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (currentHP < maxHP * 0.5) {
            isInDarkness = true;
            System.out.println("¡Umbreon se siente fortalecido por la oscuridad!");
        }
    }

    /**
     * Restaura puntos de salud al Pokémon.
     * Si la salud supera el 70%, desactiva el estado de oscuridad.
     * @param amount Cantidad de salud a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            isInDarkness = false;
            System.out.println("¡Umbreon ha salido de la oscuridad!");
        }
    }

    /**
     * Crea una copia exacta de este Umbreon.
     */
    @Override
    public Umbreon clone() {
        Umbreon cloned = (Umbreon) super.clone();
        return cloned;
    }
}
