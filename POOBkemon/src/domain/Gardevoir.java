package domain;

import java.util.ArrayList;

public class Gardevoir extends AbstractPokemon {

    private boolean emotionalBalance;

    public Gardevoir() {
        this.name = "Gardevoir";
        this.primaryType = Type.PSYCHIC;
        this.secondaryType = Type.FAIRY;
        this.maxHP = 340;
        this.currentHP = maxHP;
        this.attack = 251;
        this.defense = 251;
        this.specialAttack = 383;
        this.specialDefense = 361;
        this.speed = 284;

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Confusion", Type.PSYCHIC, 50, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Teleport", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Double Team", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Magical Leaf", Type.PLANT, 60, 0, MoveCategory.SPECIAL));
        moves.add(new Move("Calm Mind", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Psychic", Type.PSYCHIC, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Charm", Type.FAIRY, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Moonblast", Type.FAIRY, 95, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Healing Wish", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));

        // Movimientos por MT/HM
        moves.add(new Move("Thunderbolt", Type.ELECTRIC, 95, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Shadow Ball", Type.GHOST, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Thunder Wave", Type.ELECTRIC, 0, 90, MoveCategory.STATUS));
        moves.add(new Move("Protect", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Energy Ball", Type.PLANT, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Dazzling Gleam", Type.FAIRY, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Ice Beam", Type.ICE, 95, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL));
        moves.add(new Move("Focus Blast", Type.FIGHTING, 120, 70, MoveCategory.SPECIAL));
        moves.add(new Move("Calm Mind", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));

        // Movimientos por tutor
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
            emotionalBalance = false;
            System.out.println("¡Gardevoir está perturbada por el daño recibido!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            emotionalBalance = true;
            System.out.println("¡Gardevoir se siente en armonía!");
        }
    }

    public boolean isEmotionallyBalanced() {
        return emotionalBalance;
    }

    public void psychicBurst() {
        if (emotionalBalance) {
            System.out.println("¡Gardevoir desata una explosión psíquica devastadora!");
        } else {
            System.out.println("Gardevoir no está centrada para usar su poder.");
        }
    }
}
