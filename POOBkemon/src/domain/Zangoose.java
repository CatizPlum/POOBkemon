package domain;

import java.util.ArrayList;

public class Zangoose extends AbstractPokemon {

    private boolean clawTemper; // Reemplaza a happyMood

    public Zangoose() {
        this.name = "Zangoose";
        this.primaryType = Type.NORMAL;
        this.secondaryType = null;
        this.maxHP = 350;
        this.currentHP = maxHP;
        this.attack = 361;
        this.defense = 240;
        this.specialAttack = 240;
        this.specialDefense = 240;
        this.speed = 306;

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
            clawTemper = true;
            System.out.println("¡Zangoose está enfurecido por el daño recibido!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            clawTemper = false;
            System.out.println("Zangoose se calma un poco al sentirse mejor.");
        }
    }

    public boolean isClawTemperActive() {
        return clawTemper;
    }

    public void brandishClaws() {
        if (clawTemper) {
            System.out.println("¡Zangoose muestra sus garras con furia, listo para la batalla!");
        } else {
            System.out.println("Zangoose mantiene la calma, observando a su oponente.");
        }
    }
}