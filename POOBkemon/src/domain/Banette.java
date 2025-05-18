package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Banette, un Pokémon de tipo Fantasma (Ghost).
 * Posee habilidades especiales relacionadas con maldiciones que se activan al ser debilitado
 * o al canalizar energía oscura para aumentar su ataque.
 */
public class Banette extends AbstractPokemon implements Serializable {
    /**
     * Indica si la maldición post-mortem de Banette ha sido activada.
     * Evita que el efecto se active múltiples veces.
     */
    private boolean triggeredCurse;

    /**
     * Indica si Banette ha activado su energía maldita para potenciar su ataque.
     */
    private boolean cursedActivated = false;

    /**
     * Constructor de Banette. Inicializa sus estadísticas base, tipo y movimientos.
     * Estadísticas destacadas:
     * - Alto ataque físico (361)
     * - Defensas moderadas
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
     * Movimientos incluyen ataques físicos y técnicas de tipo fantasma.
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
     * Habilidad especial: Canaliza energía maldita para aumentar permanentemente su ataque.
     * Solo puede activarse una vez por combate.
     * Efecto:
     * - Aumenta el ataque en 30 puntos
     */
    public void cursedEnergy() {
        if (!cursedActivated) {
            System.out.println("Banette canaliza energía maldita al clavarse púas...");
            this.attack += 30;
            cursedActivated = true;
            System.out.println("¡Su poder de ataque ha aumentado a " + this.attack + "!");
        } else {
            System.out.println("La energía maldita ya ha sido canalizada. Banette no puede potenciarse más.");
        }
    }

    /**
     * Verifica si Banette ha sido debilitado y activa su maldición final.
     * @param enemy El oponente que recibirá el daño de la maldición
     * @return true si Banette está debilitado, false en caso contrario
     * Efecto secundario:
     * - Inflige hasta 50 puntos de daño al oponente al ser debilitado
     * - Solo se activa una vez
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
}