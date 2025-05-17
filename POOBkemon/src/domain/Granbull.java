package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Granbull, un Pokémon de tipo Hada.
 * Aunque su apariencia es intimidante, es en realidad muy cariñoso.
 * Destaca por su enorme fuerza física y potentes mordiscos.
 */
public class Granbull extends AbstractPokemon {

    /**
     * Constructor de Granbull. Inicializa sus estadísticas base, tipo y movimientos.
     * Características principales:
     * - Ataque físico extremadamente alto (372)
     * - Defensa decente (273)
     * - HP considerable (384)
     * - Velocidad baja (207)
     * - Tipo Hada puro
     */
    public Granbull() {
        this.name = "Granbull";
        this.primaryType = Type.FAIRY;
        this.secondaryType = null;
        this.maxHP = 384;
        this.currentHP = maxHP;
        this.attack = 372;
        this.defense = 273;
        this.specialAttack = 240;
        this.specialDefense = 240;
        this.speed = 207;
        this.moves = new ArrayList<>();

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Granbull puede aprender por defecto.
     * Incluye movimientos físicos y técnicas básicas.
     */
    @Override
    protected void initializeMoves() {
        learnMove("Leer");
        learnMove("Quick Attack");
        learnMove("Double Team");
        learnMove("Knock Off");
        learnMove("Future Sight");
        learnMove("Water Pulse");
    }

    /**
     * Habilidad especial: Powerful Bite.
     * Potencia los movimientos de mordida (Bite/Crunch) en un 50%.
     *
     * Efecto:
     * - Aumenta temporalmente el poder de movimientos de mordida
     * - Muestra mensaje informando del aumento de poder
     *
     * Nota: Requiere que Granbull tenga aprendidos los movimientos Bite o Crunch
     * para que tenga efecto.
     */
    public void powerfulBite() {
        System.out.println("Granbull ataca con una mordida poderosa.");

        for (Move move : moves) {
            if (move.getName().equalsIgnoreCase("Bite") || move.getName().equalsIgnoreCase("Crunch")) {
                int originalPower = move.getPower();
                int boostedPower = (int)(originalPower * 1.5); // Aumenta el poder en un 50%
                System.out.println("El poder de " + move.getName() + " aumenta temporalmente de " +
                        originalPower + " a " + boostedPower + ".");
            }
        }
    }
}