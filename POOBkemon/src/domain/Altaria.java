package domain;

import java.util.ArrayList;

public class Altaria extends AbstractPokemon {
    private boolean enraged = false;

    public Altaria() {
        this.name = "Altaria";
        this.primaryType = Type.DRAGON;
        this.secondaryType = Type.FLYING;
        this.maxHP = 354;
        this.currentHP = maxHP;
        this.attack = 262;
        this.defense = 306;
        this.specialAttack = 262;
        this.specialDefense = 339;
        this.speed = 284;


        this.moves = new ArrayList<>();

        // Movimientos por nivel (Level-up)
        moves.add(new Move("Peck", Type.FLYING, 35, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Growl", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Astonish", Type.GHOST, 30, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Sing", Type.NORMAL, 0, 55, MoveCategory.STATUS));
        moves.add(new Move("Fury Attack", Type.NORMAL, 15, 85, MoveCategory.PHYSICAL));
        moves.add(new Move("Safeguard", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Mist", Type.ICE, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Take Down", Type.NORMAL, 90, 85, MoveCategory.PHYSICAL));
        moves.add(new Move("DragonBreath", Type.DRAGON, 60, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Perish Song", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Refresh", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Dragon Dance", Type.DRAGON, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Sky Attack", Type.FLYING, 140, 90, MoveCategory.PHYSICAL));

        // Movimientos por evolución
        moves.add(new Move("DragonBreath", Type.DRAGON, 60, 100, MoveCategory.SPECIAL)); // ya incluido

        // Movimientos huevo
        moves.add(new Move("Agility", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Haze", Type.ICE, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Pursuit", Type.DARK, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Rage", Type.NORMAL, 20, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Dragon Dance", Type.DRAGON, 0, 0, MoveCategory.STATUS)); // duplicado posible

        // MT/HM (TM/HM Moves)
        moves.add(new Move("Fly", Type.FLYING, 90, 95, MoveCategory.PHYSICAL));
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL));
        moves.add(new Move("Protect", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Toxic", Type.POISON, 0, 90, MoveCategory.STATUS));
        moves.add(new Move("Double Team", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Aerial Ace", Type.FLYING, 60, 0, MoveCategory.PHYSICAL));
        moves.add(new Move("Rest", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Attract", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Steel Wing", Type.STEEL, 70, 90, MoveCategory.PHYSICAL));
        moves.add(new Move("Facade", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Secret Power", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Snore", Type.NORMAL, 40, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Endure", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Swagger", Type.NORMAL, 0, 90, MoveCategory.STATUS));
        moves.add(new Move("Sleep Talk", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Swift", Type.NORMAL, 60, 0, MoveCategory.SPECIAL));
        moves.add(new Move("Defense Curl", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Body Slam", Type.NORMAL, 85, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Double-Edge", Type.NORMAL, 120, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Mimic", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Substitute", Type.NORMAL, 0, 0, MoveCategory.STATUS));
    }

    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);

        if (!enraged && amount > 80) {
            enraged = true;
            specialAttack += 50;
            System.out.println("¡Altaria se enfurece! Su chillido estridente intimida y aumenta su ataque especial.");
        }
    }

    public void singSoothingMelody() {
        double chance = Math.random();
        if (chance < 0.25) {
            System.out.println("Altaria sings a crystal-clear melody. The opponent is mesmerized and loses their turn.");
        } else {
            System.out.println("Altaria hums a soothing tune, but the opponent resists its charm.");
        }
    }

    public boolean isEnraged() {
        return enraged;
    }

    public void restInCloud() {
        System.out.println("Altaria snuggles into its fluffy wings and regains some energy.");
        this.heal(40); // Restores health
    }



}

