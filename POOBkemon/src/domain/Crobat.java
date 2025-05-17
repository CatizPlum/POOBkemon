package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Crobat, la evolución final de Zubat.
 * Pokémon de tipo Veneno/Volador conocido por su excepcional velocidad.
 * Especializado en ataques rápidos y movimientos tácticos que aprovechan su agilidad.
 */
public class Crobat extends AbstractPokemon {

    /**
     * Constructor de Crobat. Inicializa sus estadísticas base, tipos y movimientos.
     * Características principales:
     * - Velocidad extremadamente alta (394)
     * - Estadísticas defensivas moderadas
     * - Movimientos que aprovechan su velocidad y tipo dual
     */
    public Crobat() {
        this.name = "Crobat";
        this.primaryType = Type.POISON;
        this.secondaryType = Type.FLYING;
        this.maxHP = 374;  // HP ligeramente menor que Seviper
        this.currentHP = maxHP;
        this.attack = 306;  // Ataque físico más bajo
        this.defense = 284;  // Defensa más baja
        this.specialAttack = 262;  // Ataque especial más bajo
        this.specialDefense = 284;  // Defensa especial más baja
        this.speed = 394;  // Velocidad mucho mayor (Crobat es uno de los Pokémon más rápidos)

        this.moves = new ArrayList<>();

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Crobat puede aprender por defecto.
     * Movimientos enfocados en su tipo Veneno/Volador y ataques rápidos.
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
     * Cura a Crobat. Sobreescribe el método base sin modificaciones adicionales.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
    }

    // Métodos especiales de Crobat

    /**
     * Ataque característico de Crobat: Cross Poison.
     * Tiene alta probabilidad de golpe crítico gracias a la velocidad de Crobat.
     */
    public void crossPoison() {
        System.out.println("¡Crobat usa Cross Poison con alta probabilidad de golpe crítico!");
    }

    /**
     * Ataque aéreo que puede hacer retroceder al oponente.
     * Aprovecha el tipo Volador y la velocidad de Crobat.
     */
    public void airSlash() {
        System.out.println("¡Crobat corta el aire con sus alas! Puede hacer retroceder al oponente.");
    }

    /**
     * Ataque táctico que permite cambiar de Pokémon después de atacar.
     * Ideal para estrategias de rotación de equipo.
     */
    public void uTurn() {
        System.out.println("¡Crobat usa U-turn, atacando y luego cambiando de Pokémon!");
    }

    /**
     * Ataque poderoso con efecto de retroceso.
     * Representa el estilo de ataque de alto riesgo/recompensa de Crobat.
     */
    public void braveBird() {
        System.out.println("¡Crobat se lanza en picado con Brave Bird! Causa daño pero también recibe retroceso.");
    }
}