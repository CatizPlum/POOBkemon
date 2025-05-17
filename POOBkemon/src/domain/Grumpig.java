package domain;

import java.util.ArrayList;

public class Grumpig extends AbstractPokemon {

    private boolean dancingMood;

    public Grumpig() {
        this.name = "Grumpig";
        this.primaryType = Type.PSYCHIC;
        this.secondaryType = null;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 207;
        this.defense = 251;
        this.specialAttack = 306;
        this.specialDefense = 350;
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
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            dancingMood = false;
            System.out.println("¡Grumpig pierde el ritmo tras el daño recibido!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            dancingMood = true;
            System.out.println("¡Grumpig comienza a bailar de felicidad!");
        }
    }

    public boolean isDancing() {
        return dancingMood;
    }

    public void psyPulse() {
        if (dancingMood) {
            System.out.println("¡Grumpig libera una potente onda psíquica al ritmo de su danza!");
        } else {
            System.out.println("Grumpig no está inspirado para bailar y atacar.");
        }
    }
}