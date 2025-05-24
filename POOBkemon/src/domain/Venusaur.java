package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Venusaur, la evolución final de Bulbasaur.
 * Pokémon de tipo Planta/Veneno conocido por la gran flor en su espalda.
 * Sus habilidades giran en torno a la fotosíntesis y el uso de esporas.
 */
public class Venusaur extends AbstractPokemon implements Serializable {

    /**
     * Estado de la flor de Venusaur.
     */
    private boolean flowerBlossomed;

    /**
     * Constructor de Venusaur. Inicializa sus estadísticas base, tipo y movimientos.
     */
    public Venusaur() {
        this.name = "Venusaur";
        this.primaryType = Type.PLANT;
        this.secondaryType = Type.POISON;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 289;
        this.defense = 291;
        this.specialAttack = 328;
        this.specialDefense = 328;
        this.speed = 284;
        this.flowerBlossomed = true;
        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Venusaur puede aprender por defecto.
     * Incluye movimientos de tipo Planta, Veneno y técnicas variadas.
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
     * Método para recibir daño. Si el HP baja del 30%, la flor pierde su esplendor.
     * @param amount Cantidad de daño recibido
     */
    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);

        if (currentHP < maxHP * 0.3) {
            flowerBlossomed = false;
            System.out.println("¡La flor de Venusaur pierde su esplendor!");
        }
    }

    /**
     * Método para curar HP. Si el HP supera el 60%, la flor recupera su esplendor.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);

        if (currentHP > maxHP * 0.6) {
            flowerBlossomed = true;
            System.out.println("¡La flor de Venusaur recupera su esplendor!");
        }
    }

    /**
     * Verifica si Venusaur ha sido debilitado.
     * @return true si HP == 0, false en caso contrario
     */
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }

    /**
     * Crea una copia exacta de este Venusaur.
     */
    @Override
    public Venusaur clone() {
        Venusaur cloned = (Venusaur) super.clone();
        return cloned;
    }
}