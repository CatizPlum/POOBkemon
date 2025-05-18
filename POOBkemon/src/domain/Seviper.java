package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Seviper, un Pokémon de tipo Veneno/Bicho.
 * Conocido como el Pokémon Serpiente, destaca por su potente veneno y ataques físicos.
 * Rival natural de Zangoose, con quien mantiene una eterna enemistad.
 */
public class Seviper extends AbstractPokemon implements Serializable {

    /**
     * Constructor de Seviper. Inicializa sus estadísticas base, tipos y movimientos.
     * Características principales:
     * - Ataque físico y especial equilibrados (328)
     * - Tipo dual Veneno/Bicho
     * - HP decente (350)
     * - Velocidad moderada (251)
     * - Defensas moderadas (240)
     */
    public Seviper() {
        this.name = "Seviper";
        this.primaryType = Type.POISON;
        this.secondaryType = Type.BUG;
        this.maxHP = 350;
        this.currentHP = maxHP;
        this.attack = 328;
        this.defense = 240;
        this.specialAttack = 328;
        this.specialDefense = 240;
        this.speed = 251;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Seviper puede aprender por defecto.
     * Incluye movimientos venenosos y técnicas de combate engañosas.
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
     * Método para recibir daño sin modificadores especiales.
     * @param amount Cantidad de daño recibido
     */
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
    }

    /**
     * Método para curar HP sin habilidades especiales.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
    }

    // === Movimientos Especiales ===

    /**
     * Ataque característico: Sludge Bomb.
     * Movimiento de tipo Veneno con alta potencia y posibilidad de envenenar.
     * Potencia: 90 | Probabilidad de envenenar: 30%
     */
    public void sludgeBomb() {
        System.out.println("¡Seviper lanza Sludge Bomb, cubriendo al oponente en veneno!");
    }

    /**
     * Ataque físico: Bug Bite.
     * Muerde al oponente y consume su baya (si la tiene equipada).
     * Potencia: 60 | Efecto adicional: Roba efecto de baya del oponente
     */
    public void bugBite() {
        System.out.println("¡Seviper usa Bug Bite, mordiendo con sus poderosas mandíbulas!");
    }

    /**
     * Movimiento de estado: Toxic.
     * Envenena gravemente al oponente con daño incremental.
     * Precisión: 90% | Daño inicial: 1/16 HP, incrementando cada turno
     */
    public void toxic() {
        System.out.println("¡Seviper usa Toxic y envenena al oponente!");
    }

    /**
     * Técnica evasiva: Double Team.
     * Aumenta la evasión creando ilusiones de sí mismo.
     * Efecto: Aumenta evasión en 1 nivel (multiplica precisión enemiga por 0.75)
     */
    public void doubleTeam() {
        System.out.println("¡Seviper usa Double Team, aumentando su evasión!");
    }
}
