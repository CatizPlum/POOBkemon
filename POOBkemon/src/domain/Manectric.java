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
    public void initializeMoves() {
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
     * Crea una copia exacta de este Manectric.
     */
    @Override
    public Manectric clone() {
        Manectric cloned = (Manectric) super.clone();
        return cloned;
    }
}