package domain;

import java.util.ArrayList;

public class Absol extends AbstractPokemon {

    private boolean hasSuperstition;

    public Absol() {
        this.name = "Absol";
        this.primaryType = Type.DARK;
        this.secondaryType = null;
        this.maxHP = 334;
        this.currentHP = maxHP;
        this.attack = 394;
        this.defense = 240;
        this.specialAttack = 273;
        this.specialDefense = 240;
        this.speed = 273;

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Bite", Type.DARK, 60, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Sucker Punch", Type.DARK, 70, 100, MoveCategory.PHYSICAL));  // Movimiento distintivo
        moves.add(new Move("Night Slash", Type.DARK, 70, 100, MoveCategory.PHYSICAL));   // Movimiento distintivo
        moves.add(new Move("Pursuit", Type.DARK, 40, 100, MoveCategory.PHYSICAL));        // Movimiento distintivo
        moves.add(new Move("Zen Headbutt", Type.PSYCHIC, 80, 90, MoveCategory.PHYSICAL));  // Movimiento Psíquico físico
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL));    // Movimiento explosivo

        // Movimientos MT/HM
        moves.add(new Move("Shadow Ball", Type.GHOST, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Protect", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Dazzling Gleam", Type.FAIRY, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Focus Blast", Type.FIGHTING, 120, 70, MoveCategory.SPECIAL)); // Movimiento luchador
        moves.add(new Move("Energy Ball", Type.PLANT, 80, 100, MoveCategory.SPECIAL));    // Movimiento de energía

        // Movimientos de Tutor
        moves.add(new Move("Heal Bell", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Helping Hand", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Signal Beam", Type.BUG, 75, 100, MoveCategory.SPECIAL));
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            hasSuperstition = true;
            System.out.println("¡Absol siente una mala presagio y se llena de energía oscura!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            hasSuperstition = false;
            System.out.println("¡Absol se calma y pierde la energía oscura del presagio!");
        }
    }

    public boolean hasSuperstition() {
        return hasSuperstition;
    }

    // Método especial que hace que Absol pueda hacer un ataque extra fuerte cuando su HP es bajo
    public void darkFury() {
        if (hasSuperstition) {
            System.out.println("¡Absol desata su furia oscura con un ataque devastador!");
        } else {
            System.out.println("Absol no está bajo un mal presagio en este momento.");
        }
    }
}
