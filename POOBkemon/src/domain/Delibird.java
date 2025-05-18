package domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que representa al Pokémon Delibird, un Pokémon de tipo Hielo/Volador.
 * Conocido por su habilidad única de entregar regalos con efectos aleatorios.
 * Es un Pokémon con estadísticas modestas pero con una habilidad especial impredecible.
 */
public class Delibird extends AbstractPokemon {

    /**
     * Constructor de Delibird. Inicializa sus estadísticas base, tipos y movimientos.
     * Características principales:
     * - Estadísticas generales bajas
     * - Tipo dual Hielo/Volador
     * - Movimientos variados que incluyen ataques rápidos y técnicas de apoyo
     */
    public Delibird() {
        this.name = "Delibird";
        this.primaryType = Type.ICE;
        this.secondaryType = Type.FLYING;
        this.maxHP = 294;
        this.currentHP = maxHP;
        this.attack = 229;
        this.defense = 207;
        this.specialAttack = 251;
        this.specialDefense = 207;
        this.speed = 273;
        this.moves = new ArrayList<>();

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Delibird puede aprender por defecto.
     * Incluye movimientos de diversos tipos para versatilidad en combate.
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
     * Habilidad especial: Entrega un regalo con efectos aleatorios al oponente.
     * Puede tener tres resultados posibles con igual probabilidad:
     * 1. Daño (50 puntos)
     * 2. Curación (30 puntos)
     * 3. Mejora de ataque (+10 puntos)
     *
     * @param opponent El Pokémon oponente que recibirá el efecto
     */
    public void surpriseGift(Pokemon opponent) {
        Random rand = new Random();
        int effect = rand.nextInt(3);  // Genera un valor aleatorio entre 0 y 2

        switch (effect) {
            case 0:
                System.out.println("Delibird entrega un regalo explosivo al oponente.");
                opponent.takeDamage(50);  // Inflige daño
                break;

            case 1:
                System.out.println("Delibird entrega un regalo curativo al oponente.");
                opponent.heal(30);  // Cura
                break;

            case 2:
                System.out.println("Delibird entrega un regalo que aumenta las estadísticas del oponente.");
                if (opponent instanceof AbstractPokemon) {
                    ((AbstractPokemon) opponent).incrementAttack(10);  // Aumenta ataque
                }
                break;
        }
    }
}