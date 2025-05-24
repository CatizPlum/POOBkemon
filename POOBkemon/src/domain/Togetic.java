package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa al Pokémon Togetic, un Pokémon de tipo Hada/Volador.
 * Conocido como el Pokémon Felicidad, su poder varía según su estado emocional.
 * Es capaz de esparcir polvo de alegría y detectar bondad en otros seres.
 */
public class Togetic extends AbstractPokemon implements Serializable {

    /**
     * Indica el estado emocional de Togetic.
     */
    private boolean happyMood;

    /**
     * Constructor de Togetic. Inicializa sus estadísticas base, tipos y movimientos.
     */
    public Togetic() {
        this.name = "Togetic";
        this.primaryType = Type.FAIRY;
        this.secondaryType = Type.FLYING;
        this.maxHP = 314;
        this.currentHP = maxHP;
        this.attack = 196;
        this.defense = 295;
        this.specialAttack = 284;
        this.specialDefense = 339;
        this.speed = 196;
        this.happyMood = true;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Togetic puede aprender por defecto.
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
     * Método para recibir daño. Afecta su estado emocional.
     * @param amount Cantidad de daño recibido
     */
    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);
        if (amount > maxHP * 0.25) {
            happyMood = false;
            System.out.println("¡Togetic se entristece por el daño recibido!");
        }
    }

    /**
     * Método para curar HP. Restaura su felicidad si HP >70%.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);
        if (currentHP > maxHP * 0.7) {
            happyMood = true;
            System.out.println("¡Togetic se siente mejor y recupera su felicidad!");
        }
    }

    /**
     * Verifica si Togetic ha sido debilitado.
     * @return true si HP == 0, false en caso contrario
     */
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }

    /**
     * Crea una copia exacta de este Togetic.
     */
    @Override
    public Togetic clone() {
        Togetic cloned = (Togetic) super.clone();
        return cloned;
    }
}
