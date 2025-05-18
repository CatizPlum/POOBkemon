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
     * Cuando es true, Donphan ha resistido un golpe que lo habría debilitado.
     */
    private boolean sturdyActivated;

    /**
     * Constructor de Donphan. Inicializa sus estadísticas base, tipo y movimientos.
     * Estadísticas destacadas:
     * - Alto ataque físico (372) y defensa (372)
     * - HP considerable (384)
     * - Velocidad baja (218)
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
     * Método para recibir daño. Implementa la habilidad Sturdy (Robustez).
     * @param amount Cantidad de daño recibido
     *
     * Efecto especial:
     * - Si el daño dejaría a Donphan con 1 HP o menos, activa Sturdy
     * - Previene el debilitamiento una vez por combate
     */
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        // Habilidad Sturdy: Sobrevive a un golpe que lo dejaría debilitado
        if (currentHP <= 1 && !sturdyActivated) {
            sturdyActivated = true;
            System.out.println("¡Donphan resiste el ataque con su habilidad Sturdy!");
        }
    }

    /**
     * Método para curar HP. Desactiva Sturdy si se recupera suficiente salud.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            sturdyActivated = false; // Sturdy se desactiva al recuperarse
        }
    }

    /**
     * Verifica si la habilidad Sturdy está actualmente activa.
     * @return true si Sturdy está activado, false en caso contrario
     */
    public boolean hasSturdyActivated() {
        return sturdyActivated;
    }

    /**
     * Ataque especial de Donphan: Rollout.
     * Un ataque de tipo Roca que aumenta su poder con cada uso consecutivo.
     */
    public void rollout() {
        System.out.println("¡Donphan lanza Rollout con gran poder!");
        // En implementación completa, aumentaría su poder con cada uso
    }

    /**
     * Movimiento estratégico: Stealth Rock.
     * Coloca rocas afiladas en el campo del oponente que dañan a los Pokémon
     * que entran en combate.
     */
    public void stealthRock() {
        System.out.println("¡Donphan coloca Stealth Rock en el campo!");
    }
}
