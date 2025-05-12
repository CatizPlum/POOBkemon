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

        // Movimientos por nivel (algunos representativos)
        moves.add(new Move("Tackle", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Scary Face", Type.NORMAL, 0, 90, MoveCategory.STATUS));
        moves.add(new Move("Charm", Type.FAIRY, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Bite", Type.DARK, 60, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Roar", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Rage", Type.NORMAL, 20, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Take Down", Type.NORMAL, 90, 85, MoveCategory.PHYSICAL));
        moves.add(new Move("Crunch", Type.DARK, 80, 100, MoveCategory.PHYSICAL));

        // Movimientos por huevo (Egg moves)
        moves.add(new Move("Close Combat", Type.FIGHTING, 120, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Heal Bell", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Metronome", Type.NORMAL, 0, 100, MoveCategory.STATUS));

        // Movimientos por MT/MO
        moves.add(new Move("Shadow Ball", Type.GHOST, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Earthquake", Type.GROUND, 100, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Thunderbolt", Type.ELECTRIC, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL));
        moves.add(new Move("Brick Break", Type.FIGHTING, 75, 100, MoveCategory.PHYSICAL));

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
