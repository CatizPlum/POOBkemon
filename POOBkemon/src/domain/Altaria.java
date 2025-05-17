package domain;

import java.util.ArrayList;

public class Altaria extends AbstractPokemon {
    private boolean enraged = false;

    public Altaria() {
        this.name = "Altaria";
        this.primaryType = Type.DRAGON;
        this.secondaryType = Type.FLYING;
        this.maxHP = 354;
        this.currentHP = maxHP;
        this.attack = 262;
        this.defense = 306;
        this.specialAttack = 262;
        this.specialDefense = 339;
        this.speed = 284;


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

        if (!enraged && amount > 80) {
            enraged = true;
            specialAttack += 50;
            System.out.println("¡Altaria se enfurece! Su chillido estridente intimida y aumenta su ataque especial.");
        }
    }

    public void singSoothingMelody() {
        double chance = Math.random();
        if (chance < 0.25) {
            System.out.println("Altaria sings a crystal-clear melody. The opponent is mesmerized and loses their turn.");
        } else {
            System.out.println("Altaria hums a soothing tune, but the opponent resists its charm.");
        }
    }

    public boolean isEnraged() {
        return enraged;
    }

    public void restInCloud() {
        System.out.println("Altaria snuggles into its fluffy wings and regains some energy.");
        this.heal(40); // Restores health
    }



}

