package domain;

import java.util.ArrayList;

public class Flygon extends AbstractPokemon {

    private boolean levitateActivated;

    public Flygon() {
        this.name = "Flygon";
        this.primaryType = Type.GROUND;
        this.secondaryType = Type.DRAGON;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 328;
        this.defense = 284;
        this.specialAttack = 284;
        this.specialDefense = 284;
        this.speed = 328;

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Tackle", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Dragon Breath", Type.DRAGON, 60, 100, MoveCategory.SPECIAL)); // Movimiento tipo Dragón
        moves.add(new Move("Earthquake", Type.GROUND, 100, 100, MoveCategory.PHYSICAL)); // Movimiento tierra
        moves.add(new Move("Fly", Type.FLYING, 90, 95, MoveCategory.PHYSICAL)); // Movimiento volador
        moves.add(new Move("U-turn", Type.BUG, 70, 100, MoveCategory.PHYSICAL)); // Movimiento de cambio rápido
        moves.add(new Move("Sand Tomb", Type.GROUND, 35, 85, MoveCategory.PHYSICAL)); // Movimiento de tierra
        moves.add(new Move("Crunch", Type.DARK, 80, 100, MoveCategory.PHYSICAL)); // Movimiento tipo siniestro
        moves.add(new Move("Steel Wing", Type.STEEL, 70, 90, MoveCategory.PHYSICAL)); // Movimiento de acero

        // Movimientos MT/HM
        moves.add(new Move("Stone Edge", Type.ROCK, 100, 80, MoveCategory.PHYSICAL)); // Movimiento roca
        moves.add(new Move("Earth Power", Type.GROUND, 90, 100, MoveCategory.SPECIAL)); // Movimiento tierra
        moves.add(new Move("Fire Blast", Type.FIRE, 110, 85, MoveCategory.SPECIAL)); // Movimiento de fuego
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL)); // Movimiento explosivo

        // Movimientos de Tutor
        moves.add(new Move("Iron Tail", Type.STEEL, 100, 75, MoveCategory.PHYSICAL)); // Movimiento de acero
        moves.add(new Move("Dragon Claw", Type.DRAGON, 80, 100, MoveCategory.PHYSICAL)); // Movimiento dragón
        moves.add(new Move("Roar", Type.NORMAL, 0, 0, MoveCategory.STATUS)); // Movimiento para forzar al oponente a huir
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        // Si Flygon recibe daño de un movimiento de tipo tierra, revisamos si tiene Levitate.
        if (this.primaryType == Type.GROUND || (this.secondaryType != null && this.secondaryType == Type.GROUND)) {
            if (levitateActivated) {
                System.out.println("¡Flygon es inmune a los movimientos de tipo Tierra gracias a su habilidad Levitate!");
                // Si es inmune a movimientos de tipo Tierra debido a Levitate, no recibe el daño.
                return;
            }
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        // Si la salud de Flygon se encuentra por encima del 70%, desactiva su habilidad Levitate.
        if (currentHP > maxHP * 0.7) {
            levitateActivated = true;
        }
    }

    public boolean hasLevitateActivated() {
        return levitateActivated;
    }

    // Método especial para utilizar Dragon Claw, un fuerte ataque de tipo Dragón
    public void dragonClaw() {
        System.out.println("¡Flygon utiliza Dragon Claw con gran poder!");
    }

    // Método adicional para usar Fly y hacer daño mientras vuela
    public void fly() {
        System.out.println("¡Flygon se eleva en el aire para realizar un potente ataque Fly!");
    }

    // Método adicional para usar U-turn y cambiar de lugar
    public void uTurn() {
        System.out.println("¡Flygon usa U-turn y cambia de Pokémon rápidamente!");
    }

    // Método para activar la habilidad Levitate cuando Flygon está en el aire
    public void activateLevitate() {
        levitateActivated = true;
        System.out.println("¡Flygon activa su habilidad Levitate!");
    }

    // Método para desactivar la habilidad Levitate cuando Flygon está en el suelo
    public void deactivateLevitate() {
        levitateActivated = false;
        System.out.println("¡Flygon desactiva su habilidad Levitate!");
    }
}
