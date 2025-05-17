package domain;

import java.util.ArrayList;
import java.util.List;

public class Gengar extends AbstractPokemon  {

    private boolean shadowForm;  // Estado de forma sombra

    public Gengar() {
        this.name = "Gengar";
        this.primaryType = Type.GHOST;
        this.secondaryType = Type.POISON;
        this.maxHP = 324;
        this.currentHP = maxHP;
        this.attack = 251;
        this.defense = 240;
        this.specialAttack = 394;
        this.specialDefense = 273;
        this.speed = 350;
        this.shadowForm = false;

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
        // Si está en forma sombra, recibe menos daño de ataques físicos
        if (shadowForm && amount > 0) {
            amount = (int)(amount * 0.7);
            System.out.println("¡La forma sombra de Gengar reduce el daño recibido!");
        }

        currentHP = Math.max(0, currentHP - amount);
    }

    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);

        // Si se recupera completamente, puede volver a usar su forma sombra
        if (currentHP == maxHP) {
            shadowForm = false;
        }
    }

    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }

    // Métodos específicos de Gengar
    public boolean isInShadowForm() {
        return shadowForm;
    }

    public void enterShadowForm() {
        if (!shadowForm && currentHP > maxHP * 0.3) {
            shadowForm = true;
            this.speed = (int)(this.speed * 1.2);
            this.specialAttack = (int)(this.specialAttack * 1.1);
            System.out.println("¡Gengar se sumerge en las sombras! Su velocidad y ataque especial aumentan.");
        } else {
            System.out.println("Gengar no tiene suficiente energía para entrar en forma sombra.");
        }
    }

    public void stealSoul() {
        // Ataque especial que drena vida del oponente
        System.out.println("¡Gengar intenta robar el alma de su oponente!");

        // Aumenta temporalmente su ataque especial
        this.specialAttack = (int)(this.specialAttack * 1.3);

        // Este método simula un efecto, en un sistema real recuperaría PS del oponente
        int healAmount = maxHP / 5;
        heal(healAmount);
        System.out.println("¡Gengar recupera " + healAmount + " PS!");
    }
}