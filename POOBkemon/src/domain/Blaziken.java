package domain;

import java.util.ArrayList;

public class Blaziken extends AbstractPokemon {

    private boolean intenseFlamesActive = false; // Para comprobar si la habilidad está activa
    private int flamesTurns = 0; // Contador para la duración de las llamas intensas (si fuera temporal)

    public Blaziken() {
        this.name = "Blaziken";
        this.primaryType = Type.FIRE;
        this.secondaryType = Type.FIGHTING;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 372;
        this.defense = 262;
        this.specialAttack = 350;
        this.specialDefense = 262;
        this.speed = 284;
        this.moves = new ArrayList<>();

        // Movimientos por Nivel
        moves.add(new Move("Ember", Type.FIRE, 40, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Growl", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Focus Energy", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Peck", Type.FLYING, 35, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Double Kick", Type.FIGHTING, 30, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Fire Punch", Type.FIRE, 75, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Blaze Kick", Type.FIRE, 85, 90, MoveCategory.PHYSICAL));
        moves.add(new Move("Slash", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Sky Uppercut", Type.FIGHTING, 85, 90, MoveCategory.PHYSICAL));
        moves.add(new Move("Flamethrower", Type.FIRE, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Endeavor", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Close Combat", Type.FIGHTING, 120, 100, MoveCategory.PHYSICAL));

        // Movimientos por Huevo
        moves.add(new Move("Counter", Type.FIGHTING, 0, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Reversal", Type.FIGHTING, 20, 100, MoveCategory.PHYSICAL));

        // Movimientos por TM
        moves.add(new Move("TM01 - Focus Punch", Type.FIGHTING, 150, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("TM02 - Dragon Claw", Type.DRAGON, 80, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("TM03 - Water Pulse", Type.WATER, 60, 100, MoveCategory.SPECIAL));
        moves.add(new Move("TM04 - Calm Mind", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("TM06 - Toxic", Type.POISON, 0, 90, MoveCategory.STATUS));
        moves.add(new Move("TM10 - Hidden Power", Type.NORMAL, 60, 100, MoveCategory.SPECIAL));
        moves.add(new Move("TM11 - Sunny Day", Type.FIRE, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("TM15 - Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL));
        moves.add(new Move("TM18 - Rain Dance", Type.WATER, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("TM22 - SolarBeam", Type.PLANT, 120, 100, MoveCategory.SPECIAL));
        moves.add(new Move("TM26 - Earthquake", Type.GROUND, 100, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("TM27 - Return", Type.NORMAL, 0, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("TM29 - Psychic", Type.PSYCHIC, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("TM35 - Flamethrower", Type.FIRE, 90, 100, MoveCategory.SPECIAL));
    }


    // Habilidad pasiva: Llamas intensas
    public void intenseFlames() {
        System.out.println("Blaziken expulsa llamas intensas desde sus muñecas.");

        // Activamos la habilidad de Llamas Intensadas.
        if (!intenseFlamesActive) {
            intenseFlamesActive = true;
            this.attack *= 1.3; // Aumenta el ataque en un 30%
            System.out.println("¡El poder de Blaziken aumenta por las llamas intensas!");
        }
        //Duración de la habilidad es de tres turnos
        flamesTurns = 3;
    }

    // Método que puede ser llamado al final de cada turno para decrementar la duración de la habilidad
    public void endTurn() {
        if (intenseFlamesActive) {
            flamesTurns--;
            if (flamesTurns <= 0) {
                intenseFlamesActive = false;
                this.attack /= 1.3; // El ataque vuelve a su valor normal
                System.out.println("El poder de Blaziken disminuye al cesar las llamas intensas.");
            }
        }
    }
    public void igniteSpirit() {
        this.attack *= 1.05;
        this.speed *= 1.05;
        System.out.println("Blaziken's fighting spirit flares up!");
    }

}
