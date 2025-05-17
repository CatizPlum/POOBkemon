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

        // Movimientos por nivel
        moves.add(new Move("Scratch", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Harden", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Leech Life", Type.BUG, 20, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Sand-Attack", Type.GROUND, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Fury Swipes", Type.NORMAL, 18, 80, MoveCategory.PHYSICAL));
        moves.add(new Move("Mind Reader", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Double Team", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Agility", Type.PSYCHIC, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Fury Cutter", Type.BUG, 10, 95, MoveCategory.PHYSICAL));
        moves.add(new Move("Screech", Type.NORMAL, 0, 85, MoveCategory.STATUS));
        moves.add(new Move("Swords Dance", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Baton Pass", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Slash", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Detect", Type.FIGHTING, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Aerial Ace", Type.FLYING, 60, -1, MoveCategory.PHYSICAL)); // nunca falla

        // Movimientos por huevo
        moves.add(new Move("Gust", Type.FLYING, 40, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Silver Wind", Type.BUG, 60, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Bug Buzz", Type.BUG, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Endure", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Night Slash", Type.DARK, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Flail", Type.NORMAL, 0, 100, MoveCategory.PHYSICAL));

        // Movimientos por MT/MO
        moves.add(new Move("Toxic", Type.POISON, 0, 90, MoveCategory.STATUS));
        moves.add(new Move("Hidden Power", Type.NORMAL, 60, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Sunny Day", Type.FIRE, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Protect", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Rain Dance", Type.WATER, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("SolarBeam", Type.PLANT, 120, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Return", Type.NORMAL, 0, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Double Team", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Aerial Ace", Type.FLYING, 60, -1, MoveCategory.PHYSICAL));
        moves.add(new Move("Facade", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Rest", Type.PSYCHIC, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Attract", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Thief", Type.DARK, 60, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Snatch", Type.DARK, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Substitute", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Cut", Type.NORMAL, 50, 95, MoveCategory.PHYSICAL)); // MO01
        moves.add(new Move("Flash", Type.NORMAL, 0, 100, MoveCategory.STATUS)); // MO05
        moves.add(new Move("Shadow Ball", Type.GHOST, 80, 100, MoveCategory.SPECIAL)); // ya estaba
    }

    public void onTurnStart() {
        turnsPassed++;
        if (turnsPassed > 1) {
            this.speed += 10;
            System.out.println("¡La velocidad de Ninjask aumentó!");
        }
    }
}