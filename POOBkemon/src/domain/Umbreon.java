package domain;

import java.util.ArrayList;

public class Umbreon extends AbstractPokemon {

    private boolean isInDarkness;

    public Umbreon() {
        this.name = "Umbreon";
        this.primaryType = Type.DARK;
        this.secondaryType = null;
        this.maxHP = 394;
        this.currentHP = maxHP;
        this.attack = 251;
        this.defense = 350;
        this.specialAttack = 240;
        this.specialDefense = 394;
        this.speed = 251;

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
        if (currentHP < maxHP * 0.5) {
            isInDarkness = true;
            System.out.println("¡Umbreon se siente fortalecido por la oscuridad!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            isInDarkness = false;
            System.out.println("¡Umbreon ha salido de la oscuridad!");
        }
    }

    public boolean isInDarkness() {
        return isInDarkness;
    }

    // Método especial que representa la resistencia y defensa de Umbreon en la oscuridad
    public void moonlight() {
        if (isInDarkness) {
            System.out.println("¡Umbreon se recupera en la oscuridad con Moonlight!");
            heal(maxHP / 4); // Recupera una cuarta parte de su HP
        } else {
            System.out.println("Umbreon necesita estar en la oscuridad para utilizar Moonlight.");
        }
    }

    // Método adicional para activar una defensa contra efectos negativos utilizando Sincronía
    public void synchronizeStatus() {
        System.out.println("¡Umbreon utiliza Sincronía para compartir efectos negativos con el oponente!");
    }
}