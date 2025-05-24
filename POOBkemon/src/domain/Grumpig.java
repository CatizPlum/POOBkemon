package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Grumpig, un Pokémon de tipo Psíquico.
 * Conocido por sus poderes psíquicos que se potencian cuando baila.
 * Su efectividad en combate depende de su estado de ánimo y ritmo.
 */
public class Grumpig extends AbstractPokemon implements Serializable {

    /**
     * Indica si Grumpig está en estado de baile.
     */
    private boolean dancingMood;

    /**
     * Constructor de Grumpig. Inicializa sus estadísticas base, tipo y movimientos.
     */
    public Grumpig() {
        this.name = "Grumpig";
        this.primaryType = Type.PSYCHIC;
        this.secondaryType = null;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 207;
        this.defense = 251;
        this.specialAttack = 306;
        this.specialDefense = 350;
        this.speed = 284;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Grumpig puede aprender por defecto.
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
     * Método para recibir daño. Afecta el estado de ánimo de Grumpig.
     * @param amount Cantidad de daño recibido
     */
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            dancingMood = false;
            System.out.println("¡Grumpig pierde el ritmo tras el daño recibido!");
        }
    }

    /**
     * Método para curar HP. Restaura el estado de baile si HP >70%.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            dancingMood = true;
            System.out.println("¡Grumpig comienza a bailar de felicidad!");
        }
    }

    /**
     * Crea una copia exacta de este Grumpig.
     */
    @Override
    public Grumpig clone() {
        Grumpig cloned = (Grumpig) super.clone();
        return cloned;
    }
}
