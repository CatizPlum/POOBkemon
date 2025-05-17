package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon legendario Moltres, de tipo Fuego/Volador.
 * Conocido como el Pokémon Llama, es una de las aves legendarias de Kanto.
 * Destaca por su poder ígneo y habilidades de vuelo majestuoso.
 */
public class Moltres extends AbstractPokemon {

    /**
     * Constructor de Moltres. Inicializa sus estadísticas base como Pokémon legendario.
     * Características principales:
     * - Ataque especial muy alto (350)
     * - Defensas equilibradas (280)
     * - HP legendario (380)
     * - Tipo dual Fuego/Volador
     * - Velocidad decente (290)
     */
    public Moltres() {
        this.name = "Moltres";
        this.primaryType = Type.FIRE;
        this.secondaryType = Type.FLYING;
        this.maxHP = 380;
        this.currentHP = maxHP;
        this.attack = 290;
        this.defense = 280;
        this.specialAttack = 350;
        this.specialDefense = 280;
        this.speed = 290;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Moltres puede aprender por defecto.
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
     * Método para recibir daño con resistencia a tipo Fuego.
     * @param amount Cantidad de daño recibido
     */
    @Override
    public void takeDamage(int amount) {
        // En implementación completa, reducir daño si es de tipo Fuego
        super.takeDamage(amount);
    }

    /**
     * Método para curar HP.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
    }

    // Métodos especiales de Moltres

    /**
     * Ataque característico: Fire Blast.
     * Movimiento de tipo Fuego con alta potencia y posibilidad de quemar.
     * Potencia: 110 | Precisión: 85% | Probabilidad de quemar: 10%
     */
    public void fireBlast() {
        System.out.println("¡Moltres lanza un devastador Fire Blast! ¡Puede causar quemaduras!");
    }

    /**
     * Ataque único: Burn Up.
     * Libera todo su poder ígneo pero pierde temporalmente el tipo Fuego.
     * Potencia: 130 | Elimina tipo Fuego hasta que descanse
     */
    public void burnUp() {
        System.out.println("¡Moltres usa Burn Up, liberando todo su poder ígneo! (Pierde tipo Fuego temporalmente)");
    }

    /**
     * Técnica solar: Solar Beam.
     * Carga energía solar para un potente ataque de tipo Planta.
     * Potencia: 120 | Requiere carga (excepto bajo luz solar intensa)
     */
    public void solarBeam() {
        System.out.println("¡Moltres absorbe energía solar para lanzar un poderoso Solar Beam!");
    }

    /**
     * Movimiento ancestral: Ancient Power.
     * Ataque de tipo Roca con posibilidad de aumentar todas las estadísticas.
     * Potencia: 60 | 10% de aumentar ataque/defensa/velocidad en 1 nivel
     */
    public void ancientPower() {
        System.out.println("¡Moltres invoca poder ancestral! Puede aumentar todas sus estadísticas.");
    }
}