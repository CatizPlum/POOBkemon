package domain;

import java.util.ArrayList;

public class Sceptile extends AbstractPokemon {

    private boolean bladesSharpened;

    public Sceptile() {
        this.name = "Sceptile";
        this.primaryType = Type.PLANT;
        this.secondaryType = null;
        this.maxHP = 310;
        this.currentHP = maxHP;
        this.attack = 265;
        this.defense = 215;
        this.specialAttack = 300;
        this.specialDefense = 210;
        this.speed = 350;

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
            bladesSharpened = false;
            System.out.println("¡Sceptile pierde su filo tras recibir daño!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            bladesSharpened = true;
            System.out.println("¡Sceptile afila sus hojas mientras se recupera!");
        }
    }

    public boolean areBladesSharpened() {
        return bladesSharpened;
    }

    public void leafStorm() {
        if (bladesSharpened) {
            System.out.println("¡Sceptile lanza una tormenta de hojas afiladas devastadora!");
        } else {
            System.out.println("Sceptile no ha afilado sus hojas. ¡El ataque pierde potencia!");
        }
    }
}