package domain;

import java.util.ArrayList;
import java.util.List;

public class Venusaur extends AbstractPokemon {

    private boolean flowerBlossomed;  // Estado de la flor

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
        this.flowerBlossomed = true;

        // Inicializar movimientos de ejemplo
        this.moves = new ArrayList<>();
        initializeMoves();
    }

    @Override
    protected void initializeMoves() {
        learnMove("Cross Poison");
        learnMove("Air Slash");
        learnMove("Bite");
        learnMove("Mean Look");
        learnMove("Screech");
        learnMove("Absorb");
    }

    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);

        // Si los PS bajan demasiado, la flor pierde su esplendor
        if (currentHP < maxHP * 0.3) {
            flowerBlossomed = false;
            System.out.println("¡La flor de Venusaur pierde su esplendor!");
        }
    }

    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);

        // Si se recupera lo suficiente, la flor florece de nuevo
        if (currentHP > maxHP * 0.6) {
            flowerBlossomed = true;
            System.out.println("¡La flor de Venusaur recupera su esplendor!");
        }
    }

    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }

    // Métodos específicos de Venusaur
    public boolean isFlowerBlossomed() {
        return flowerBlossomed;
    }

    public void photosynthesis() {
        if (flowerBlossomed) {
            // Recuperación de PS mediante fotosíntesis
            int healAmount = maxHP / 4;
            heal(healAmount);
            System.out.println("¡Venusaur realiza fotosíntesis y recupera " + healAmount + " PS!");
        } else {
            System.out.println("La flor de Venusaur no está en condiciones para realizar fotosíntesis efectiva.");
        }
    }

    public void powderRelease() {
        // Libera esporas que pueden afectar al oponente
        System.out.println("¡Venusaur libera una nube de esporas desde su flor!");

        // Aumenta temporalmente la defensa y defensa especial
        this.defense = (int)(this.defense * 1.2);
        this.specialDefense = (int)(this.specialDefense * 1.2);
    }
} 