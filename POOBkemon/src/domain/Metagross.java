package domain;


import java.util.ArrayList;
import java.util.List;

public class Metagross extends AbstractPokemon  {

    private boolean calculationMode;  // Estado de cálculo intensivo

    public Metagross() {
        this.name = "Metagross";
        this.primaryType = Type.STEEL;
        this.secondaryType = Type.PSYCHIC;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 405;
        this.defense = 394;
        this.specialAttack = 317;
        this.specialDefense = 306;
        this.speed = 262;
        this.calculationMode = false;

        this.moves = new ArrayList<>();

        moves.add(new Move("Metal Claw", Type.STEEL, 50, 95, MoveCategory.PHYSICAL));
        moves.add(new Move("Confusion", Type.PSYCHIC, 50, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Take Down", Type.NORMAL, 90, 85, MoveCategory.PHYSICAL));
        moves.add(new Move("Psychic", Type.PSYCHIC, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Meteor Mash", Type.STEEL, 100, 85, MoveCategory.PHYSICAL));
        moves.add(new Move("Scary Face", Type.NORMAL, 0, 90, MoveCategory.STATUS));
    }

    @Override
    public void takeDamage(int amount) {
        // Su cuerpo de acero reduce el daño
        int reducedAmount = (int)(amount * 0.85);
        currentHP = Math.max(0, currentHP - reducedAmount);

        // Si el daño es importante, entra en modo de cálculo para analizar al oponente
        if (amount > maxHP * 0.2 && !calculationMode) {
            calculationMode = true;
            System.out.println("¡Metagross entra en modo de cálculo avanzado para analizar a su oponente!");
        }
    }

    // Métodos específicos de Metagross
    public boolean isInCalculationMode() {
        return calculationMode;
    }

    public void activateCalculationMode() {
        if (!calculationMode) {
            calculationMode = true;
            System.out.println("¡Metagross activa sus cuatro cerebros para cálculos avanzados!");

            // Aumenta su precisión y potencia de ataques (representado como ataque y ataque especial)
            this.attack = (int)(this.attack * 1.15);
            this.specialAttack = (int)(this.specialAttack * 1.15);
        } else {
            System.out.println("Metagross ya está en modo de cálculo.");
        }
    }

    public void magneticLevitation() {
        // Simula la levitación magnética
        System.out.println("¡Metagross utiliza sus poderes magnéticos para levitar!");
        this.speed = (int)(this.speed * 1.3);

        // En un juego real, esto podría hacerlo inmune a ataques de tierra
    }

    public void ironDefense() {
        // Refuerza su cuerpo de acero
        this.defense = (int)(this.defense * 1.2);
        this.specialDefense = (int)(this.specialDefense * 1.1);
        System.out.println("¡Metagross refuerza su coraza de acero! Su defensa aumenta considerablemente.");
    }
}