package domain;

import java.util.ArrayList;

public class Umbreon extends AbstractPokemon {

    private boolean isInDarkness;

    public Umbreon() {
        this.name = "Umbreon";
        this.primaryType = Type.DARK;
        this.secondaryType = null;
        this.maxHP = 394;
        this.currentHP = maxHP;
        this.attack = 251;
        this.defense = 350;
        this.specialAttack = 240;
        this.specialDefense = 394;
        this.speed = 251;

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Bite", Type.DARK, 60, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Foul Play", Type.DARK, 95, 100, MoveCategory.PHYSICAL)); // Movimiento distintivo
        moves.add(new Move("Moonlight", Type.DARK, 0, 0, MoveCategory.STATUS)); // Recupera vida
        moves.add(new Move("Confuse Ray", Type.GHOST, 0, 100, MoveCategory.STATUS)); // Confunde al oponente
        moves.add(new Move("Toxic", Type.POISON, 0, 90, MoveCategory.STATUS)); // Movimiento venenoso
        moves.add(new Move("Screech", Type.NORMAL, 0, 85, MoveCategory.STATUS)); // Reduce la defensa

        // Movimientos MT/HM
        moves.add(new Move("Shadow Ball", Type.GHOST, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Protect", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Dazzling Gleam", Type.FAIRY, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Psychic", Type.PSYCHIC, 90, 100, MoveCategory.SPECIAL)); // Movimiento psíquico
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL)); // Movimiento explosivo

        // Movimientos de Tutor
        moves.add(new Move("Heal Bell", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Helping Hand", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Signal Beam", Type.BUG, 75, 100, MoveCategory.SPECIAL));
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (currentHP < maxHP * 0.5) {
            isInDarkness = true;
            System.out.println("¡Umbreon se siente fortalecido por la oscuridad!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            isInDarkness = false;
            System.out.println("¡Umbreon ha salido de la oscuridad!");
        }
    }

    public boolean isInDarkness() {
        return isInDarkness;
    }

    // Método especial que representa la resistencia y defensa de Umbreon en la oscuridad
    public void moonlight() {
        if (isInDarkness) {
            System.out.println("¡Umbreon se recupera en la oscuridad con Moonlight!");
            heal(maxHP / 4); // Recupera una cuarta parte de su HP
        } else {
            System.out.println("Umbreon necesita estar en la oscuridad para utilizar Moonlight.");
        }
    }

    // Método adicional para activar una defensa contra efectos negativos utilizando Sincronía
    public void synchronizeStatus() {
        System.out.println("¡Umbreon utiliza Sincronía para compartir efectos negativos con el oponente!");
    }
}
