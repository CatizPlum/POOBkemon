package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Zangoose, una implementación concreta de AbstractPokemon.
 * Zangoose es un Pokémon de tipo Normal conocido por sus garras afiladas y su temperamento feroz
 * que se activa cuando recibe daño significativo.
 */
public class Zangoose extends AbstractPokemon  implements Serializable {

    private boolean clawTemper;

    /**
     * Constructor de Zangoose.
     * Inicializa sus estadísticas base, movimientos y estado de temperamento.
     */
    public Zangoose() {
        this.name = "Zangoose";
        this.primaryType = Type.NORMAL;
        this.secondaryType = null;
        this.maxHP = 350;
        this.currentHP = maxHP;
        this.attack = 361;
        this.defense = 240;
        this.specialAttack = 240;
        this.specialDefense = 240;
        this.speed = 306;
        this.clawTemper = false;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Zangoose puede aprender.
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
     * Reduce los puntos de salud del Pokémon.
     * Si el daño recibido es mayor al 25% de su salud máxima, activa su furia.
     * @param amount Cantidad de daño a recibir
     */
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            clawTemper = true;
            System.out.println("¡Zangoose está enfurecido por el daño recibido!");
        }
    }

    /**
     * Restaura puntos de salud al Pokémon.
     * Si la salud supera el 70%, se calma y pierde su furia.
     * @param amount Cantidad de salud a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            clawTemper = false;
            System.out.println("Zangoose se calma un poco al sentirse mejor.");
        }
    }

    /**
     * Crea una copia exacta de este Zangoose.
     */
    @Override
    public Zangoose clone() {
        Zangoose cloned = (Zangoose) super.clone();
        return cloned;
    }
}
