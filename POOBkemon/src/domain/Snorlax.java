package domain;

import java.util.ArrayList;

public class Snorlax extends AbstractPokemon {

    private boolean deeplyAsleep;

    public Snorlax() {
        this.name = "Snorlax";
        this.primaryType = Type.NORMAL;
        this.secondaryType = null;
        this.maxHP = 524;
        this.currentHP = maxHP;
        this.attack = 350;
        this.defense = 251;
        this.specialAttack = 251;
        this.specialDefense = 350;
        this.speed = 174;

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

    public void restDeeply() {
        this.deeplyAsleep = true;
        this.currentHP = this.maxHP;
        System.out.println("Snorlax entra en un sueño profundo y se cura completamente.");
    }

    public void wakeUp() {
        this.deeplyAsleep = false;
        System.out.println("Snorlax se ha despertado.");
    }

    public boolean isDeeplyAsleep() {
        return deeplyAsleep;
    }

    public void snoreLoudly() {
        if (deeplyAsleep) {
            System.out.println("¡Snorlax ronca tan fuerte que asusta a sus enemigos!");
        } else {
            System.out.println("Snorlax no está dormido profundamente y no puede usar Ronquido.");
        }
    }
}