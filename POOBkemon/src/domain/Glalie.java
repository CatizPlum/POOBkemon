package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Glalie, la evolución de Snorunt.
 * Es un Pokémon de tipo Hielo (Ice) conocido por su cabeza de hielo macizo y
 * su capacidad para congelar instantáneamente el aire a su alrededor.
 * Sus habilidades giran alrededor del control del hielo y ataques sorpresivos.
 */
public class Glalie extends AbstractPokemon implements Serializable {

    /**
     * Constructor de Glalie. Inicializa sus estadísticas base, tipo y movimientos.
     * Estadísticas destacadas:
     * - Estadísticas balanceadas (todas a 284 excepto HP)
     * - Tipo Hielo puro
     * - HP moderadamente alto (364)
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
     * Incluye movimientos de tipo Hielo y técnicas variadas que reflejan su naturaleza versátil.
     */
    @Override
    public void initializeMoves() {
        learnMove("Leer");         // Movimiento de estado que reduce defensa
        learnMove("Quick Attack"); // Ataque prioritario
        learnMove("Double Team");  // Aumenta evasión
        learnMove("Knock Off");    // Ataque oscuro que remueve items
        learnMove("Future Sight"); // Ataque psíquico diferido
        learnMove("Water Pulse");  // Ataque agua que puede confundir
    }

    /**
     * Habilidad especial: Congelación Instantánea
     * Glalie congela la humedad del aire alrededor, creando efectos potenciales en el combate.
     * (Implementación actual muestra mensaje descriptivo, puede extenderse para efectos de juego)
     */
    public void instantFreeze() {
        System.out.println("Glalie congela instantáneamente la humedad del aire.");
        // Potencial extensión:
        // - Podría reducir velocidad del oponente
        // - Añadir chance de congelación
        // - Crear terreno helado para beneficios defensivos
    }

    @Override
    public Glalie clone() {
        Glalie cloned = (Glalie) super.clone();
        return cloned;
    }
}