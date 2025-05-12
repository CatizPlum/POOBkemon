package domain;

import java.util.ArrayList;

public class Grumpig extends AbstractPokemon {

    private boolean dancingMood;

    public Grumpig() {
        this.name = "Grumpig";
        this.primaryType = Type.PSYCHIC;
        this.secondaryType = null;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 207;
        this.defense = 251;
        this.specialAttack = 306;
        this.specialDefense = 350;
        this.speed = 284;

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Confusion", Type.PSYCHIC, 50, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Psybeam", Type.PSYCHIC, 65, 100, MoveCategory.SPECIAL)); // Agregado Psybeam
        moves.add(new Move("Calm Mind", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Psychic", Type.PSYCHIC, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Miracle Eye", Type.PSYCHIC, 0, 0, MoveCategory.STATUS)); // Agregado Miracle Eye
        moves.add(new Move("Psycho Cut", Type.PSYCHIC, 70, 100, MoveCategory.SPECIAL)); // Agregado Psycho Cut

        // Movimientos por TM/HM
        moves.add(new Move("Thunderbolt", Type.ELECTRIC, 95, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Shadow Ball", Type.GHOST, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Protect", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Energy Ball", Type.PLANT, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Dazzling Gleam", Type.FAIRY, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Ice Beam", Type.ICE, 95, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL));
        moves.add(new Move("Focus Blast", Type.FIGHTING, 120, 70, MoveCategory.SPECIAL));

        // Movimientos por Tutor
        moves.add(new Move("Heal Bell", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Helping Hand", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Icy Wind", Type.ICE, 55, 95, MoveCategory.SPECIAL));
        moves.add(new Move("Signal Beam", Type.BUG, 75, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Trick", Type.PSYCHIC, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Pain Split", Type.NORMAL, 0, 0, MoveCategory.STATUS));
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            dancingMood = false;
            System.out.println("¡Grumpig pierde el ritmo tras el daño recibido!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            dancingMood = true;
            System.out.println("¡Grumpig comienza a bailar de felicidad!");
        }
    }

    public boolean isDancing() {
        return dancingMood;
    }

    public void psyPulse() {
        if (dancingMood) {
            System.out.println("¡Grumpig libera una potente onda psíquica al ritmo de su danza!");
        } else {
            System.out.println("Grumpig no está inspirado para bailar y atacar.");
        }
    }
}
