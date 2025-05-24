package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Blastoise - Pokémon tipo Agua con poderosos cañones de agua.
 * Su fuerza depende de la presión del agua en sus cañones.
 */
public class Blastoise extends AbstractPokemon implements Serializable {

    // Controla si los cañones tienen presión suficiente (true = presión normal)
    private boolean cannonsPowered;

    public Blastoise() {
        this.name = "Blastoise";
        this.primaryType = Type.WATER;
        this.maxHP = 362;
        this.currentHP = maxHP;
        this.attack = 291;
        this.defense = 328;
        this.specialAttack = 295;
        this.specialDefense = 339;
        this.speed = 280;
        this.cannonsPowered = true;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    // Añade movimientos básicos de Blastoise
    @Override
    public void initializeMoves() {
        learnMove("Tackle");
        learnMove("Water Gun");
        learnMove("Withdraw");
        learnMove("Water Pulse");
        learnMove("Bite");
    }

    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);
        // Pierde presión si tiene poca vida
        if (currentHP < maxHP * 0.2) {
            cannonsPowered = false;
        }
    }

    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);
        // Recupera presión si tiene suficiente vida
        if (currentHP > maxHP * 0.5) {
            cannonsPowered = true;
        }
    }

    /**
     * Crea y devuelve una copia exacta de este objeto Blastoise.
     */
    @Override
    public Blastoise clone() {
        return (Blastoise) super.clone();
    }
}