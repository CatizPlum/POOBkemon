package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Crobat, la evolución final de Zubat.
 * Pokémon de tipo Veneno/Volador conocido por su excepcional velocidad.
 * Especializado en ataques rápidos y movimientos tácticos que aprovechan su agilidad.
 */
public class Crobat extends AbstractPokemon implements Serializable {

    /**
     * Constructor de Crobat. Inicializa sus estadísticas base, tipos y movimientos.
     */
    public Crobat() {
        this.name = "Crobat";
        this.primaryType = Type.POISON;
        this.secondaryType = Type.FLYING;
        this.maxHP = 374;
        this.currentHP = maxHP;
        this.attack = 306;
        this.defense = 284;
        this.specialAttack = 262;
        this.specialDefense = 284;
        this.speed = 394;

        this.moves = new ArrayList<>();

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Crobat puede aprender por defecto.
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
     * Cura a Crobat. Sobreescribe el método base sin modificaciones adicionales.
     * @param amount Cantidad de HP a recuperar (debe ser un valor positivo)
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
    }


    /**
     * Crea y devuelve una copia exacta de este objeto Crobat.
     */
    @Override
    public Crobat clone() {
        Crobat cloned = (Crobat) super.clone();
        return cloned;
    }
}