package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Donphan, un Pokémon de tipo Tierra.
 * Destaca por su gran defensa y ataque físico, además de su habilidad Sturdy
 * que le permite resistir un golpe que lo dejaría debilitado.
 */
public class Donphan extends AbstractPokemon implements Serializable {

    /**
     * Indica si la habilidad Sturdy (Robustez) está activada.
     */
    private boolean sturdyActivated;

    /**
     * Constructor de Donphan. Inicializa sus estadísticas base, tipo y movimientos.
     */
    public Donphan() {
        this.name = "Donphan";
        this.primaryType = Type.GROUND;
        this.secondaryType = null;
        this.maxHP = 384;
        this.currentHP = maxHP;
        this.attack = 372;
        this.defense = 372;
        this.specialAttack = 240;
        this.specialDefense = 240;
        this.speed = 218;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Donphan puede aprender por defecto.
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
     * Procesa el daño recibido e implementa la habilidad Sturdy.
     * @param amount Cantidad de daño a recibir (valor positivo)
     */
    @Override
    public void takeDamage(int amount) {
        int potentialHP = currentHP - amount;

        if (potentialHP <= 0 && !sturdyActivated) {
            sturdyActivated = true;
            currentHP = 1;
            System.out.println("¡Donphan resiste el ataque con su habilidad Sturdy!");
        } else {
            currentHP = Math.max(0, potentialHP);
        }
    }

    /**
     * Recupera HP y reinicia Sturdy si se cura suficiente.
     * @param amount Cantidad de HP a recuperar (valor positivo)
     */
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);

        if (currentHP > maxHP * 0.7) {
            sturdyActivated = false;
        }
    }

    /**
     * Crea una copia exacta de este Donphan.
     */
    @Override
    public Donphan clone() {
        Donphan cloned = (Donphan) super.clone();
        cloned.sturdyActivated = this.sturdyActivated;
        return cloned;
    }
}