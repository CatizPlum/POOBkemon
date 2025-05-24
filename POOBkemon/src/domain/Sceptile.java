package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Sceptile, la evolución final de Treecko.
 * Pokémon de tipo Planta conocido por su velocidad y hojas afiladas como cuchillas.
 * Su efectividad en combate depende del estado de sus hojas.
 */
public class Sceptile extends AbstractPokemon implements Serializable {

    /**
     * Indica si Sceptile tiene sus hojas afiladas.
     */
    private boolean bladesSharpened;

    /**
     * Constructor de Sceptile. Inicializa sus estadísticas base, tipo y movimientos.
     */
    public Sceptile() {
        this.name = "Sceptile";
        this.primaryType = Type.PLANT;
        this.secondaryType = null;
        this.maxHP = 310;
        this.currentHP = maxHP;
        this.attack = 265;
        this.defense = 215;
        this.specialAttack = 300;
        this.specialDefense = 210;
        this.speed = 350;
        this.bladesSharpened = true;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Sceptile puede aprender por defecto.
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
     * Método para recibir daño. Afecta el estado de sus hojas.
     * @param amount Cantidad de daño recibido
     */
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            bladesSharpened = false;
            System.out.println("¡Sceptile pierde su filo tras recibir daño!");
        }
    }

    /**
     * Método para curar HP. Restaura el filo de sus hojas si HP >70%.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            bladesSharpened = true;
            System.out.println("¡Sceptile afila sus hojas mientras se recupera!");
        }
    }

    /**
     * Crea una copia exacta de este Sceptile.
     */
    @Override
    public Sceptile clone() {
        Sceptile cloned = (Sceptile) super.clone();
        return cloned;
    }
}
