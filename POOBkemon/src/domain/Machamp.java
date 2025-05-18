package domain;

import java.util.ArrayList;

public class Machamp extends AbstractPokemon {
    public Machamp() {
        this.name = "Machamp";
        this.primaryType = Type.FIGHTING;
        this.secondaryType = null;
        this.maxHP = 384;
        this.currentHP = maxHP;
        this.attack = 394; // Muy alto
        this.defense = 284;
        this.specialAttack = 218;
        this.specialDefense = 273;
        this.speed = 207;

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

    // Habilidad pasiva: aumenta daño físico
    public void applyPassiveAbility(Move move) {
        if (move.getCategory() == MoveCategory.PHYSICAL) {
            double boostedPower = move.getPower() * 1.25;
            move.setPower((int) boostedPower);
            System.out.println(this.name + "'s BruteForceBoost increases " + move.getName() + "'s power to " + move.getPower());
        }
    }



}
