package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Swampert, la evolución final de Mudkip.
 * Pokémon de tipo Agua/Tierra conocido por su gran resistencia y poder físico.
 * Su armadura fangosa lo protege de ataques eléctricos y mejora su defensa.
 */
public class Swampert extends AbstractPokemon {

    /**
     * Indica si Swampert ha activado su armadura fangosa.
     * Cuando es true:
     * - Aumenta su defensa en 20%
     * - Representa su resistencia mejorada tras recibir un ataque eléctrico
     */
    private boolean muddyArmor;

    /**
     * Constructor de Swampert. Inicializa sus estadísticas base, tipos y movimientos.
     * Características principales:
     * - HP muy alto (404)
     * - Ataque físico poderoso (350)
     * - Defensas sólidas (306)
     * - Tipo dual Agua/Tierra (inmune a Electrico)
     * - Velocidad moderada (240)
     */
    public Swampert() {
        this.name = "Swampert";
        this.primaryType = Type.WATER;
        this.secondaryType = Type.GROUND;
        this.maxHP = 404;
        this.currentHP = maxHP;
        this.attack = 350;
        this.defense = 306;
        this.specialAttack = 295;
        this.specialDefense = 306;
        this.speed = 240;
        this.muddyArmor = false;

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Swampert puede aprender por defecto.
     * Incluye movimientos de varios tipos para versatilidad en combate.
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
     * Maneja ataques eléctricos dirigidos a Swampert.
     * @param amount Cantidad de daño que habría recibido (ignorado por inmunidad)
     *
     * Efectos:
     * - No recibe daño por ser inmune a eléctricos
     * - Aumenta defensa en 20%
     * - Activa estado de armadura fangosa
     */
    public void receiveElectricAttack(int amount) {
        System.out.println("El ataque eléctrico no afecta a Swampert, ¡pero activa su armadura fangosa!");
        this.defense = (int)(this.defense * 1.2);
        this.muddyArmor = true;
    }

    /**
     * Verifica si la armadura fangosa está activa.
     * @return true si la armadura fangosa está activa, false en caso contrario
     */
    public boolean isMuddyArmorActive() {
        return muddyArmor;
    }
}