package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Dragonite, un poderoso Pokémon dragón/volador.
 * Conocido como el Pokémon de los mares, combina gran fuerza con la capacidad de volar
 * para rescatar personas perdidas en el mar.
 */
public class Dragonite extends AbstractPokemon implements Serializable {

    /**
     * Indica si Dragonite está actualmente en modo de vuelo.
     */
    private boolean flyingMode;

    /**
     * Constructor de Dragonite. Inicializa sus estadísticas base, tipos y movimientos.
     */
    public Dragonite() {
        this.name = "Dragonite";
        this.primaryType = Type.DRAGON;
        this.secondaryType = Type.FLYING;
        this.maxHP = 386;
        this.currentHP = maxHP;
        this.attack = 403;
        this.defense = 317;
        this.specialAttack = 328;
        this.specialDefense = 328;
        this.speed = 284;
        this.flyingMode = false;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Dragonite puede aprender por defecto.
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
     * Procesa el daño recibido con mecánicas especiales de vuelo.
     * @param amount Cantidad de daño a recibir (valor positivo)
     */
    @Override
    public void takeDamage(int amount) {
        if (flyingMode && Math.random() < 0.3) {
            System.out.println("¡Dragonite evade el ataque gracias a su vuelo!");
            return;
        }

        currentHP = Math.max(0, currentHP - amount);

        if (flyingMode && amount > maxHP * 0.2) {
            exitFlightMode();
        }
    }


    /**
     * Desactiva el modo vuelo y revierte sus beneficios.
     * Se llama automáticamente al recibir daño significativo.
     */
    private void exitFlightMode() {
        flyingMode = false;
        this.speed = (int)(this.speed / 1.5);
        System.out.println("¡Dragonite pierde su estabilidad aérea y desciende!");
    }

    /**
     * Verifica el estado actual de vuelo.
     * @return true si está en modo vuelo, false en caso contrario
     */
    public boolean isFlying() {
        return flyingMode;
    }

    /**
     * Crea una copia exacta de este Dragonite.
     */
    @Override
    public Dragonite clone() {
        Dragonite cloned = (Dragonite) super.clone();
        cloned.flyingMode = this.flyingMode;
        return cloned;
    }
}