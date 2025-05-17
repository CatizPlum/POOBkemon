package domain;

import java.util.ArrayList;

public class Absol extends AbstractPokemon {

    private boolean hasSuperstition;

    public Absol() {
        this.name = "Absol";
        this.primaryType = Type.DARK;
        this.secondaryType = null;
        this.maxHP = 334;
        this.currentHP = maxHP;
        this.attack = 394;
        this.defense = 240;
        this.specialAttack = 273;
        this.specialDefense = 240;
        this.speed = 273;

        this.moves = new ArrayList<>();

        initializeMoves();
    }

    @Override
    protected void initializeMoves() {
        learnMove("Leer");
        learnMove("Quick Attack");
        learnMove("Double Team");
        learnMove("Knock Off");
        learnMove("Future Sight");
        learnMove("Water Pulse");
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            hasSuperstition = true;
            System.out.println("¡Absol siente una mala presagio y se llena de energía oscura!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            hasSuperstition = false;
            System.out.println("¡Absol se calma y pierde la energía oscura del presagio!");
        }
    }

    public boolean hasSuperstition() {
        return hasSuperstition;
    }

    // Método especial que hace que Absol pueda hacer un ataque extra fuerte cuando su HP es bajo
    public void darkFury() {
        if (hasSuperstition) {
            System.out.println("¡Absol desata su furia oscura con un ataque devastador!");
        } else {
            System.out.println("Absol no está bajo un mal presagio en este momento.");
        }
    }
}