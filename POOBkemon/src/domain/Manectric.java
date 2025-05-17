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

        initializeMoves();
    }

    @Override
    protected void initializeMoves() {
        learnMove("Leer");
        learnMove("Quick Attack");
        learnMove("Double Team");
        learnMove("Knock Off");
        learnMove("Future Sight");
        learnMove("Water Pulse");
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
