package domain;

import java.util.ArrayList;

public class Granbull extends AbstractPokemon {

    public Granbull() {
        this.name = "Granbull";
        this.primaryType = Type.FAIRY;
        this.secondaryType = null;
        this.maxHP = 384;
        this.currentHP = maxHP;
        this.attack = 372;
        this.defense = 273;
        this.specialAttack = 240;
        this.specialDefense = 240;
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

    // Habilidad pasiva: Mordida poderosa
    public void powerfulBite() {
        System.out.println("Granbull ataca con una mordida poderosa.");

        for (Move move : moves) {
            if (move.getName().equalsIgnoreCase("Bite") || move.getName().equalsIgnoreCase("Crunch")) {
                int originalPower = move.getPower();
                int boostedPower = (int)(originalPower * 1.5); // Aumenta el poder en un 50%
                System.out.println("El poder de " + move.getName() + " aumenta temporalmente de " +
                        originalPower + " a " + boostedPower + ".");
            }
        }
    }
}
