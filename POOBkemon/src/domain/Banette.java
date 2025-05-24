package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Banette, un Pokémon de tipo Fantasma (Ghost).
 */
public class Banette extends AbstractPokemon implements Serializable {

    /**
     * Indica si la maldición post-mortem de Banette ha sido activada.
     * Evita que el efecto se active múltiples veces.
     */
    private boolean triggeredCurse = false;

    /**
     * Indica si Banette ha activado su energía maldita para potenciar su ataque.
     * El aumento de ataque solo puede activarse una vez por combate.
     */
    private boolean cursedActivated = false;

    /**
     * Constructor de Banette. Inicializa sus estadísticas base, tipo y movimientos.
     */
    public Banette() {
        this.name = "Banette";
        this.primaryType = Type.GHOST;
        this.secondaryType = null;
        this.maxHP = 332;
        this.currentHP = maxHP;
        this.attack = 361;
        this.defense = 251;
        this.specialAttack = 291;
        this.specialDefense = 247;
        this.speed = 251;
        this.moves = new ArrayList<>();

        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Banette puede aprender por defecto.
     */
    @Override
    public void initializeMoves() {
        learnMove("Cross Poison");
        learnMove("Air Slash");
        learnMove("Bite");
        learnMove("Mean Look");
        learnMove("Screech");
        learnMove("Absorb");
    }

    /**
     * Verifica si Banette ha sido debilitado y activa su maldición final.
     * @param enemy El oponente que recibirá el daño de la maldición (puede ser null)
     * @return true si Banette está debilitado, false en caso contrario
     */
    public boolean isFainted(AbstractPokemon enemy) {
        if (currentHP == 0 && !triggeredCurse) {
            System.out.println("¡Banette deja escapar un último grito maldito!");

            if (enemy != null && !enemy.isFainted()) {
                int curseDamage = Math.min(enemy.getCurrentHP(), 50); // Daño máximo de 50
                enemy.takeDamage(curseDamage);
                System.out.println("El grito maldito hiere a " + enemy.getName() + " por " + curseDamage + " puntos.");
            }

            triggeredCurse = true;
        }
        return currentHP == 0;
    }

    /**
     * Crea y devuelve una copia exacta de este objeto Banette.
     */
    @Override
    public Banette clone() {
        Banette cloned = (Banette) super.clone();
        cloned.cursedActivated = this.cursedActivated;
        cloned.triggeredCurse = this.triggeredCurse;
        return cloned;
    }
}