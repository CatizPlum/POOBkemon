package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Absol, un Pokémon de tipo oscuro (Dark).
 * Tiene una habilidad especial llamada "Superstición" que se activa al recibir
 * daño significativo y le permite realizar ataques potenciados.
 */
public class Absol extends AbstractPokemon implements Serializable {

    /** Indica si Absol está bajo el efecto de un mal presagio (Superstición). */
    private boolean hasSuperstition;

    /**
     * Constructor por defecto de Absol.
     * Inicializa sus estadísticas base, tipo y movimientos aprendidos.
     */
    public Absol() {
        this.name = "Absol";
        this.primaryType = Type.DARK;
        this.secondaryType = null;
        this.maxHP = 334;
        this.currentHP = maxHP;
        this.attack = 394;
        this.defense = 240;
        this.specialAttack = 273;
        this.specialDefense = 240;
        this.speed = 273;

        this.moves = new ArrayList<>();

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Absol puede aprender por defecto.
     * Los movimientos se añaden a la lista `moves`.
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
     * Método para recibir daño. Si el daño es mayor al 25% del HP máximo,
     * activa el estado de Superstición.
     * @param amount Cantidad de daño recibido.
     */
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            hasSuperstition = true;
            System.out.println("¡Absol siente una mala presagio y se llena de energía oscura!");
        }
    }

    /**
     * Método para curar HP. Si el HP actual supera el 70% del máximo,
     * desactiva el estado de Superstición.
     * @param amount Cantidad de HP curado.
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            hasSuperstition = false;
            System.out.println("¡Absol se calma y pierde la energía oscura del presagio!");
        }
    }

    /**
     * Verifica si Absol está bajo el efecto de Superstición.
     * @return `true` si está bajo el efecto, `false` en caso contrario.
     */
    public boolean hasSuperstition() {
        return hasSuperstition;
    }

    /**
     * Ataque especial de Absol que solo funciona si está bajo Superstición.
     * Muestra un mensaje descriptivo dependiendo del estado.
     */
    public void darkFury() {
        if (hasSuperstition) {
            System.out.println("¡Absol desata su furia oscura con un ataque devastador!");
        } else {
            System.out.println("Absol no está bajo un mal presagio en este momento.");
        }
    }
}
