package domain;

import java.util.ArrayList;

public class Manectric extends AbstractPokemon {
    private boolean overcharged = true;

    public Manectric() {
        this.name = "Manectric";
        this.primaryType = Type.ELECTRIC;
        this.secondaryType = null;
        this.maxHP = 344;
        this.currentHP = maxHP;
        this.attack = 273;
        this.defense = 240;
        this.specialAttack = 339;
        this.specialDefense = 240;
        this.speed = 339;
        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Tackle", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Thunder Wave", Type.ELECTRIC, 0, 90, MoveCategory.STATUS));
        moves.add(new Move("Spark", Type.ELECTRIC, 65, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Quick Attack", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Howl", Type.NORMAL, 0, 0, MoveCategory.STATUS)); // Sube ataque
        moves.add(new Move("Charge", Type.ELECTRIC, 0, 0, MoveCategory.STATUS)); // Mejora siguiente ataque eléctrico
        moves.add(new Move("Bite", Type.DARK, 60, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Thunder", Type.ELECTRIC, 120, 70, MoveCategory.SPECIAL));
        moves.add(new Move("Roar", Type.NORMAL, 0, 0, MoveCategory.STATUS));

        // Movimientos por huevo
        moves.add(new Move("Crunch", Type.DARK, 80, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Fire Fang", Type.FIRE, 65, 95, MoveCategory.PHYSICAL));
        moves.add(new Move("Ice Fang", Type.ICE, 65, 95, MoveCategory.PHYSICAL));
        moves.add(new Move("Swift", Type.NORMAL, 60, 0, MoveCategory.SPECIAL)); // Nunca falla

        // Movimientos por MT/HM
        moves.add(new Move("Thunderbolt", Type.ELECTRIC, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Hidden Power", Type.NORMAL, 60, 100, MoveCategory.SPECIAL)); // Tipo variable
        moves.add(new Move("Facade", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Double Team", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Secret Power", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Protect", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Thunder", Type.ELECTRIC, 120, 70, MoveCategory.SPECIAL));
        moves.add(new Move("Iron Tail", Type.STEEL, 100, 75, MoveCategory.PHYSICAL));
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL));
    }


    @Override
    public void takeDamage(int amount) {
        int reducedAmount = (int) (amount * 0.95);
        currentHP = Math.max(0, currentHP - reducedAmount);
    }

    public void enterOverchargeState() {
        if (!overcharged) {
            this.speed = (int)(this.speed * 1.3);
            this.overcharged = true;
            System.out.println("⚡ Manectric entra en estado de sobrecarga: ¡su velocidad se incrementa un 30%!");
        } else {
            System.out.println("Manectric ya está en estado de sobrecarga.");
        }
    }
    public void applyOverchargeBonus(Move move) {
        if (overcharged && move.getType() == Type.ELECTRIC) {
            int boostedPower = (int)(move.getPower() * 1.3);
            move.setPower(boostedPower);
            System.out.println("El estado de sobrecarga potencia " + move.getName() + " a " + boostedPower + " de poder.");
            this.overcharged = false; // el efecto solo se aplica una vez
        }
    }

}
