package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Manectric, la evolución final de Electrike.
 * Pokémon de tipo Eléctrico puro especializado en velocidad y ataques especiales.
 * Posee un estado de sobrecarga que potencia sus movimientos eléctricos.
 */
public class Manectric extends AbstractPokemon implements Serializable {

    private boolean overcharged = true; // Estado de sobrecarga eléctrica

    /**
     * Constructor de Manectric. Inicializa sus estadísticas base y movimientos.
     * Estadísticas destacadas:
     * - Alta velocidad y ataque especial (339)
     * - HP moderado (344)
     * - Defensas equilibradas (240)
     */
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

    /**
     * Inicializa los movimientos que Manectric puede aprender por defecto.
     * Incluye movimientos rápidos y eléctricos.
     */
    @Override
    protected void initializeMoves() {
        learnMove("Leer");
        learnMove("Quick Attack");
        learnMove("Double Team");
        learnMove("Knock Off");
        learnMove("Future Sight");
        learnMove("Water Pulse");
    }

    /**
     * Sobreescribe el método para recibir daño, reduciéndolo en un 5%.
     * @param amount Cantidad de daño a recibir
     */
    @Override
    public void takeDamage(int amount) {
        int reducedAmount = (int) (amount * 0.95);
        currentHP = Math.max(0, currentHP - reducedAmount);
    }

    /**
     * Activa el estado de sobrecarga, aumentando la velocidad en un 30%.
     */
    public void enterOverchargeState() {
        if (!overcharged) {
            this.speed = (int)(this.speed * 1.3);
            this.overcharged = true;
            System.out.println("⚡ Manectric entra en estado de sobrecarga: ¡su velocidad se incrementa un 30%!");
        } else {
            System.out.println("Manectric ya está en estado de sobrecarga.");
        }
    }

    /**
     * Aplica el bonus de sobrecarga a movimientos eléctricos.
     * Potencia el movimiento en un 30% y consume el estado de sobrecarga.
     * @param move Movimiento a potenciar
     */
    public void applyOverchargeBonus(Move move) {
        if (overcharged && move.getType() == Type.ELECTRIC) {
            int boostedPower = (int)(move.getPower() * 1.3);
            move.setPower(boostedPower);
            System.out.println("El estado de sobrecarga potencia " + move.getName() + " a " + boostedPower + " de poder.");
            this.overcharged = false;
        }
    }
}