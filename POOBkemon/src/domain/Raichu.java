package domain;

import java.util.ArrayList;

public class Raichu extends AbstractPokemon {

    public Raichu() {
        this.name = "RAICHU";
        this.primaryType = Type.ELECTRIC;
        this.secondaryType = null;
        this.maxHP = 324;
        this.currentHP = maxHP;
        this.attack = 306;
        this.defense = 229;
        this.specialAttack = 306;
        this.specialDefense = 284;
        this.speed = 350;

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

    public boolean tryStaticEffect() {
        double chance = Math.random();
        if (chance < 0.3) {
            System.out.println("¡El atacante fue paralizado por electricidad estática!");
            return true;
        }
        return false;
    }
}
