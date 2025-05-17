package domain;

import java.util.ArrayList;

public class Ninjask extends AbstractPokemon {
    private int turnsPassed = 0;

    public Ninjask() {
        this.name = "Ninjask";
        this.primaryType = Type.BUG;
        this.secondaryType = Type.FLYING;

        this.maxHP = 326;
        this.currentHP = maxHP;
        this.attack = 306;
        this.defense = 207;
        this.specialAttack = 218;
        this.specialDefense = 218;
        this.speed = 460;

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

    public void onTurnStart() {
        turnsPassed++;
        if (turnsPassed > 1) {
            this.speed += 10;
            System.out.println("¡La velocidad de Ninjask aumentó!");
        }
    }
}