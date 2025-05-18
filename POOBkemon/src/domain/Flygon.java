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
     * Cuando es true:
     * - Flygon es inmune a movimientos de tipo Tierra
     * - Representa su estado de vuelo/flotación
     */
    private boolean levitateActivated;

    /**
     * Constructor de Flygon. Inicializa sus estadísticas base, tipos y movimientos.
     * Estadísticas destacadas:
     * - Ataque y velocidad equilibrados (328)
     * - Defensas moderadas (284)
     * - HP decente (364)
     * - Tipo dual Tierra/Dragón
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
    protected void initializeMoves() {
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
     * Verifica si Levitate está activado.
     * @return true si la habilidad está activa, false en caso contrario
     */
    public boolean hasLevitateActivated() {
        return levitateActivated;
    }

    // Métodos especiales de Flygon

    /**
     * Ataque característico: Dragon Claw.
     * Poderoso movimiento de tipo Dragón con alta precisión.
     */
    public void dragonClaw() {
        System.out.println("¡Flygon utiliza Dragon Claw con gran poder!");
    }

    /**
     * Movimiento de tipo Volador que permite evadir por un turno.
     * En el segundo turno, realiza un ataque aéreo.
     */
    public void fly() {
        System.out.println("¡Flygon se eleva en el aire para realizar un potente ataque Fly!");
    }

    /**
     * Movimiento táctico que permite cambiar de Pokémon después de atacar.
     * Ideal para estrategias de rotación.
     */
    public void uTurn() {
        System.out.println("¡Flygon usa U-turn y cambia de Pokémon rápidamente!");
    }

    /**
     * Activa manualmente la habilidad Levitate.
     * Hace a Flygon inmune a movimientos de tipo Tierra.
     */
    public void activateLevitate() {
        levitateActivated = true;
        System.out.println("¡Flygon activa su habilidad Levitate!");
    }

    /**
     * Desactiva manualmente la habilidad Levitate.
     * Permite que Flygon sea afectado por movimientos de tipo Tierra.
     */
    public void deactivateLevitate() {
        levitateActivated = false;
        System.out.println("¡Flygon desactiva su habilidad Levitate!");
    }
}
