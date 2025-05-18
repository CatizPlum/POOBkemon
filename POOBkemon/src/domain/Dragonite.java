package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Dragonite, un poderoso Pokémon dragón/volador.
 * Conocido como el Pokémon de los mares, combina gran fuerza con la capacidad de volar
 * para rescatar personas perdidas en el mar.
 */
public class Dragonite extends AbstractPokemon {

    /**
     * Indica si Dragonite está actualmente en modo de vuelo.
     * En este estado:
     * - Tiene un 30% de probabilidad de esquivar ataques
     * - Su velocidad aumenta en un 50%
     * - Pierde el estado si recibe daño significativo (>20% HP máximo)
     */
    private boolean flyingMode;

    /**
     * Constructor de Dragonite. Inicializa sus estadísticas base, tipos y movimientos.
     * Estadísticas destacadas:
     * - Ataque físico excepcional (403)
     * - Defensas equilibradas (~320)
     * - Velocidad decente (284)
     * - HP alto (386)
     */
    public Dragonite() {
        this.name = "Dragonite";
        this.primaryType = Type.DRAGON;
        this.secondaryType = Type.FLYING;
        this.maxHP = 386;
        this.currentHP = 386;
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
     * Método para recibir daño con comportamiento especial en modo vuelo.
     * @param amount Cantidad de daño recibido
     *
     * Efectos especiales:
     * - 30% de probabilidad de esquivar en modo vuelo
     * - Sale del modo vuelo si recibe daño significativo (>20% HP máximo)
     */
    @Override
    public void takeDamage(int amount) {
        // 30% de probabilidad de esquivar en modo vuelo
        if (flyingMode && Math.random() > 0.7) {
            System.out.println("¡Dragonite evade el ataque gracias a su vuelo!");
            return;
        }

        currentHP = Math.max(0, currentHP - amount);

        // Pierde el vuelo por daño significativo
        if (flyingMode && amount > maxHP * 0.2) {
            flyingMode = false;
            this.speed = (int)(this.speed / 1.5); // Revierte el bonus de velocidad
            System.out.println("¡Dragonite pierde su estabilidad aérea y desciende!");
        }
    }

    // Métodos específicos de Dragonite

    /**
     * Verifica si Dragonite está en modo vuelo.
     * @return true si está volando, false en caso contrario
     */
    public boolean isFlying() {
        return flyingMode;
    }

    /**
     * Activa el modo vuelo de Dragonite si tiene suficiente salud (>30% HP).
     * Efectos:
     * - Aumenta la velocidad en 50%
     * - Permite esquivar ataques
     */
    public void takeFlightMode() {
        if (!flyingMode && currentHP > maxHP * 0.3) {
            flyingMode = true;
            this.speed = (int)(this.speed * 1.5);
            System.out.println("¡Dragonite levanta el vuelo! Su velocidad aumenta considerablemente.");
        } else if (flyingMode) {
            System.out.println("Dragonite ya está volando.");
        } else {
            System.out.println("Dragonite no tiene suficiente energía para volar.");
        }
    }

    /**
     * Movimiento especial: Dragon Dance.
     * Aumenta el ataque y velocidad en un 20%.
     * Representa la danza ritual de los dragones para aumentar su poder.
     */
    public void dragonDance() {
        this.attack = (int)(this.attack * 1.2);
        this.speed = (int)(this.speed * 1.2);
        System.out.println("¡Dragonite realiza una Danza Dragón! Su ataque y velocidad aumentan.");
    }

    /**
     * Habilidad única: Rescue Operation.
     * Representa la capacidad legendaria de Dragonite para rescatar personas.
     * En un sistema de juego completo, podría:
     * - Curar aliados
     * - Recuperar objetos
     * - Proporcionar efectos de apoyo
     */
    public void rescueOperation() {
        System.out.println("Dragonite busca a personas en peligro para rescatarlas.");
    }
}
