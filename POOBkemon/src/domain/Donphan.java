package domain;

import java.util.ArrayList;

public class Donphan extends AbstractPokemon {

    private boolean sturdyActivated;

    public Donphan() {
        this.name = "Donphan";
        this.primaryType = Type.GROUND;
        this.secondaryType = null;
        this.maxHP = 384;
        this.currentHP = maxHP;
        this.attack = 372;
        this.defense = 372;
        this.specialAttack = 240;
        this.specialDefense = 240;
        this.speed = 218;

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Tackle", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Rollout", Type.NORMAL, 30, 90, MoveCategory.PHYSICAL)); // Movimiento distintivo
        moves.add(new Move("Earthquake", Type.GROUND, 100, 100, MoveCategory.PHYSICAL)); // Movimiento tierra
        moves.add(new Move("Mud-Slap", Type.GROUND, 20, 100, MoveCategory.PHYSICAL)); // Movimiento de tierra
        moves.add(new Move("Defense Curl", Type.NORMAL, 0, 0, MoveCategory.STATUS)); // Aumenta la defensa
        moves.add(new Move("Magnitude", Type.GROUND, 10, 100, MoveCategory.PHYSICAL)); // Movimiento de tierra
        moves.add(new Move("Body Slam", Type.NORMAL, 85, 100, MoveCategory.PHYSICAL)); // Movimiento físico
        moves.add(new Move("Stealth Rock", Type.ROCK, 0, 0, MoveCategory.STATUS)); // Coloca rocas para dañar al oponente

        // Movimientos MT/HM
        moves.add(new Move("Stone Edge", Type.ROCK, 100, 80, MoveCategory.PHYSICAL)); // Movimiento roca
        moves.add(new Move("Earth Power", Type.GROUND, 90, 100, MoveCategory.SPECIAL)); // Movimiento tierra
        moves.add(new Move("Rock Slide", Type.ROCK, 75, 90, MoveCategory.PHYSICAL)); // Movimiento roca
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL)); // Movimiento explosivo

        // Movimientos de Tutor
        moves.add(new Move("Iron Tail", Type.STEEL, 100, 75, MoveCategory.PHYSICAL)); // Movimiento de acero
        moves.add(new Move("Yawn", Type.NORMAL, 0, 100, MoveCategory.STATUS)); // Causa sueño en el oponente
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        // Si Donphan recibe daño que podría haberlo dejado con menos del 1% de HP, activa su habilidad Sturdy.
        if (currentHP <= 1 && !sturdyActivated) {
            sturdyActivated = true;
            System.out.println("¡Donphan resiste el ataque con su habilidad Sturdy!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            sturdyActivated = false; // Desactiva Sturdy cuando Donphan está bien de salud.
        }
    }

    public boolean hasSturdyActivated() {
        return sturdyActivated;
    }

    // Método especial para hacer un ataque de roca con Rollout
    public void rollout() {
        System.out.println("¡Donphan lanza Rollout con gran poder!");
        // Lógica de Rollout en combate
    }

    // Método adicional para utilizar Stealth Rock, que daña a los Pokémon enemigos que entran al campo
    public void stealthRock() {
        System.out.println("¡Donphan coloca Stealth Rock en el campo!");
    }
}
