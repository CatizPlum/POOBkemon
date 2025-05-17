package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa al Pokémon Charizard, la evolución final de Charmander.
 * Pokémon de tipo Fuego/Volador conocido por su poderosa llama en la cola.
 * Su estado de salud está directamente relacionado con la intensidad de su llama.
 */
public class Charizard extends AbstractPokemon {

    /**
     * Estado de la llama en la cola de Charizard.
     * Representa la vitalidad del Pokémon:
     * - true: Llama activa (Pokémon saludable)
     * - false: Llama apagada (Pokémon en estado crítico)
     *
     * Si la llama se apaga al quedar debilitado, no puede ser revivido.
     */
    private boolean flameLit;

    /**
     * Constructor de Charizard. Inicializa sus estadísticas base, tipos y movimientos.
     * Estadísticas destacadas:
     * - Alto ataque especial (348)
     * - Buena velocidad (328)
     * - Llama inicialmente activada
     */
    public Charizard() {
        this.name = "Charizard";
        this.primaryType = Type.FIRE;
        this.secondaryType = Type.FLYING;
        this.maxHP = 360;
        this.currentHP = maxHP;
        this.attack = 293;
        this.defense = 280;
        this.specialAttack = 348;
        this.specialDefense = 295;
        this.speed = 328;
        this.flameLit = true;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Charizard puede aprender por defecto.
     * Incluye movimientos de tipo Fuego y técnicas básicas.
     */
    @Override
    protected void initializeMoves() {
        learnMove("Scratch");
        learnMove("Growl");
        learnMove("Ember");
        learnMove("Smokescreen");
        learnMove("Flamethrower");
        learnMove("Slash");
    }

    /**
     * Método para recibir daño. Controla el estado de la llama basado en el HP actual.
     * @param amount Cantidad de daño recibido
     *
     * Efectos secundarios:
     * - Apaga la llama si HP < 10%
     * - Si la llama está apagada al quedar debilitado, no puede revivir
     */
    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);

        // Umbral crítico para la llama
        if (currentHP < maxHP * 0.1) {
            flameLit = false;
        }

        // Mecánica de muerte permanente
        if (!flameLit && currentHP == 0) {
            System.out.println("¡La llama de Charizard se ha apagado! No puede ser revivido.");
        }
    }

    /**
     * Método para curar HP. Tiene restricciones si la llama está apagada.
     * @param amount Cantidad de HP a recuperar
     *
     * Efectos:
     * - No puede revivir si la llama está apagada
     * - Reactiva la llama si HP > 30%
     */
    @Override
    public void heal(int amount) {
        if (!flameLit && currentHP == 0) {
            System.out.println("No se puede revivir a Charizard, su llama se ha apagado.");
            return;
        }

        currentHP = Math.min(maxHP, currentHP + amount);

        // Umbral para reactivar la llama
        if (currentHP > maxHP * 0.3) {
            flameLit = true;
        }
    }

    // Métodos específicos de Charizard

    /**
     * Verifica el estado de la llama de Charizard.
     * @return true si la llama está activa, false si está apagada
     */
    public boolean isFlameActive() {
        return flameLit;
    }

    /**
     * Potencia los movimientos de tipo Fuego en un 20%.
     * Representa la experiencia ganada en combates difíciles.
     */
    public void powerUpFireMoves() {
        for (Move move : moves) {
            if (move.getType() == Type.FIRE) {
                move.setPower((int)(move.getPower() * 1.2));
            }
        }
    }

    /**
     * Habilidad especial: Aumenta temporalmente el ataque en un 50%.
     * Representa la furia característica de los dragones.
     */
    public void dragonRage() {
        this.attack = (int)(this.attack * 1.5);
        System.out.println("¡Charizard entra en estado de furia! Su ataque aumenta considerablemente.");
    }
}