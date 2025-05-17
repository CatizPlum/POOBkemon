package domain;

import java.util.ArrayList;

public class Banette extends AbstractPokemon {
    private boolean triggeredCurse;
    private boolean cursedActivated = false;

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

    @Override
    protected void initializeMoves() {
        learnMove("Cross Poison");
        learnMove("Air Slash");
        learnMove("Bite");
        learnMove("Mean Look");
        learnMove("Screech");
        learnMove("Absorb");
    }


    // Habilidad pasiva: Energía maldita
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
