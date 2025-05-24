package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Flygon, un Pokémon de tipo Tierra/Dragón.
 * Conocido como el "Espíritu del Desierto", combina atributos terrestres y aéreos,
 * incluyendo la habilidad Levitate que lo hace inmune a movimientos de tipo Tierra.
 */
public class Flygon extends AbstractPokemon implements Serializable {

    /**
     * Indica si la habilidad Levitate está activada.
     */
    private boolean levitateActivated;

    /**
     * Constructor de Flygon. Inicializa sus estadísticas base, tipos y movimientos.
     */
    public Flygon() {
        this.name = "Flygon";
        this.primaryType = Type.GROUND;
        this.secondaryType = Type.DRAGON;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 328;
        this.defense = 284;
        this.specialAttack = 284;
        this.specialDefense = 284;
        this.speed = 328;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Flygon puede aprender por defecto.
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
     * Método para recibir daño con inmunidad a movimientos de tipo Tierra cuando Levitate está activo.
     * @param amount Cantidad de daño recibido
     */
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        // Verifica inmunidad a movimientos de tipo Tierra
        if (this.primaryType == Type.GROUND || (this.secondaryType != null && this.secondaryType == Type.GROUND)) {
            if (levitateActivated) {
                System.out.println("¡Flygon es inmune a los movimientos de tipo Tierra gracias a su habilidad Levitate!");
                return;
            }
        }
    }

    /**
     * Método para curar HP. Reactiva Levitate si la salud supera el 70%.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            levitateActivated = true;
        }
    }

    /**
     * Crea una copia exacta de este Flygon.
     */
    @Override
    public Flygon clone() {
        Flygon cloned = (Flygon) super.clone();
        return cloned;
    }
}
