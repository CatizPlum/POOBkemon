package domain;

import java.util.ArrayList;

public class Gardevoir extends AbstractPokemon {

    private boolean emotionalBalance;

    public Gardevoir() {
        this.name = "Gardevoir";
        this.primaryType = Type.PSYCHIC;
        this.secondaryType = Type.FAIRY;
        this.maxHP = 340;
        this.currentHP = maxHP;
        this.attack = 251;
        this.defense = 251;
        this.specialAttack = 383;
        this.specialDefense = 361;
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
            emotionalBalance = false;
            System.out.println("¡Gardevoir está perturbada por el daño recibido!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            emotionalBalance = true;
            System.out.println("¡Gardevoir se siente en armonía!");
        }
    }

    public boolean isEmotionallyBalanced() {
        return emotionalBalance;
    }

    public void psychicBurst() {
        if (emotionalBalance) {
            System.out.println("¡Gardevoir desata una explosión psíquica devastadora!");
        } else {
            System.out.println("Gardevoir no está centrada para usar su poder.");
        }
    }
}