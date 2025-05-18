package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa al Pokémon Gengar, un Pokémon de tipo Fantasma/Veneno.
 * Conocido como el Pokémon Sombra, puede desvanecerse en las tinieblas y
 * absorber la energía vital de sus oponentes.
 */
public class Gengar extends AbstractPokemon {

    /**
     * Indica si Gengar está en su Forma Sombra.
     * Cuando es true:
     * - Reduce el daño físico recibido en 30%
     * - Aumenta su velocidad en 20%
     * - Aumenta su ataque especial en 10%
     *
     * Requiere al menos 30% de salud para activarse
     * Se desactiva al curarse completamente
     */
    private boolean shadowForm;

    /**
     * Constructor de Gengar. Inicializa sus estadísticas base, tipos y movimientos.
     * Estadísticas destacadas:
     * - Velocidad muy alta (350)
     * - Ataque especial excepcional (394)
     * - Defensas moderadas
     * - Tipo dual Fantasma/Veneno
     */
    public Gengar() {
        this.name = "Gengar";
        this.primaryType = Type.GHOST;
        this.secondaryType = Type.POISON;
        this.maxHP = 324;
        this.currentHP = maxHP;
        this.attack = 251;
        this.defense = 240;
        this.specialAttack = 394;
        this.specialDefense = 273;
        this.speed = 350;
        this.shadowForm = false;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Gengar puede aprender por defecto.
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
     * Método para recibir daño con reducción en Forma Sombra.
     * @param amount Cantidad de daño recibido (se reduce un 30% en Forma Sombra)
     */
    @Override
    public void takeDamage(int amount) {
        // Reducción de daño en Forma Sombra
        if (shadowForm && amount > 0) {
            amount = (int)(amount * 0.7);
            System.out.println("¡La forma sombra de Gengar reduce el daño recibido!");
        }

        currentHP = Math.max(0, currentHP - amount);
    }

    /**
     * Método para curar HP. Desactiva Forma Sombra al curarse completamente.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);

        // Desactivación al curarse completamente
        if (currentHP == maxHP) {
            shadowForm = false;
        }
    }

    /**
     * Verifica si Gengar ha sido debilitado.
     * @return true si HP == 0, false en caso contrario
     */
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }

    // Métodos específicos de Gengar

    /**
     * Verifica si Gengar está en Forma Sombra.
     * @return true si está en Forma Sombra, false en caso contrario
     */
    public boolean isInShadowForm() {
        return shadowForm;
    }

    /**
     * Activa la Forma Sombra de Gengar si tiene suficiente salud (>30% HP).
     * Efectos:
     * - Aumenta velocidad en 20%
     * - Aumenta ataque especial en 10%
     * - Reduce daño físico recibido
     */
    public void enterShadowForm() {
        if (!shadowForm && currentHP > maxHP * 0.3) {
            shadowForm = true;
            this.speed = (int)(this.speed * 1.2);
            this.specialAttack = (int)(this.specialAttack * 1.1);
            System.out.println("¡Gengar se sumerge en las sombras! Su velocidad y ataque especial aumentan.");
        } else {
            System.out.println("Gengar no tiene suficiente energía para entrar en forma sombra.");
        }
    }

    /**
     * Ataque especial: Steal Soul.
     * Drena vida del oponente y potencia temporalmente su ataque especial.
     * Efectos:
     * - Aumenta ataque especial en 30% (temporal)
     * - Recupera 20% de su HP máximo
     */
    public void stealSoul() {
        System.out.println("¡Gengar intenta robar el alma de su oponente!");

        // Potenciación temporal
        this.specialAttack = (int)(this.specialAttack * 1.3);

        // Curación por drenaje
        int healAmount = maxHP / 5;
        heal(healAmount);
        System.out.println("¡Gengar recupera " + healAmount + " PS!");
    }
}
