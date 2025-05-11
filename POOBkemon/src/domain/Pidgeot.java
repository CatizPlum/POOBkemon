package domain;

import java.util.ArrayList;

public class Pidgeot extends AbstractPokemon {

    public Pidgeot() {
        this.name = "Pidgeot";
        this.primaryType = Type.NORMAL;
        this.secondaryType = Type.FLYING;
        this.maxHP = 370;  // HP más alto que Crobat
        this.currentHP = maxHP;
        this.attack = 284;  // Ataque físico decente
        this.defense = 273;  // Defensa decente
        this.specialAttack = 262;  // Ataque especial más bajo
        this.specialDefense = 262;  // Defensa especial decente
        this.speed = 331;  // Velocidad alta pero no tanto como Crobat

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Gust", Type.FLYING, 40, 100, MoveCategory.SPECIAL)); // Movimiento inicial
        moves.add(new Move("Quick Attack", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL)); // Ataque prioritario
        moves.add(new Move("Wing Attack", Type.FLYING, 60, 100, MoveCategory.PHYSICAL)); // Ataque básico de volador
        moves.add(new Move("Air Slash", Type.FLYING, 75, 95, MoveCategory.SPECIAL)); // Movimiento con posibilidad de retroceso
        moves.add(new Move("Feather Dance", Type.FLYING, 0, 100, MoveCategory.STATUS)); // Reduce ataque del oponente
        moves.add(new Move("Agility", Type.PSYCHIC, 0, 0, MoveCategory.STATUS)); // Aumenta velocidad
        moves.add(new Move("Hurricane", Type.FLYING, 110, 70, MoveCategory.SPECIAL)); // Movimiento poderoso
        moves.add(new Move("Roost", Type.FLYING, 0, 0, MoveCategory.STATUS)); // Recupera HP

        // Movimientos MT/HM
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL)); // Movimiento poderoso
        moves.add(new Move("Steel Wing", Type.STEEL, 70, 90, MoveCategory.PHYSICAL)); // Cobertura contra roca/hielo
        moves.add(new Move("U-turn", Type.BUG, 70, 100, MoveCategory.PHYSICAL)); // Movimiento táctico
        moves.add(new Move("Fly", Type.FLYING, 90, 95, MoveCategory.PHYSICAL)); // Movimiento volador clásico
        moves.add(new Move("Heat Wave", Type.FIRE, 95, 90, MoveCategory.SPECIAL)); // Cobertura contra acero/hielo

        // Movimientos de Tutor
        moves.add(new Move("Sky Attack", Type.FLYING, 140, 90, MoveCategory.PHYSICAL)); // Movimiento de dos turnos
        moves.add(new Move("Tailwind", Type.FLYING, 0, 0, MoveCategory.STATUS)); // Aumenta velocidad del equipo
        moves.add(new Move("Defog", Type.FLYING, 0, 0, MoveCategory.STATUS)); // Elimina trampas y reduce evasión
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        // Pidgeot no tiene habilidades especiales que afecten el daño recibido
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        // Sin habilidades especiales de curación
    }

    // Método especial para Hurricane, su ataque más poderoso
    public void hurricane() {
        System.out.println("¡Pidgeot crea un poderoso Hurricane! Puede confundir al oponente.");
    }

    // Método para Roost, que recupera HP
    public void roost() {
        System.out.println("¡Pidgeot se posa para descansar y recuperar HP!");
    }

    // Método para Feather Dance, que reduce el ataque del oponente
    public void featherDance() {
        System.out.println("¡Pidgeot realiza un Feather Dance, reduciendo el ataque del oponente!");
    }

    // Método para Sky Attack, movimiento poderoso de dos turnos
    public void skyAttack() {
        System.out.println("¡Pidgeot se prepara para un devastador Sky Attack en el próximo turno!");
    }
}