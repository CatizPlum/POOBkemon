package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Grumpig, un Pokémon de tipo Psíquico.
 * Conocido por sus poderes psíquicos que se potencian cuando baila.
 * Su efectividad en combate depende de su estado de ánimo y ritmo.
 */
public class Grumpig extends AbstractPokemon {

    /**
     * Indica si Grumpig está en estado de baile.
     * Cuando es true:
     * - Sus ataques psíquicos son más potentes
     * - Se encuentra en un estado de concentración rítmica
     *
     * Se pierde al recibir daño significativo (>25% HP máximo)
     * Se recupera al estar por encima del 70% de salud
     */
    private boolean dancingMood;

    /**
     * Constructor de Grumpig. Inicializa sus estadísticas base, tipo y movimientos.
     * Estadísticas destacadas:
     * - Defensa especial muy alta (350)
     * - Ataque especial considerable (306)
     * - Velocidad decente (284)
     * - Tipo Psíquico puro
     */
    public Grumpig() {
        this.name = "Grumpig";
        this.primaryType = Type.PSYCHIC;
        this.secondaryType = null;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 207;
        this.defense = 251;
        this.specialAttack = 306;
        this.specialDefense = 350;
        this.speed = 284;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Grumpig puede aprender por defecto.
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
     * Método para recibir daño. Afecta el estado de ánimo de Grumpig.
     * @param amount Cantidad de daño recibido
     *
     * Efecto secundario:
     * - Pierde el estado de baile si el daño >25% HP máximo
     */
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            dancingMood = false;
            System.out.println("¡Grumpig pierde el ritmo tras el daño recibido!");
        }
    }

    /**
     * Método para curar HP. Restaura el estado de baile si HP >70%.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            dancingMood = true;
            System.out.println("¡Grumpig comienza a bailar de felicidad!");
        }
    }

    /**
     * Verifica el estado de ánimo de Grumpig.
     * @return true si está bailando, false en caso contrario
     */
    public boolean isDancing() {
        return dancingMood;
    }

    /**
     * Ataque especial: Psy Pulse.
     * Solo funciona cuando Grumpig está bailando.
     * Representa la liberación de energía psíquica al ritmo de su danza.
     */
    public void psyPulse() {
        if (dancingMood) {
            System.out.println("¡Grumpig libera una potente onda psíquica al ritmo de su danza!");
        } else {
            System.out.println("Grumpig no está inspirado para bailar y atacar.");
        }
    }
}
