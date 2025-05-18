package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Gardevoir, un Pokémon de tipo Psíquico/Hada.
 * Conocido por su gran poder especial y conexión emocional con su entrenador.
 * Su efectividad en combate depende de su equilibrio emocional.
 */
public class Gardevoir extends AbstractPokemon implements Serializable {

    /**
     * Indica el estado emocional de Gardevoir.
     * Cuando es true:
     * - Gardevoir puede usar sus habilidades al máximo potencial
     * - Representa su estado de armonía mental
     *
     * Se pierde al recibir daño significativo (>25% HP máximo)
     * Se recupera al estar por encima del 70% de salud
     */
    private boolean emotionalBalance;

    /**
     * Constructor de Gardevoir. Inicializa sus estadísticas base, tipos y movimientos.
     * Estadísticas destacadas:
     * - Ataque especial excepcional (383)
     * - Defensa especial alta (361)
     * - HP moderado (340)
     * - Tipo dual Psíquico/Hada
     */
    public Gardevoir() {
        this.name = "Gardevoir";
        this.primaryType = Type.PSYCHIC;
        this.secondaryType = Type.FAIRY;
        this.maxHP = 340;
        this.currentHP = maxHP;
        this.attack = 251;
        this.defense = 251;
        this.specialAttack = 383;
        this.specialDefense = 361;
        this.speed = 284;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Gardevoir puede aprender por defecto.
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
     * Método para recibir daño. Afecta el equilibrio emocional de Gardevoir.
     * @param amount Cantidad de daño recibido
     *
     * Efecto secundario:
     * - Pierde equilibrio emocional si el daño >25% HP máximo
     */
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            emotionalBalance = false;
            System.out.println("¡Gardevoir está perturbada por el daño recibido!");
        }
    }

    /**
     * Método para curar HP. Restaura el equilibrio emocional si HP >70%.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            emotionalBalance = true;
            System.out.println("¡Gardevoir se siente en armonía!");
        }
    }

    /**
     * Verifica el estado emocional de Gardevoir.
     * @return true si está en equilibrio emocional, false en caso contrario
     */
    public boolean isEmotionallyBalanced() {
        return emotionalBalance;
    }

    /**
     * Ataque especial: Psychic Burst.
     * Solo funciona cuando Gardevoir está en equilibrio emocional.
     * Representa la explosión de poder psíquico cuando está centrada.
     */
    public void psychicBurst() {
        if (emotionalBalance) {
            System.out.println("¡Gardevoir desata una explosión psíquica devastadora!");
        } else {
            System.out.println("Gardevoir no está centrada para usar su poder.");
        }
    }
}
