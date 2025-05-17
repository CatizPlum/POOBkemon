package domain;

import java.util.ArrayList;


public class Dragonite extends AbstractPokemon  {
    private boolean flyingMode;  // Estado de vuelo

    public Dragonite() {
        this.name = "Dragonite";
        this.primaryType = Type.DRAGON;
        this.secondaryType = Type.FLYING;
        this.maxHP = 386;
        this.currentHP = 386;
        this.attack = 403;
        this.defense = 317;
        this.specialAttack = 328;
        this.specialDefense = 328;
        this.speed = 284;
        this.flyingMode = false;

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
        // Si está en modo vuelo, esquiva algunos ataques
        if (flyingMode && Math.random() > 0.7) {
            System.out.println("¡Dragonite evade el ataque gracias a su vuelo!");
            return;
        }

        currentHP = Math.max(0, currentHP - amount);

        // Si recibe mucho daño, sale del modo vuelo
        if (flyingMode && amount > maxHP * 0.2) {
            flyingMode = false;
            System.out.println("¡Dragonite pierde su estabilidad aérea y desciende!");
        }
    }

    // Métodos específicos de Dragonite
    public boolean isFlying() {
        return flyingMode;
    }

    public void takeFlightMode() {
        if (!flyingMode && currentHP > maxHP * 0.3) {
            flyingMode = true;
            this.speed = (int)(this.speed * 1.5);
            System.out.println("¡Dragonite levanta el vuelo! Su velocidad aumenta considerablemente.");
        } else if (flyingMode) {
            System.out.println("Dragonite ya está volando.");
        } else {
            System.out.println("Dragonite no tiene suficiente energía para volar.");
        }
    }

    public void dragonDance() {
        // Movimiento de danza que aumenta estadísticas
        this.attack = (int)(this.attack * 1.2);
        this.speed = (int)(this.speed * 1.2);
        System.out.println("¡Dragonite realiza una Danza Dragón! Su ataque y velocidad aumentan.");
    }

    public void rescueOperation() {
        // Simula la capacidad de Dragonite para rescatar a náufragos
        System.out.println("Dragonite busca a personas en peligro para rescatarlas.");
        // Este método podría tener diferentes efectos en un sistema de juego real
    }
}