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

        // Movimientos por Nivel
        moves.add(new Move("Knock Off", Type.DARK, 20, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Screech", Type.NORMAL, 0, 85, MoveCategory.STATUS));
        moves.add(new Move("Night Shade", Type.GHOST, 0, 100, MoveCategory.SPECIAL)); // Fixed damage
        moves.add(new Move("Curse", Type.GHOST, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Will-O-Wisp", Type.FIRE, 0, 85, MoveCategory.STATUS));
        moves.add(new Move("Feint Attack", Type.DARK, 60, 0, MoveCategory.PHYSICAL)); // Never misses
        moves.add(new Move("Shadow Ball", Type.GHOST, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Grudge", Type.GHOST, 0, 0, MoveCategory.STATUS));

        //  Movimientos por TM
        moves.add(new Move("Toxic", Type.POISON, 0, 90, MoveCategory.STATUS));         // TM06
        moves.add(new Move("Hidden Power", Type.NORMAL, 60, 100, MoveCategory.SPECIAL)); // TM10
        moves.add(new Move("Protect", Type.NORMAL, 0, 0, MoveCategory.STATUS));        // TM17
        moves.add(new Move("Shadow Ball", Type.GHOST, 80, 100, MoveCategory.SPECIAL)); // TM30
        moves.add(new Move("Double Team", Type.NORMAL, 0, 0, MoveCategory.STATUS));    // TM32
        moves.add(new Move("Shock Wave", Type.ELECTRIC, 60, 0, MoveCategory.SPECIAL)); // TM34
        moves.add(new Move("Facade", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));    // TM42
        moves.add(new Move("Rest", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));          // TM44
        moves.add(new Move("Attract", Type.NORMAL, 0, 100, MoveCategory.STATUS));      // TM45
        moves.add(new Move("Snatch", Type.DARK, 0, 0, MoveCategory.STATUS));           // TM49
        moves.add(new Move("Secret Power", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL)); // TM43

        // Movimientos por Huevo
        moves.add(new Move("Astonish", Type.GHOST, 30, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Imprison", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Pursuit", Type.DARK, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Disable", Type.NORMAL, 0, 55, MoveCategory.STATUS));
        moves.add(new Move("Destiny Bond", Type.GHOST, 0, 0, MoveCategory.STATUS));

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
