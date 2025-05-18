package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Blastoise, la evolución final de Squirtle.
 * Es un Pokémon de tipo Agua (Water) conocido por sus poderosos cañones hidráulicos.
 * Sus habilidades giran alrededor del manejo del agua y la defensa con su caparazón.
 */
public class Blastoise extends AbstractPokemon {

    /**
     * Estado de los cañones hidráulicos de Blastoise.
     * true = funcionando a plena capacidad
     * false = presión reducida (cuando HP < 20%)
     */
    private boolean cannonsPowered;

    /**
     * Constructor de Blastoise. Inicializa sus estadísticas base, tipo y movimientos.
     * Estadísticas destacadas:
     * - Alta defensa (328) y defensa especial (339)
     * - Cañones inicialmente activados
     */
    public Blastoise() {
        this.name = "Blastoise";
        this.primaryType = Type.WATER;
        this.secondaryType = null;
        this.maxHP = 362;
        this.currentHP = maxHP;
        this.attack = 291;
        this.defense = 328;
        this.specialAttack = 295;
        this.specialDefense = 339;
        this.speed = 280;
        this.cannonsPowered = true;

        this.moves = new ArrayList<>();

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Blastoise puede aprender por defecto.
     * Incluye movimientos de tipo Agua y técnicas defensivas.
     */
    @Override
    protected void initializeMoves() {
        learnMove("Tackle");
        learnMove("Tail Whip");
        learnMove("Water Gun");
        learnMove("Withdraw");
        learnMove("Water Pulse");
        learnMove("Bite");
    }

    /**
     * Método para recibir daño. Si el HP baja del 20%, los cañones pierden presión.
     * @param amount Cantidad de daño recibido
     */
    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);
        // Si los PS bajan demasiado, la potencia de los cañones disminuye
        if (currentHP < maxHP * 0.2) {
            cannonsPowered = false;
            System.out.println("¡Los cañones de Blastoise pierden presión!");
        }
    }

    /**
     * Método para curar HP. Si el HP supera el 50%, los cañones recuperan presión.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);

        // Si se recupera lo suficiente, los cañones recuperan potencia
        if (currentHP > maxHP * 0.5) {
            cannonsPowered = true;
            System.out.println("¡Los cañones de Blastoise recuperan su presión normal!");
        }
    }

    /**
     * Verifica si Blastoise ha sido debilitado.
     * @return true si HP == 0, false en caso contrario
     */
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }

    // Métodos específicos de Blastoise

    /**
     * Verifica el estado de los cañones hidráulicos.
     * @return true si los cañones están a plena potencia, false si están debilitados
     */
    public boolean areCannonsPowered() {
        return cannonsPowered;
    }

    /**
     * Técnica defensiva que aumenta temporalmente la defensa en un 50%.
     * Blastoise se retrae en su caparazón para protegerse.
     */
    public void shellDefense() {
        // Incrementa temporalmente la defensa
        this.defense = (int)(this.defense * 1.5);
        System.out.println("¡Blastoise se refugia en su caparazón! Su defensa aumenta considerablemente.");
    }

    /**
     * Ataque especial que potencia todos los movimientos de tipo Agua en un 30%.
     * Solo funciona si los cañones tienen suficiente presión (HP > 20%).
     */
    public void hydroCannon() {
        if (cannonsPowered) {
            // Disparo concentrado que aumenta el poder de ataque de agua
            for (Move move : moves) {
                if (move.getType() == Type.WATER) {
                    move.setPower((int)(move.getPower() * 1.3));
                }
            }
            System.out.println("¡Blastoise prepara sus cañones para un disparo concentrado!");
        } else {
            System.out.println("Los cañones de Blastoise no tienen suficiente presión para un disparo concentrado.");
        }
    }
}