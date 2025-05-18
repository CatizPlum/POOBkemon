package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa al Pokémon Togetic, un Pokémon de tipo Hada/Volador.
 * Conocido como el Pokémon Felicidad, su poder varía según su estado emocional.
 * Es capaz de esparcir polvo de alegría y detectar bondad en otros seres.
 */
public class Togetic extends AbstractPokemon {

    /**
     * Indica el estado emocional de Togetic.
     * Cuando es true:
     * - Puede usar movimientos especiales como Spread Joy Powder
     * - Sus habilidades defensivas son más efectivas
     * - Representa su conexión con la energía positiva
     */
    private boolean happyMood;

    /**
     * Constructor de Togetic. Inicializa sus estadísticas base, tipos y movimientos.
     * Características principales:
     * - Defensa especial muy alta (339)
     * - Defensa física considerable (295)
     * - Tipo dual Hada/Volador con múltiples resistencias
     * - HP moderado (314)
     * - Felicidad inicial activada
     */
    public Togetic() {
        this.name = "Togetic";
        this.primaryType = Type.FAIRY;
        this.secondaryType = Type.FLYING;
        this.maxHP = 314;
        this.currentHP = maxHP;
        this.attack = 196;
        this.defense = 295;
        this.specialAttack = 284;
        this.specialDefense = 339;
        this.speed = 196;
        this.happyMood = true;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Togetic puede aprender por defecto.
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
     * Método para recibir daño. Afecta su estado emocional.
     * @param amount Cantidad de daño recibido
     *
     * Efecto secundario:
     * - Pierde felicidad si recibe daño significativo (>25% HP máximo)
     */
    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);
        if (amount > maxHP * 0.25) {
            happyMood = false;
            System.out.println("¡Togetic se entristece por el daño recibido!");
        }
    }

    /**
     * Método para curar HP. Restaura su felicidad si HP >70%.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);
        if (currentHP > maxHP * 0.7) {
            happyMood = true;
            System.out.println("¡Togetic se siente mejor y recupera su felicidad!");
        }
    }

    /**
     * Verifica si Togetic ha sido debilitado.
     * @return true si HP == 0, false en caso contrario
     */
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }

    // === Métodos Específicos ===

    /**
     * Verifica el estado emocional de Togetic.
     * @return true si está feliz, false en caso contrario
     */
    public boolean isHappy() {
        return happyMood;
    }

    /**
     * Habilidad especial: Spread Joy Powder.
     * Solo funciona cuando Togetic está feliz.
     * Efectos:
     * - Mejora el ánimo del equipo
     * - Aumenta defensas en 20%
     * - Puede curar estados negativos de aliados
     */
    public void spreadJoyPowder() {
        if (happyMood) {
            System.out.println("¡Togetic esparce polvillo de alegría, mejorando el ánimo de todos!");
            this.defense = (int)(this.defense * 1.2);
            this.specialDefense = (int)(this.specialDefense * 1.2);
        } else {
            System.out.println("Togetic está demasiado triste para esparcir polvillo de alegría.");
        }
    }

    /**
     * Habilidad única: Detect Kindness.
     * Detecta la bondad en los alrededores, con efectos variables según su estado emocional.
     * En combate podría:
     * - Revelar intenciones del oponente
     * - Aumentar precisión de movimientos
     * - Reducir daño de ataques de oponentes "malvados"
     */
    public void detectKindness() {
        System.out.println("Togetic busca personas de buen corazón en los alrededores.");
        if (happyMood) {
            System.out.println("¡Togetic ha detectado un corazón puro cerca!");
        }
    }
}