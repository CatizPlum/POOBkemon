package domain;

import java.util.ArrayList;

public class Seviper extends AbstractPokemon {

    public Seviper() {
        this.name = "Seviper";
        this.primaryType = Type.POISON;
        this.secondaryType = Type.BUG;
        this.maxHP = 350;
        this.currentHP = maxHP;
        this.attack = 328;
        this.defense = 240;
        this.specialAttack = 328;
        this.specialDefense = 240;
        this.speed = 251;

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Tackle", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Poison Tail", Type.POISON, 50, 100, MoveCategory.PHYSICAL)); // Movimiento de veneno
        moves.add(new Move("Sludge Bomb", Type.POISON, 90, 100, MoveCategory.SPECIAL)); // Movimiento de veneno fuerte
        moves.add(new Move("Bug Bite", Type.BUG, 60, 100, MoveCategory.PHYSICAL)); // Movimiento de bicho
        moves.add(new Move("Screech", Type.NORMAL, 0, 85, MoveCategory.STATUS)); // Reduce defensa del oponente
        moves.add(new Move("Toxic", Type.POISON, 0, 90, MoveCategory.STATUS)); // Envenena al oponente
        moves.add(new Move("Iron Tail", Type.STEEL, 100, 75, MoveCategory.PHYSICAL)); // Movimiento de acero
        moves.add(new Move("Double Team", Type.NORMAL, 0, 0, MoveCategory.STATUS)); // Aumenta evasión

        // Movimientos MT/HM
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL)); // Movimiento explosivo
        moves.add(new Move("X-Scissor", Type.BUG, 80, 100, MoveCategory.PHYSICAL)); // Movimiento de bicho
        moves.add(new Move("Stone Edge", Type.ROCK, 100, 80, MoveCategory.PHYSICAL)); // Movimiento roca
        moves.add(new Move("Earthquake", Type.GROUND, 100, 100, MoveCategory.PHYSICAL)); // Movimiento tierra

        // Movimientos de Tutor
        moves.add(new Move("Giga Drain", Type.PLANT, 75, 100, MoveCategory.SPECIAL)); // Movimiento de absorción
        moves.add(new Move("Roar", Type.NORMAL, 0, 0, MoveCategory.STATUS)); // Fuerza al oponente a huir
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        // No es necesario manejar habilidades como Levitate, ya que Seviper no la tiene.
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        // Seviper no tiene una habilidad activa como Levitate, por lo que no hay que cambiar la lógica aquí.
    }

    // Método especial para utilizar Sludge Bomb, un potente ataque de veneno
    public void sludgeBomb() {
        System.out.println("¡Seviper lanza Sludge Bomb, cubriendo al oponente en veneno!");
    }

    // Método adicional para utilizar Bug Bite, un ataque que consume energía del oponente
    public void bugBite() {
        System.out.println("¡Seviper usa Bug Bite, mordiendo con sus poderosas mandíbulas!");
    }

    // Método para usar Toxic y envenenar al oponente
    public void toxic() {
        System.out.println("¡Seviper usa Toxic y envenena al oponente!");
    }

    // Método adicional para usar Double Team y aumentar su evasión
    public void doubleTeam() {
        System.out.println("¡Seviper usa Double Team, aumentando su evasión!");
    }
}
