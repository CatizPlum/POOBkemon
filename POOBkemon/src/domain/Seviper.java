package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Seviper, un Pokémon de tipo Veneno/Bicho.
 * Conocido como el Pokémon Serpiente, destaca por su potente veneno y ataques físicos.
 * Rival natural de Zangoose, con quien mantiene una eterna enemistad.
 */
public class Seviper extends AbstractPokemon implements Serializable {

    /**
     * Constructor de Seviper. Inicializa sus estadísticas base, tipos y movimientos.
     */
    public Seviper() {
        this.name = "Seviper";
        this.primaryType = Type.POISON;
        this.secondaryType = Type.BUG;
        this.maxHP = 350;
        this.currentHP = maxHP;
        this.attack = 328;
        this.defense = 240;
        this.specialAttack = 328;
        this.specialDefense = 240;
        this.speed = 251;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Seviper puede aprender por defecto.
     * Incluye movimientos venenosos y técnicas de combate engañosas.
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
     * Crea una copia exacta de este Seviper.
     */
    @Override
    public Seviper clone() {
        Seviper cloned = (Seviper) super.clone();
        return cloned;
    }
}
