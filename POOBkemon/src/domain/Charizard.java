package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Charizard, la evolución final de Charmander.
 * Pokémon de tipo Fuego/Volador conocido por su poderosa llama en la cola.
 * Su estado de salud está directamente relacionado con la intensidad de su llama.
 */
public class Charizard extends AbstractPokemon implements Serializable {

    /**
     * Estado de la llama en la cola de Charizard.
     */
    private boolean flameLit;

    /**
     * Constructor de Charizard. Inicializa sus estadísticas base, tipos y movimientos.
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
    public void initializeMoves() {
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


    /**
     * Crea y devuelve una copia exacta de este Charizard.
     * @return Un nuevo objeto Charizard idéntico a este
     */
    @Override
    public Charizard clone() {
        Charizard cloned = (Charizard) super.clone();
        cloned.flameLit = this.flameLit;
        return cloned;
    }
}