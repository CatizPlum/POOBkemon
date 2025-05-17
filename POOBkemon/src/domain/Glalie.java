package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Glalie, un Pokémon de tipo Hielo puro.
 * Conocido como el Pokémon Cara de Hielo, puede congelar instantáneamente el aire a su alrededor.
 * Tiene estadísticas perfectamente balanceadas que reflejan su naturaleza simétrica.
 */
public class Glalie extends AbstractPokemon {

    /**
     * Constructor de Glalie. Inicializa sus estadísticas base, tipo y movimientos.
     * Características únicas:
     * - Todas sus estadísticas (excepto HP) tienen el mismo valor (284)
     * - Representa la simetría perfecta de su forma esférica
     * - Tipo Hielo puro con movimientos versátiles
     */
    public Glalie() {
        this.name = "Glalie";
        this.primaryType = Type.ICE;
        this.secondaryType = null;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 284;
        this.defense = 284;
        this.specialAttack = 284;
        this.specialDefense = 284;
        this.speed = 284;
        this.moves = new ArrayList<>();

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Glalie puede aprender por defecto.
     * Incluye movimientos de varios tipos para complementar su tipo Hielo.
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
     * Habilidad especial: Instant Freeze.
     * Congela instantáneamente la humedad del aire alrededor de Glalie.
     * En un sistema de combate completo, podría:
     * - Tener probabilidad de congelar al oponente
     * - Crear un campo de hielo que afecte el terreno
     * - Mejorar sus propios movimientos de tipo Hielo
     */
    public void instantFreeze() {
        System.out.println("Glalie congela instantáneamente la humedad del aire.");
        // Implementación básica - puede expandirse para efectos de juego reales
    }
}