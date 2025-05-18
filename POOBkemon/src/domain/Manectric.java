package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Manectric, un Pokémon de tipo Eléctrico.
 * Conocido por su increíble velocidad y capacidad de sobrecarga eléctrica.
 * Especializado en ataques eléctricos rápidos y potentes.
 */
public class Manectric extends AbstractPokemon {

    /**
     * Indica si Manectric está en estado de sobrecarga eléctrica.
     * Cuando es true:
     * - Aumenta temporalmente su velocidad
     * - Potencia su próximo ataque eléctrico
     */
    private boolean overcharged = true;

    /**
     * Constructor de Manectric. Inicializa sus estadísticas base, tipo y movimientos.
     * Características principales:
     * - Velocidad muy alta (339)
     * - Ataque especial considerable (339)
     * - Tipo Eléctrico puro
     * - Estado de sobrecarga inicialmente activado
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
     * Incluye movimientos rápidos y técnicas básicas.
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
     * Método para recibir daño con reducción del 5%.
     * @param amount Cantidad de daño recibido (se reduce un 5%)
     */
    @Override
    public void takeDamage(int amount) {
        int reducedAmount = (int) (amount * 0.95);
        currentHP = Math.max(0, currentHP - reducedAmount);
    }

    /**
     * Activa el estado de sobrecarga eléctrica.
     * Efectos:
     * - Aumenta la velocidad en un 30%
     * - Prepara un bonus para el próximo ataque eléctrico
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
     * Aplica el bonus de sobrecarga a un movimiento eléctrico.
     * @param move El movimiento a potenciar
     *
     * Efecto:
     * - Aumenta el poder en un 30% para movimientos eléctricos
     * - Consume el estado de sobrecarga
     * - Solo se aplica una vez por activación
     */
    public void applyOverchargeBonus(Move move) {
        if (overcharged && move.getType() == Type.ELECTRIC) {
            int boostedPower = (int)(move.getPower() * 1.3);
            move.setPower(boostedPower);
            System.out.println("El estado de sobrecarga potencia " + move.getName() +
                    " a " + boostedPower + " de poder.");
            this.overcharged = false; // el efecto solo se aplica una vez
        }
    }
}
