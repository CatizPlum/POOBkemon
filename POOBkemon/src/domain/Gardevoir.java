package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Gardevoir, un Pokémon de tipo Psíquico/Hada.
 * Conocido por su gran poder especial y conexión emocional con su entrenador.
 * Su efectividad en combate depende de su equilibrio emocional.
 */
public class Gardevoir extends AbstractPokemon implements Serializable {

    /**
     * Indica el estado emocional de Gardevoir.
     */
    private boolean emotionalBalance;

    /**
     * Constructor de Gardevoir. Inicializa sus estadísticas base, tipos y movimientos.
     */
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
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Gardevoir puede aprender por defecto.
     * Incluye movimientos de varios tipos para versatilidad en combate.
     */
    @Override
    public void initializeMoves() {
        learnMove("Cross Poison");
        learnMove("Air Slash");
        learnMove("Bite");
        learnMove("Mean Look");
        learnMove("Screech");
        learnMove("Absorb");
    }

    /**
     * Método para recibir daño. Afecta el equilibrio emocional de Gardevoir.
     * @param amount Cantidad de daño recibido
     */
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            emotionalBalance = false;
            System.out.println("¡Gardevoir está perturbada por el daño recibido!");
        }
    }

    /**
     * Método para curar HP. Restaura el equilibrio emocional si HP >70%.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            emotionalBalance = true;
            System.out.println("¡Gardevoir se siente en armonía!");
        }
    }

    /**
     * Crea una copia exacta de este Gardevoir.
     */
    @Override
    public Gardevoir clone() {
        Gardevoir cloned = (Gardevoir) super.clone();
        return cloned;
    }
}
