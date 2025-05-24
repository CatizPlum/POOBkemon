package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Solrock, un Pokémon de tipo Roca/Psíquico.
 * Conocido como el Pokémon Meteorito, absorbe y almacena energía solar para potenciar sus ataques.
 * Su efectividad en combate depende de su nivel de energía solar acumulada.
 */
public class Solrock extends AbstractPokemon implements Serializable {

    /**
     * Indica si Solrock tiene energía solar acumulada.
     */
    private boolean solarEnergy;

    /**
     * Constructor de Solrock. Inicializa sus estadísticas base, tipos y movimientos.
     */
    public Solrock() {
        this.name = "Solrock";
        this.primaryType = Type.ROCK;
        this.secondaryType = Type.PSYCHIC;
        this.maxHP = 344;
        this.currentHP = maxHP;
        this.attack = 317;
        this.defense = 295;
        this.specialAttack = 229;
        this.specialDefense = 251;
        this.speed = 262;
        this.solarEnergy = true; // Comienza con energía solar

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Solrock puede aprender por defecto.
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
     * Método para recibir daño. Afecta su reserva de energía solar.
     * @param amount Cantidad de daño recibido
     */
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            solarEnergy = false;
            System.out.println("¡Solrock pierde su energía solar por el daño recibido!");
        }
    }

    /**
     * Método para curar HP. Restaura energía solar si HP >70%.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            solarEnergy = true;
            System.out.println("¡Solrock recarga su energía solar y se siente revitalizado!");
        }
    }

    /**
     * Crea una copia exacta de este Solrock.
     */
    @Override
    public Solrock clone() {
        Solrock cloned = (Solrock) super.clone();
        return cloned;
    }
}
