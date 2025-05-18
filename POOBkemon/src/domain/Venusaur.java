package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa al Pokémon Venusaur, una implementación concreta de AbstractPokemon.
 * Venusaur es un Pokémon de tipo Planta/Veneno conocido por su gran flor y habilidades
 * relacionadas con la naturaleza y fotosíntesis.
 */
public class Venusaur extends AbstractPokemon {

    private boolean flowerBlossomed;  // Estado de floración de su flor característica

    /**
     * Constructor de Venusaur.
     * Inicializa sus estadísticas base, movimientos y estado de floración.
     */
    public Venusaur() {
        this.name = "Venusaur";
        this.primaryType = Type.PLANT;
        this.secondaryType = Type.POISON;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 289;
        this.defense = 291;
        this.specialAttack = 328;
        this.specialDefense = 328;
        this.speed = 284;
        this.flowerBlossomed = true;  // La flor comienza en estado florecido

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Venusaur puede aprender.
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
     * Reduce los puntos de salud del Pokémon.
     * Si la salud baja del 30%, la flor pierde su esplendor.
     * @param amount Cantidad de daño a recibir
     */
    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);

        if (currentHP < maxHP * 0.3) {
            flowerBlossomed = false;
            System.out.println("¡La flor de Venusaur pierde su esplendor!");
        }
    }

    /**
     * Restaura puntos de salud al Pokémon.
     * Si la salud supera el 60%, la flor recupera su esplendor.
     * @param amount Cantidad de salud a recuperar
     */
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);

        if (currentHP > maxHP * 0.6) {
            flowerBlossomed = true;
            System.out.println("¡La flor de Venusaur recupera su esplendor!");
        }
    }

    /**
     * Verifica si el Pokémon está debilitado.
     * @return true si el Pokémon no tiene salud restante, false en caso contrario
     */
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }

    // Habilidades especiales de Venusaur

    /**
     * Verifica el estado de floración de Venusaur.
     * @return true si la flor está en esplendor, false en caso contrario
     */
    public boolean isFlowerBlossomed() {
        return flowerBlossomed;
    }

    /**
     * Habilidad de fotosíntesis que recupera salud cuando la flor está florecida.
     * Recupera un 25% de su salud máxima.
     */
    public void photosynthesis() {
        if (flowerBlossomed) {
            int healAmount = maxHP / 4;
            heal(healAmount);
            System.out.println("¡Venusaur realiza fotosíntesis y recupera " + healAmount + " PS!");
        } else {
            System.out.println("La flor de Venusaur no está en condiciones para realizar fotosíntesis efectiva.");
        }
    }

    /**
     * Libera esporas desde su flor, aumentando sus defensas.
     * Aumenta la Defensa y Defensa Especial en un 20%.
     */
    public void powderRelease() {
        System.out.println("¡Venusaur libera una nube de esporas desde su flor!");
        this.defense = (int)(this.defense * 1.2);
        this.specialDefense = (int)(this.specialDefense * 1.2);
    }
}