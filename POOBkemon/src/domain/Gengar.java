package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa al Pokémon Gengar, un Pokémon de tipo Fantasma/Veneno.
 * Conocido como el Pokémon Sombra, puede desvanecerse en las tinieblas y
 * absorber la energía vital de sus oponentes.
 */
public class Gengar extends AbstractPokemon implements Serializable {

    /**
     * Indica si Gengar está en su Forma Sombra.
     */
    private boolean shadowForm;

    /**
     * Constructor de Gengar. Inicializa sus estadísticas base, tipos y movimientos.
     */
    public Gengar() {
        this.name = "Gengar";
        this.primaryType = Type.GHOST;
        this.secondaryType = Type.POISON;
        this.maxHP = 324;
        this.currentHP = maxHP;
        this.attack = 251;
        this.defense = 240;
        this.specialAttack = 394;
        this.specialDefense = 273;
        this.speed = 350;
        this.shadowForm = false;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Gengar puede aprender por defecto.
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
     * Método para recibir daño con reducción en Forma Sombra.
     * @param amount Cantidad de daño recibido (se reduce un 30% en Forma Sombra)
     */
    @Override
    public void takeDamage(int amount) {
        // Reducción de daño en Forma Sombra
        if (shadowForm && amount > 0) {
            amount = (int)(amount * 0.7);
            System.out.println("¡La forma sombra de Gengar reduce el daño recibido!");
        }

        currentHP = Math.max(0, currentHP - amount);
    }

    /**
     * Método para curar HP. Desactiva Forma Sombra al curarse completamente.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);

        // Desactivación al curarse completamente
        if (currentHP == maxHP) {
            shadowForm = false;
        }
    }

    /**
     * Verifica si Gengar ha sido debilitado.
     * @return true si HP == 0, false en caso contrario
     */
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }

    /**
     * Crea una copia exacta de este Gengar.
     */
    @Override
    public Gengar clone() {
        Gengar cloned = (Gengar) super.clone();
        return cloned;
    }
}
